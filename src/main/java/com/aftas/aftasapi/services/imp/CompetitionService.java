package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqCompetition;
import com.aftas.aftasapi.dtos.ResCompetition;
import com.aftas.aftasapi.exceptions.*;
import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.repositories.CompetitionRepository;
import com.aftas.aftasapi.services.ICompetitionService;
import com.aftas.aftasapi.utilities.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    public Page<ResCompetition> readAllPaginated(Pageable pageable) {
        return null;
    }

    @Override
    public List<ResCompetition> readAllFiltered(String filter) {
        List<Competition> competitions = repository.findAll();
        List<Competition> filteredCompetitions;
        filter = Objects.requireNonNullElse(filter, ""); // solve NullPointerException caused by .equals()

        if(filter.equals("ongoing"))
            filteredCompetitions = competitions.stream().filter(competition -> Objects.equals(competition.getDate(), LocalDate.now())).toList();
        else if(filter.equals("finished"))
            filteredCompetitions = competitions.stream().filter(competition -> competition.getDate().isBefore(LocalDate.now())).toList();
        else filteredCompetitions = competitions;

        if (filteredCompetitions.isEmpty()) throw new CompetitionNotFoundException("No competitions were found");

        return filteredCompetitions.stream()
                .map(competition -> modelMapper.map(competition, ResCompetition.class))
                .toList();
    }

    @Override
    public Page<ResCompetition> readAllFilteredPaginated(Pageable pageable, String filter) {
        Page<Competition> paginatedCompetitions;
        filter = Objects.requireNonNullElse(filter, ""); // solve NullPointerException caused by .equals()

        if(filter.equals("ongoing")) paginatedCompetitions = repository.findAllByDate(LocalDate.now(), pageable);
        else if(filter.equals("finished")) paginatedCompetitions = repository.findAllByDateBefore(LocalDate.now(), pageable);
        else paginatedCompetitions = repository.findAll(pageable);

        if(paginatedCompetitions.isEmpty()) throw new CompetitionNotFoundException("No Competitions were found");
        return paginatedCompetitions.map(competition -> modelMapper.map(competition, ResCompetition.class));
    }

    @Override
    public ResCompetition create(ReqCompetition reqCompetition) {
        if(checkByDate(reqCompetition.getDate())) throw new DuplicatedCodeException("A competition already exists in date " + reqCompetition.getDate());

        Competition competition = modelMapper.map(reqCompetition, Competition.class);

        competition.setDate(LocalDate.parse(reqCompetition.getDate(), dateFormatter));
        parseAndCheckDates(reqCompetition, competition);

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
            parseAndCheckDates(reqCompetition, competition);

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
        try {
            LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
            return repository.getByDate(parsedDate).isPresent();
        } catch (DateTimeException e) { throw new IllegalActionException("Unacceptable date/time format"); }
    }

    private void parseAndCheckDates(ReqCompetition reqCompetition, Competition competition) {
        try {
            competition.setStartTime(LocalTime.parse(reqCompetition.getStartTime(), timeFormatter));
            competition.setEndTime(LocalTime.parse(reqCompetition.getEndTime(), timeFormatter));

            if(LocalDate.now().isAfter(competition.getDate())) throw new BadRequestException("Cannot create a competition in the past");
            if(competition.getStartTime().isAfter(competition.getEndTime())) throw new BadRequestException("Start time cannot be past end time");
        } catch (DateTimeException e) { throw new IllegalActionException("Unacceptable date/time format"); }
    }
}
