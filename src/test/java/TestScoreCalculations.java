
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
			bowling.addThrowResult(0);
		assertEquals(0, bowling.getTotalCurrentScore());
	}

	@Test
	public void testSimpleThrows() throws Exception {
		bowling.addThrowResult(6);
		bowling.addThrowResult(3);
		bowling.addThrowResult(3);
		bowling.addThrowResult(5);
		assertEquals(17, bowling.getTotalCurrentScore());
	}

	@Test
	public void testJustStrike() throws Exception {
		bowling.addThrowResult(10);
		assertEquals(0, bowling.getTotalCurrentScore());
	}

	@Test
	public void testTwoStrikes() throws Exception {
		bowling.addThrowResult(10);
		bowling.addThrowResult(10);
		assertEquals(0, bowling.getTotalCurrentScore());
	}

	@Test
	public void testThreeStrikes() throws Exception {
		bowling.addThrowResult(10);
		bowling.addThrowResult(10);
		bowling.addThrowResult(10);
		assertEquals(30, bowling.getTotalCurrentScore());
	}

	@Test
	public void testStrikeAndTwoThrows() throws Exception {
		bowling.addThrowResult(10);
		bowling.addThrowResult(3);
		bowling.addThrowResult(2);
		assertEquals(20, bowling.getTotalCurrentScore());
	}

	@Test
	public void testTwoThrowsAndStrike() throws Exception {
		bowling.addThrowResult(2);
		bowling.addThrowResult(2);
		bowling.addThrowResult(10);
		assertEquals(4, bowling.getTotalCurrentScore());
	}

	@Test
	public void testAllStrikes() throws Exception {
		for (int i = 0; i < 12; i++)
			bowling.addThrowResult(10);
		assertEquals(300, bowling.getTotalCurrentScore());
	}
}
