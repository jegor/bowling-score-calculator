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
		bowling.addThrowResult(-1);
	}

	@Test
	public void testTooBigThrowResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		bowling.addThrowResult(11);
	}

	@Test
	public void testTooBigFrameResult() throws Exception {
		exception.expect(IllegalStateException.class);
		bowling.addThrowResult(4);
		bowling.addThrowResult(9);
	}

	@Test
	public void testTooManySimpleThrows() throws Exception {
		for (int i = 0; i < 10; i++) {
			bowling.addThrowResult(2);
			bowling.addThrowResult(5);
		}
		exception.expect(IllegalStateException.class);
		bowling.addThrowResult(1);
	}

	@Test
	public void testTooManyStrikes() throws Exception {
		for (int i = 0; i < 10 + 2; i++)
			bowling.addThrowResult(10);
		exception.expect(IllegalStateException.class);
		bowling.addThrowResult(1);
	}
}
