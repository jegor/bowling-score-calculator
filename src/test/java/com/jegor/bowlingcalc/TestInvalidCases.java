package com.jegor.bowlingcalc;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestInvalidCases {

	private Game game;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void testNegativeThrowResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		game.addThrow(-1);
	}

	@Test
	public void testTooBigThrowResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		game.addThrow(11);
	}
}
