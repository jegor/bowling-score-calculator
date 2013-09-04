package com.jegor.bowlingcalc;

public class Game {

    private Integer score;

    public Game() {
        score = 0;
    }

    public Integer getScore() {
        return score;
    }

    public void addThrow(Integer pinsHit) throws IllegalArgumentException {
        if (pinsHit < 0 || pinsHit > 10)
            throw new IllegalArgumentException("The number of pins hit should be from 0 to 10");
        score = score + pinsHit;
    }

}
