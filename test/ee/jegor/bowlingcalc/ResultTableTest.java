package ee.jegor.bowlingcalc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultTableTest extends BowlingTestHelper {

	@Test
	public void testResultTableAfterGameOver() throws Exception {
		int[] rolls = {10, 7, 3, 7, 2, 9, 1, 10, 10, 10, 2, 3, 6, 4, 7, 3, 3};
		for (int pinsDown : rolls) {
			rollBall(pinsDown);
		}
		final BowlingResultTable results = bowling.getResultTable();

		assertEquals(bowling.getCurrentTotalScore(), results.totalScore);
		assertEquals(bowling.isGameOver(), results.gameIsOver);
		assertEquals(bowling.getActiveFrameIndex(), results.activeFrameIndex);
		assertEquals(10, results.frameResults.size());
		assertEquals(
				"{\"frameScore\":13,\"rollResults\":[{\"result\":\"7\"},{\"result\":\"/\"},{\"result\":\"3\"}]}",
				results.frameResults.get(9).toJson()
		);
	}

	@Test
	public void testCurrentResultTable() throws Exception {
		int[] rolls = {10, 7, 3, 7, 2, 9, 0, 10};
		for (int pinsDown : rolls) {
			rollBall(pinsDown);
		}
		final BowlingResultTable results = bowling.getResultTable();

		assertEquals(bowling.getCurrentTotalScore(), results.totalScore);
		assertEquals(bowling.isGameOver(), results.gameIsOver);
		assertEquals(bowling.getActiveFrameIndex(), results.activeFrameIndex);
		assertEquals(results.activeFrameIndex.intValue(), results.frameResults.size() - 1);
		assertEquals(
				"{\"frameScore\":9,\"rollResults\":[{\"result\":\"9\"},{\"result\":\"-\"}]}",
				results.frameResults.get(3).toJson()
		);
		assertEquals(
				"{\"frameScore\":null,\"rollResults\":[{\"result\":\"X\"}]}",
				results.frameResults.get(4).toJson()
		);
	}

	@Test
	public void testJsonOutput() throws Exception {
		rollBall(10);
		rollBall(9);
		assertEquals(
				"{\"totalScore\":0,\"gameIsOver\":false,\"activeFrameIndex\":1," +
						"\"frameResults\":[" +
						"{\"frameScore\":null,\"rollResults\":[{\"result\":\"X\"}]}," +
						"{\"frameScore\":null,\"rollResults\":[{\"result\":\"9\"}]}" +
						"]}",
				bowling.getResultTable().toJson()
		);
	}
}
