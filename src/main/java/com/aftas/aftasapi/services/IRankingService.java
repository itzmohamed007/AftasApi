package com.aftas.aftasapi.services;

import com.aftas.aftasapi.dtos.ReqRanking;
import com.aftas.aftasapi.dtos.ResRanking;
import com.aftas.aftasapi.models.Competition;
import com.aftas.aftasapi.models.Ranking;
import com.aftas.aftasapi.models.RankingId;

import java.util.List;

public interface IRankingService extends MainService<ReqRanking, ResRanking, RankingId>{
    List<Ranking> calcRanking(Competition competition);
}
