package com.aftas.aftasapi.utilities;

import java.time.LocalDate;

public class CodeGenerator {
    public static String generateCompetitionCode(String location, String date) {
        String part1 = date.substring(0, 6), part2 = date.substring(8, 10);
        return location.substring(0, 3) + "-" + part1 + part2;
    }
}
