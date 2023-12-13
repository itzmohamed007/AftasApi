package com.aftas.aftasapi.services.imp;

import com.aftas.aftasapi.dtos.ReqRanking;
import com.aftas.aftasapi.dtos.ResFish;
import com.aftas.aftasapi.dtos.ResRanking;
import com.aftas.aftasapi.exceptions.FishNotFoundException;
import com.aftas.aftasapi.exceptions.LevelNotFoundException;
import com.aftas.aftasapi.exceptions.RankingNotFoundException;
import com.aftas.aftasapi.exceptions.UniqueConstraintViolationException;
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
        List<ResRanking> rankings = repository.findAll().stream()
                .map(ranking -> modelMapper.map(ranking, ResRanking.class))
                .toList();
        if(rankings.isEmpty()) {
            throw new RankingNotFoundException("No Rankings were found");
        }
        return rankings;
    }

    @Override
    public Page<ResRanking> readAllPaginated(Pageable pageable) {
        Page<Ranking> paginatedRankings = repository.findAll(pageable);
        if(paginatedRankings.isEmpty()) {
            throw new RankingNotFoundException("No Rankings were found");
        }
        return paginatedRankings.map(ranking -> modelMapper.map(ranking, ResRanking.class));
    }

    @Override
    public ResRanking create(ReqRanking reqRanking) {
        Ranking ranking = modelMapper.map(reqRanking, Ranking.class);
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
