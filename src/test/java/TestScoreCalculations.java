
import com.jegor.bowlingcalc.BowlingScoreCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestScoreCalculations {

	private BowlingScoreCalculator bowling;

	@Before
	public void setUp() throws Exception {
		bowling = new BowlingScoreCalculator();
	}

	@Test
	public void testGameStart() throws Exception {
		assertEquals(0, bowling.getTotalCurrentScore());
	}

	@Test
	public void testAllZeros() throws Exception {
		for (int i = 0; i < 20; i++)
			bowling.addBallRollResult(0);
		assertEquals(0, bowling.getTotalCurrentScore());
	}

	@Test
	public void testSimpleThrows() throws Exception {
		bowling.addBallRollResult(6);
		bowling.addBallRollResult(3);
		bowling.addBallRollResult(3);
		bowling.addBallRollResult(5);
		assertEquals(17, bowling.getTotalCurrentScore());
	}

	@Test
	public void testJustStrike() throws Exception {
		bowling.addBallRollResult(10);
		assertEquals(0, bowling.getTotalCurrentScore());
	}

	@Test
	public void testTwoStrikes() throws Exception {
		bowling.addBallRollResult(10);
		bowling.addBallRollResult(10);
		assertEquals(0, bowling.getTotalCurrentScore());
	}

	@Test
	public void testThreeStrikes() throws Exception {
		bowling.addBallRollResult(10);
		bowling.addBallRollResult(10);
		bowling.addBallRollResult(10);
		assertEquals(30, bowling.getTotalCurrentScore());
	}

	@Test
	public void testStrikeAndTwoThrows() throws Exception {
		bowling.addBallRollResult(10);
		bowling.addBallRollResult(3);
		bowling.addBallRollResult(2);
		assertEquals(20, bowling.getTotalCurrentScore());
	}

	@Test
	public void testTwoThrowsAndStrike() throws Exception {
		bowling.addBallRollResult(2);
		bowling.addBallRollResult(2);
		bowling.addBallRollResult(10);
		assertEquals(4, bowling.getTotalCurrentScore());
	}

	@Test
	public void testAllStrikes() throws Exception {
		for (int i = 0; i < 12; i++)
			bowling.addBallRollResult(10);
		assertEquals(300, bowling.getTotalCurrentScore());
	}
}
