package com.jegor.bowlingcalc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicScore {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void testGameStart() throws Exception {
        assertEquals(new Integer(0), game.getScore());
    }
}
