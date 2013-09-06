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

	public BowlingResultTable getResultTable() {
		return new BowlingResultTable(game);
	}

	public boolean isGameOver() {
		return game.isFinished();
	}

	public Integer getActiveFrameIndex() {
		return game.getActiveFrameIndex();
	}

	private void startNewGame() {
		game = new Game();
	}

}
