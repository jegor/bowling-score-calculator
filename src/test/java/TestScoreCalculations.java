
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestScoreCalculations extends TestBowlingCalculatorAbstract {

	@Test
	public void testGameStart() throws Exception {
		assertEquals(0, getScore());
	}

	@Test
	public void testAllZeros() throws Exception {
		for (int i = 0; i < 20; i++)
			rollBall(0);
		assertEquals(0, getScore());
	}

	@Test
	public void testSimpleThrows() throws Exception {
		rollBall(6);
		rollBall(3);
		rollBall(3);
		rollBall(5);
		assertEquals(17, getScore());
	}

	@Test
	public void testJustStrike() throws Exception {
		rollBall(10);
		assertEquals(0, getScore());
	}

	@Test
	public void testTwoStrikes() throws Exception {
		rollBall(10);
		rollBall(10);
		assertEquals(0, getScore());
	}

	/**
	 * Example from http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring
	 */
	@Test
	public void testStrikeAndTwoThrows() throws Exception {
		rollBall(10);
		rollBall(3);
		rollBall(6);
		assertEquals(28, getScore());
	}

	/**
	 * Example from http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring
	 */
	@Test
	public void testTwoStrikesAndOpenFrame() throws Exception {
		rollBall(10);
		rollBall(10);
		rollBall(9);
		rollBall(0);
		assertEquals(57, getScore());
	}

	/**
	 * Example from http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring
	 */
	@Test
	public void testTriple() throws Exception {
		rollBall(10);
		rollBall(10);
		rollBall(10);
		rollBall(0);
		rollBall(9);
		assertEquals(78, getScore());
	}

	@Test
	public void testTwoThrowsAndStrike() throws Exception {
		rollBall(2);
		rollBall(2);
		rollBall(10);
		assertEquals(4, getScore());
	}

	@Test
	public void testAllStrikes() throws Exception {
		for (int i = 0; i < 12; i++)
			rollBall(10);
		assertEquals(300, getScore());
	}

	/**
	 * Example from http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring
	 */
	@Test
	public void testSpare() throws Exception {
		rollBall(7);
		rollBall(3);
		rollBall(4);
		rollBall(2);
		assertEquals(20, getScore());
	}


}
