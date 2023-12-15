package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqRanking;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.dtos.ResRanking;
import com.aftas.aftasapi.exceptions.*;
import com.aftas.aftasapi.models.*;
import com.aftas.aftasapi.repositories.CompetitionRepository;
import com.aftas.aftasapi.repositories.MemberRepository;
import com.aftas.aftasapi.repositories.RankingRepository;
import com.aftas.aftasapi.services.IRankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService implements IRankingService {
    private final RankingRepository repository;
    private final ModelMapper modelMapper;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;


    @Override
    public ResRanking read(RankingId rankingId) {
        Optional<Ranking> ranking = repository.findById(rankingId);
        if (ranking.isPresent()) {
            return modelMapper.map(ranking.get(), ResRanking.class);
        } else {
            throw new FishNotFoundException("Ranking not found with id " + rankingId);
        }
    }

    @Override
    public List<ResRanking> readAll() {
        List<ResRanking> rankings = repository.findAll().stream()
                .map(ranking -> modelMapper.map(ranking, ResRanking.class))
                .toList();
        if (rankings.isEmpty()) {
            throw new RankingNotFoundException("No Rankings were found");
        }
        return rankings;
    }

    @Override
    public Page<ResRanking> readAllPaginated(Pageable pageable) {
        Page<Ranking> paginatedRankings = repository.findAll(pageable);
        if (paginatedRankings.isEmpty()) {
            throw new RankingNotFoundException("No Rankings were found");
        }
        return paginatedRankings.map(ranking -> modelMapper.map(ranking, ResRanking.class));
    }

//    @Override
//    public ResRanking create(ReqRanking reqRanking) {
//        RankingId rankingId = new RankingId();
//        reqRanking.setCompetition(reqRanking.getCompetition());
//        reqRanking.setMember(reqRanking.getMember());
//
//        // Check 1: check if member and competition already exists
//        Member member = memberRepository.findById(reqRanking.getMember()).orElseThrow(() -> new ResourceNotFoundException("Member not found with number " + reqRanking.getMember()));
//        Competition competition = competitionRepository.findById(reqRanking.getCompetition()).orElseThrow(() -> new ResourceNotFoundException("Competition not found with code " + reqRanking.getCompetition()));
//
//        Ranking ranking = new Ranking();
//        ranking.setId(rankingId);
//        ranking.setCompetition(competition);
//        ranking.setMember(member);
//
//        // Check 2: check if member is already present in competition
//        if(repository.existsById(rankingId)) {
//            System.out.println("member is already present in database");
//            throw new UniqueConstraintViolationException("member already exists in this competition");
//        }
//        System.out.println("member is not present in database");
//
//        Ranking savedRanking = repository.save(ranking);
//        return modelMapper.map(savedRanking, ResRanking.class);
//    }

    @Override
    public ResRanking create(ReqRanking reqRanking) {
        // Check 1: check if member and competition already exist
        Member member = memberRepository.findById(reqRanking.getMember())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with number " + reqRanking.getMember()));

        Competition competition = competitionRepository.findById(reqRanking.getCompetition())
                .orElseThrow(() -> new ResourceNotFoundException("Competition not found with code " + reqRanking.getCompetition()));

        RankingId rankingId = new RankingId(reqRanking.getCompetition(), reqRanking.getMember());

        // Check 2: check if member is already present in competition
        if (repository.existsById(rankingId)) throw new UniqueConstraintViolationException("Member already exists in this competition");

        // Check 3: check for 1 day exception
        long daysUntilCompetitionStart = ChronoUnit.DAYS.between(LocalDate.now(), competition.getDate());

        if (daysUntilCompetitionStart == 1) throw new TimeExceededException("Cannot assign member to a competition before 24 hours");
        else if(daysUntilCompetitionStart < 1) throw new TimeExceededException("Competition has finished " + -daysUntilCompetitionStart + " days ago");

        // Check 4: check if competition is full already
        int membersCount = competitionRepository.getMembersCount(competition.getCode());
        if (membersCount >= competition.getNumberOfParticipants()) throw new IllegalActionException("Competition is already full");

        // Creating new ranking record
        Ranking ranking = new Ranking(rankingId, reqRanking.getRank(), reqRanking.getScore(), member, competition);
        Ranking savedRanking = repository.save(ranking);
        return modelMapper.map(savedRanking, ResRanking.class);
    }

    @Override
    public ResRanking update(ReqRanking reqRanking, RankingId rankingId) {
        Optional<Ranking> ranking = repository.findById(rankingId);
        if (ranking.isPresent()) {
            Ranking createdRanked = repository.save(modelMapper.map(reqRanking, Ranking.class));
            return modelMapper.map(createdRanked, ResRanking.class);
        }
        throw new RankingNotFoundException("No ranking was found with id " + rankingId);
    }

    @Override
    public void delete(RankingId rankingId) {
        Optional<Ranking> ranking = repository.findById(rankingId);
        if (ranking.isPresent()) repository.deleteById(rankingId);
        else throw new RankingNotFoundException("No ranking was found with id " + rankingId);
    }
}
