import com.jegor.bowlingcalc.BowlingScoreCalculator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestInvalidCases {

	private BowlingScoreCalculator bowling;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		bowling = new BowlingScoreCalculator();
	}

	@Test
	public void testNegativeThrowResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		bowling.addBallRollResult(-1);
	}

	@Test
	public void testTooBigThrowResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		bowling.addBallRollResult(11);
	}

	@Test
	public void testTooBigFrameResult() throws Exception {
		exception.expect(IllegalStateException.class);
		bowling.addBallRollResult(4);
		bowling.addBallRollResult(9);
	}

	@Test
	public void testTooManySimpleThrows() throws Exception {
		for (int i = 0; i < 10; i++) {
			bowling.addBallRollResult(2);
			bowling.addBallRollResult(5);
		}
		exception.expect(IllegalStateException.class);
		bowling.addBallRollResult(1);
	}

	@Test
	public void testTooManyStrikes() throws Exception {
		for (int i = 0; i < 10 + 2; i++)
			bowling.addBallRollResult(10);
		exception.expect(IllegalStateException.class);
		bowling.addBallRollResult(1);
	}
}
