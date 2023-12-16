package com.aftas.aftasapi.services;

import com.aftas.aftasapi.dtos.ReqHunting;
import com.aftas.aftasapi.dtos.ResHunting;
import org.springframework.stereotype.Repository;

@Repository
public interface IHuntingService extends MainService<ReqHunting, ResHunting, Integer>{

}
