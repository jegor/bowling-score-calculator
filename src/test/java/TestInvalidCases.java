import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestInvalidCases extends TestBowlingCalculatorAbstract {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testNegativeThrowResult() throws Exception {
		exception.expect(IllegalArgumentException.class);
		rollBall(-1);
	}

	@Test
	public void testTooBigThrowResult() throws Exception {
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
	public void testTooManySimpleThrows() throws Exception {
		for (int i = 0; i < 10; i++) {
			rollBall(2);
			rollBall(5);
		}
		exception.expect(IllegalStateException.class);
		rollBall(1);
	}

	@Test
	public void testTooManyStrikes() throws Exception {
		for (int i = 0; i < 10 + 2; i++)
			rollBall(10);
		exception.expect(IllegalStateException.class);
		rollBall(1);
	}
}
