package com.aftas.aftasapi.services;

import com.aftas.aftasapi.dtos.ReqCompetition;
import com.aftas.aftasapi.dtos.ResCompetition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompetitionService extends MainService<ReqCompetition, ResCompetition, String> {
    List<ResCompetition> readAllFiltered(String filter);
    Page<ResCompetition> readAllFilteredPaginated(Pageable pageable, String filter);
}
