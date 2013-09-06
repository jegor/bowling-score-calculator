package com.jegor.bowlingcalc.test;

import com.jegor.bowlingcalc.BowlingScoreCalculator;
import org.junit.Before;

public abstract class TestBowlingCalculatorAbstract {

	protected BowlingScoreCalculator bowling;

	@Before
	public void setUp() throws Exception {
		bowling = new BowlingScoreCalculator();
	}

	protected void rollBall(int pinsDown) {
		bowling.addBallRollResult(pinsDown);
	}

	protected int getScore() {
		return bowling.getTotalCurrentScore();
	}
}
