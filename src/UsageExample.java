import ee.jegor.bowlingcalc.BowlingResultTable;
import ee.jegor.bowlingcalc.BowlingScoreCalculator;
import ee.jegor.bowlingcalc.FrameResult;
import ee.jegor.bowlingcalc.RollResult;

public class UsageExample {

	public static void main(String[] args) {
		usageExample1();
		usageExample2();
	}

	private static void usageExample1() {
		BowlingScoreCalculator bowling = new BowlingScoreCalculator();
		println("New game started");

		bowling.addRollResult(5);
		println("Rolling ball... Knocked down 5 pins!");

		bowling.addRollResult(4);
		println("Rolling ball... Knocked down 4 pins!");

		println("Current score is " + bowling.getCurrentTotalScore());
		println("Full game current state in JSON format: " + bowling.getResultTable().toJson());
	}

	private static void usageExample2() {
		int[] rolls = {10, 7, 3, 7, 2, 9, 1, 10, 10, 10, 2, 3, 6, 4, 10, 9, 0};
		BowlingScoreCalculator bowling = new BowlingScoreCalculator();
		println("-------------------");
		println("New game started");
		for (int pinsDown : rolls)
			bowling.addRollResult(pinsDown);
		BowlingResultTable resultTable = bowling.getResultTable();
		println("Results table:");
		String line1 = String.format("%15s", "Result: ");
		String line2 = String.format("%15s", "Frame score: ");
		String line3 = String.format("%15s", "Total score: ") + resultTable.totalScore;
		for (FrameResult frameResult : resultTable.frameResults) {
			String frameResultString = "";
			for (RollResult roll : frameResult.rollResults)
				frameResultString += roll.result + " ";
			line1 += String.format("%8s", frameResultString + "| ");
			line2 += String.format("%8s", (frameResult.frameScore == null ? "" : frameResult.frameScore) + " | ");
		}
		println(line1);
		println(line2);
		println(line3);

	}

	private static void println(String s) {
		System.out.println(s);
	}

}
