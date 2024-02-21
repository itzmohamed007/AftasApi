package com.aftas.aftasapi.services;

import com.aftas.aftasapi.dtos.ReqRanking;
import com.aftas.aftasapi.dtos.ResRanking;
import com.aftas.aftasapi.models.RankingId;

import java.util.List;

public interface IRankingService extends MainService<ReqRanking, ResRanking, RankingId>{
    List<ResRanking> calcRanking(String code);
}
