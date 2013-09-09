package ee.jegor.bowlingcalc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControlTest extends BowlingTestHelper {

	@Test
	public void testStartNewGame() throws Exception {
		rollBall(4);
		rollBall(3);
		bowling.startNewGame();
		assertEquals(0, bowling.getCurrentTotalScore());
		assertEquals(new Integer(0), bowling.getActiveFrameIndex());
	}

	@Test
	public void testGetActiveIndex1() throws Exception {
		rollBall(4);
		rollBall(3);
		assertEquals(new Integer(1), bowling.getActiveFrameIndex());
	}

	@Test
	public void testGetActiveIndex2() throws Exception {
		rollBall(4);
		rollBall(3);
		rollBall(3);
		assertEquals(new Integer(1), bowling.getActiveFrameIndex());
	}

	@Test
	public void testGetActiveIndexAfterGameOver() throws Exception {
		for (int i = 0; i < 12; i++)
			rollBall(10);
		assertEquals(null, bowling.getActiveFrameIndex());
	}

	@Test
	public void testGameNotOver() throws Exception {
		for (int i = 0; i < 11; i++)
			rollBall(10);
		assertEquals(false, bowling.isGameOver());
	}

	@Test
	public void testGameOver() throws Exception {
		for (int i = 0; i < 12; i++)
			rollBall(10);
		assertEquals(true, bowling.isGameOver());
	}

}
