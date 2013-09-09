package ee.jegor.bowlingcalc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvalidCasesTest extends BowlingTestHelper {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testNegativeRollResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		rollBall(-1);
	}

	@Test
	public void testTooBigRollResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		rollBall(11);
	}

	@Test
	public void testTooBigFrameResult() throws Exception {
		exception.expect(IllegalStateException.class);
		rollBall(4);
		rollBall(9);
	}

	@Test
	public void testTooManySimpleRolls() throws Exception {
		for (int i = 0; i < 10; i++) {
			rollBall(2);
			rollBall(5);
		}
		exception.expect(IllegalStateException.class);
		rollBall(0);
	}

	@Test
	public void testGameOverAfterAllStrikes() throws Exception {
		for (int i = 0; i < 10 + 2; i++)
			rollBall(10);
		exception.expect(IllegalStateException.class);
		rollBall(1);
	}

	@Test
	public void testGameOverAfterTenthFrameWithSpare() throws Exception {
		for (int i = 0; i < 9; i++) {
			rollBall(2);
			rollBall(5);
		}
		rollBall(7);
		rollBall(3);
		rollBall(0);
		exception.expect(IllegalStateException.class);
		rollBall(0);
	}

}
