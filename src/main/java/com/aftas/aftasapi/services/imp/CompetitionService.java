package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqCompetiton;
import com.aftas.aftasapi.dtos.ResCompetition;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.exceptions.FishNotFoundException;
import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Fish;
import com.aftas.aftasapi.repositories.CompetitionRepository;
import com.aftas.aftasapi.services.ICompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionService implements ICompetitionService {
    private final CompetitionRepository repository;

    @Override
    public ResCompetition read(String code) {
        Optional<Competition> competition = repository.findById(code);
        if(fish.isPresent()) {
            return modelMapper.map(fish.get(), ResFish.class);
        }
        throw new FishNotFoundException("Fish not found with name " + name);
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
    public ResCompetition create(ReqCompetiton reqCompetiton) {
        return null;
    }

    @Override
    public ResCompetition update(ReqCompetiton reqCompetiton, String code) {
        return null;
    }

    @Override
    public void delete(String code) {

    }
}
