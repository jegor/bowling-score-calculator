package com.jegor;

import com.jegor.bowlingcalc.BowlingScoreCalculator;

public class UsageExample {

	public static void main(String[] args) {
		BowlingScoreCalculator bowling = new BowlingScoreCalculator();
		bowling.addBallRollResult(5);
		bowling.addBallRollResult(4);
		System.out.println("Current score is " + bowling.getTotalCurrentScore());
	}

}
