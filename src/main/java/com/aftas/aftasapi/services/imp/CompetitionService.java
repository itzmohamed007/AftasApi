package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqCompetition;
import com.aftas.aftasapi.dtos.ResCompetition;
import com.aftas.aftasapi.exceptions.CompetitionNotFoundException;
import com.aftas.aftasapi.exceptions.DuplicatedCodeException;
import com.aftas.aftasapi.exceptions.FishNotFoundException;
import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.repositories.CompetitionRepository;
import com.aftas.aftasapi.services.ICompetitionService;
import com.aftas.aftasapi.utilities.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionService implements ICompetitionService {
    private final CompetitionRepository repository;
    private final ModelMapper modelMapper;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public ResCompetition read(String code) {
        Optional<Competition> competition = repository.findById(code);
        if(competition.isPresent()) {
            return modelMapper.map(competition.get(), ResCompetition.class);
        }
        throw new CompetitionNotFoundException("Competition not found with code " + code);
    }

    @Override
    public List<ResCompetition> readAll() {
        List<ResCompetition> competitions = repository.findAll().stream()
                .map(competition -> modelMapper.map(competition, ResCompetition.class))
                .toList();
        if(competitions.isEmpty()) {
            throw new CompetitionNotFoundException("No competitions were found");
        }
        return competitions;
    }

    @Override
    public Page<ResCompetition> readAllPaginated(Pageable pageable) {
        Page<Competition> paginatedCompetitions = repository.findAll(pageable);
        if(paginatedCompetitions.isEmpty()) {
            throw new CompetitionNotFoundException("No Competitions were found");
        }
        return paginatedCompetitions.map(competition -> modelMapper.map(competition, ResCompetition.class));
    }

    @Override
    public ResCompetition create(ReqCompetition reqCompetition) {
        if(checkByDate(reqCompetition.getDate())) throw new DuplicatedCodeException("A competition already exists in date " + reqCompetition.getDate());

        Competition competition = modelMapper.map(reqCompetition, Competition.class);

        competition.setDate(LocalDate.parse(reqCompetition.getDate(), dateFormatter));
        competition.setStartTime(LocalTime.parse(reqCompetition.getStartTime(), timeFormatter));
        competition.setEndTime(LocalTime.parse(reqCompetition.getEndTime(), timeFormatter));

        competition.setCode(CodeGenerator.generateCompetitionCode(reqCompetition.getLocation(), reqCompetition.getDate()));

        Competition savedCompetition = repository.save(competition);
        return modelMapper.map(savedCompetition, ResCompetition.class);
    }

    @Override
    public ResCompetition update(ReqCompetition reqCompetition, String code) {
        Optional<Competition> dbCompetition = repository.findById(code);
        if (dbCompetition.isPresent()) {
            Competition competition = modelMapper.map(reqCompetition, Competition.class);

            competition.setCode(code);
            competition.setDate(dbCompetition.get().getDate()); // Keeping old date, to prevent code generation bugs
            competition.setStartTime(LocalTime.parse(reqCompetition.getStartTime(), timeFormatter));
            competition.setEndTime(LocalTime.parse(reqCompetition.getEndTime(), timeFormatter));

            Competition createdcompetition = repository.save(competition);
            return modelMapper.map(createdcompetition, ResCompetition.class);
        } else throw new CompetitionNotFoundException("No competition was found with code " + code);
    }

    @Override
    public void delete(String code) {
        Optional<Competition> competition = repository.findById(code);
        if (competition.isPresent()) repository.deleteById(code);
        else throw new FishNotFoundException("No competition was found with code " + code);
    }

    private boolean checkByDate(String date) {
        return repository.getByDate(LocalDate.parse(date, dateFormatter)).isPresent();
    }
}
