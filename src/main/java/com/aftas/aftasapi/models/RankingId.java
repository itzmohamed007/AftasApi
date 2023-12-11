package com.aftas.aftasapi.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MapsId;

import java.io.Serializable;

@Embeddable
public class RankingId implements Serializable {
    private String code;
    private Integer num;
}
