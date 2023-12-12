package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqCompetition;
import com.aftas.aftasapi.dtos.ResCompetition;
import com.aftas.aftasapi.exceptions.CompetitionNotFoundException;
import com.aftas.aftasapi.exceptions.FishNotFoundException;
import com.aftas.aftasapi.exceptions.UniqueConstraintViolationException;
import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.repositories.CompetitionRepository;
import com.aftas.aftasapi.services.ICompetitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionService implements ICompetitionService {
    private final CompetitionRepository repository;
    private final ModelMapper modelMapper;

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
        try {
            Competition competition = modelMapper.map(reqCompetition, Competition.class);
            Competition savedCompetition = repository.save(competition);
            return modelMapper.map(savedCompetition, ResCompetition.class);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("Violated unique constraint (code)");
        }
    }

    @Override
    public ResCompetition update(ReqCompetition reqCompetition, String code) {
        Optional<Competition> competition = repository.findById(code);
        if (competition.isPresent()) {
            reqCompetition.setCode(code);
            Competition createdcompetition = repository.save(modelMapper.map(reqCompetition, Competition.class));
            return modelMapper.map(createdcompetition, ResCompetition.class);
        } else throw new CompetitionNotFoundException("No competition was found with code " + code);
    }

    @Override
    public void delete(String code) {
        Optional<Competition> competition = repository.findById(code);
        if (competition.isPresent()) repository.deleteById(code);
        else throw new FishNotFoundException("No competition was found with code " + code);
    }
}
