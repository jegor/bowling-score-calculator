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
		assertEquals(0, game.getScore());
	}

	@Test
	public void testSimpleThrows() throws Exception {
		game.addThrow(6);
		game.addThrow(3);
		game.addThrow(3);
		game.addThrow(5);
		assertEquals(17, game.getScore());
	}

	@Test
	public void testJustStrike() throws Exception {
		game.addThrow(10);
		assertEquals(0, game.getScore());
	}

	@Test
	public void testTwoStrikes() throws Exception {
		game.addThrow(10);
		game.addThrow(10);
		assertEquals(0, game.getScore());
	}

	@Test
	public void testThreeStrikes() throws Exception {
		game.addThrow(10);
		game.addThrow(10);
		game.addThrow(10);
		assertEquals(30, game.getScore());
	}

	@Test
	public void testStrikeAndTwoThrows() throws Exception {
		game.addThrow(10);
		game.addThrow(3);
		game.addThrow(2);
		assertEquals(20, game.getScore());
	}

	@Test
	public void testTwoThrowsAndStrike() throws Exception {
		game.addThrow(2);
		game.addThrow(2);
		game.addThrow(10);
		assertEquals(4, game.getScore());
	}
}
