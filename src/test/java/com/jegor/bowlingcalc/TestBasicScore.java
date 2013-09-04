package com.jegor.bowlingcalc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBasicScore {

	private Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void testGameStart() throws Exception {
		assertEquals((Integer) 0, game.getScore());
	}

	@Test
	public void testSimpleThrows() throws Exception {
		game.addThrow(6);
		game.addThrow(4);
		game.addThrow(3);
		assertEquals((Integer) 13, game.getScore());
	}
}
