package com.jegor;

import com.jegor.bowlingcalc.BowlingResultTable;
import com.jegor.bowlingcalc.BowlingScoreCalculator;
import com.jegor.bowlingcalc.FrameResult;
import com.jegor.bowlingcalc.RollResult;

public class UsageExample {

	public static void main(String[] args) {
		usageExample1();
		usageExample2();
	}

	public static void usageExample1() {
		BowlingScoreCalculator bowling = new BowlingScoreCalculator();
		println("New game started");

		bowling.addBallRollResult(5);
		println("Rolling ball... Knocked down 5 pins!");

		bowling.addBallRollResult(4);
		println("Rolling ball... Knocked down 4 pins!");

		println("Current score is " + bowling.getTotalCurrentScore());
		println("Full game current state in JSON format: " + bowling.getResultTable().toJson());
	}

	public static void usageExample2() {
		int[] rolls = {10, 7, 3, 7, 2, 9, 1, 10, 10, 10, 2, 3, 6, 4, 10, 9, 0
		};
		BowlingScoreCalculator bowling = new BowlingScoreCalculator();
		println("-------------------");
		println("New game started");
		for (int pinsDown : rolls) {
			bowling.addBallRollResult(pinsDown);
		}
		BowlingResultTable resultTable = bowling.getResultTable();
		println("Results table:");
		String firstRow = String.format("%15s", "Result: ");
		String secondRow = String.format("%15s", "Frame score: ");
		String thirdRow = String.format("%15s", "Total score: ") + resultTable.totalScore;
		for (FrameResult frameResult : resultTable.frameResults) {
			String frameResultString = "";
			for (RollResult roll : frameResult.rollResults) {
				frameResultString += roll.result + " ";
			}
			firstRow += String.format("%8s", frameResultString + "| ");
			secondRow += String.format("%8s", (frameResult.frameScore == null ? "" : frameResult.frameScore) + " | ");
		}
		println(firstRow);
		println(secondRow);
		println(thirdRow);

	}

	private static void println(String s) {
		System.out.println(s);
	}

}
