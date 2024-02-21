package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqHunting;
import com.aftas.aftasapi.dtos.ResHunting;
import com.aftas.aftasapi.exceptions.IllegalActionException;
import com.aftas.aftasapi.exceptions.ResourceNotFoundException;
import com.aftas.aftasapi.models.*;
import com.aftas.aftasapi.repositories.*;
import com.aftas.aftasapi.services.IHuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingService implements IHuntingService {
    private final HuntingRepository huntingRepository;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final FishRepository fishRepository;
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResHunting read(Integer id) {
        Optional<Hunting> hunting = huntingRepository.findById(id);
        if(hunting.isPresent())
            return modelMapper.map(hunting.get(), ResHunting.class);
        throw new ResourceNotFoundException("Hunting not found with id " + id);
    }

    @Override
    public List<ResHunting> readAll() {
        List<ResHunting> hunting = huntingRepository.findAll().stream()
                .map(hunt -> modelMapper.map(hunt, ResHunting.class))
                .toList();
        if(hunting.isEmpty()) throw new ResourceNotFoundException("No Hunts were found");
        return hunting;
    }

    @Override
    public Page<ResHunting> readAllPaginated(Pageable pageable) {
        Page<Hunting> paginatedHunting = huntingRepository.findAll(pageable);
        if(paginatedHunting.isEmpty()) throw new ResourceNotFoundException("No Hunting were found");
        return paginatedHunting.map(hunt -> modelMapper.map(hunt, ResHunting.class));
    }

    @Override
    public ResHunting create(ReqHunting reqHunting) {
        Member member = memberRepository.findById(reqHunting.getMember())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with num " + reqHunting.getMember()));
        Competition competition = competitionRepository.findById(reqHunting.getCompetition())
                .orElseThrow(() -> new ResourceNotFoundException("Competition not found with code " + reqHunting.getCompetition()));
        Fish fish = fishRepository.findById(reqHunting.getFish())
                .orElseThrow(() -> new ResourceNotFoundException("Fish not found with name " + reqHunting.getFish()));

        if (LocalTime.now().isBefore(competition.getStartTime()) || LocalDate.now().isBefore(competition.getDate()))
            throw new IllegalActionException("You cannot add new hunts to a competition that did not start yet.");

        if (LocalTime.now().isAfter(competition.getEndTime()) || LocalDate.now().isAfter(competition.getDate()))
            throw new IllegalActionException("You cannot add new hunts to a competition that had already ended.");

        if (reqHunting.getFishWeight() <= fish.getAverageWeight())
            throw new IllegalActionException("The weight of " + fish.getName() + " fish must be at least equal to " + fish.getAverageWeight() + "g.");

        RankingId rankingId = new RankingId(competition.getCode(), member.getNum());
        Ranking ranking = rankingRepository.findById(rankingId)
                .orElseThrow(() -> new IllegalActionException("Cannot add a hunting to a member that is not assigned to competition with code " + competition.getCode()));

        ranking.setScore(ranking.getScore() + fish.getLevel().getPoints());
        rankingRepository.save(ranking);

        Optional<Hunting> dbHunting = huntingRepository.findHuntingByCompetitionAndMemberAndFish(competition, member, fish);
        Hunting hunting;

        if(dbHunting.isPresent()) {
            hunting = dbHunting.get();
            int newNumberOfFish = hunting.getNumberOfFish();
            hunting.setNumberOfFish(++newNumberOfFish);
        } else {
            hunting = new Hunting();
            hunting.setFish(fish);
            hunting.setMember(member);
            hunting.setCompetition(competition);
            hunting.setNumberOfFish(1);
        }

        huntingRepository.save(hunting);
        return modelMapper.map(hunting, ResHunting.class);
    }

    @Override
    public ResHunting update(ReqHunting reqHunting, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
