package ee.jegor.bowlingcalc;

import org.junit.Before;

public abstract class BowlingTestHelper {

	protected BowlingScoreCalculator bowling;

	@Before
	public void setUp() throws Exception {
		bowling = new BowlingScoreCalculator();
	}

	protected void rollBall(int pinsDown) {
		bowling.addRollResult(pinsDown);
	}

	protected int getScore() {
		return bowling.getCurrentTotalScore();
	}
}
