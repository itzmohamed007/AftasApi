package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqRanking;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.dtos.ResRanking;
import com.aftas.aftasapi.exceptions.FishNotFoundException;
import com.aftas.aftasapi.models.Fish;
import com.aftas.aftasapi.models.Ranking;
import com.aftas.aftasapi.models.RankingId;
import com.aftas.aftasapi.repositories.RankingRepository;
import com.aftas.aftasapi.services.IRankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService implements IRankingService {
    private final RankingRepository repository;
    private final ModelMapper modelMapper;


    @Override
    public ResRanking read(RankingId rankingId) {
        Optional<Ranking> ranking = repository.findById(rankingId);
        if(ranking.isPresent()) {
            return modelMapper.map(ranking.get(), ResRanking.class);
        } else {
            throw new FishNotFoundException("Ranking not found with id " + rankingId);
        }
    }

    @Override
    public List<ResRanking> readAll() {
        return null;
    }

    @Override
    public Page<ResRanking> readAllPaginated(Pageable pageable) {
        return null;
    }

    @Override
    public ResRanking create(ReqRanking reqRanking) {
        return null;
    }

    @Override
    public ResRanking update(ReqRanking reqRanking, RankingId id) {
        return null;
    }

    @Override
    public void delete(RankingId rankingId) {

    }
}
