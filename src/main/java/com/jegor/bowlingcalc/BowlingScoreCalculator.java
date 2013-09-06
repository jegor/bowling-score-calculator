package com.jegor.bowlingcalc;

public class BowlingScoreCalculator {

	private Game game;

	public BowlingScoreCalculator() {
		startNewGame();
	}

	public void addBallRollResult(int pinsDownCount)
			throws IllegalStateException, IllegalArgumentException {
		game.addBallRollResult(pinsDownCount);
	}

	public int getTotalCurrentScore() {
		return game.getScore();
	}

	private void startNewGame() {
		game = new Game();
	}

}
