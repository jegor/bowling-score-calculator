package com.jegor.bowlingcalc;

public class BowlingScoreCalculator {

	private Game game;

	public BowlingScoreCalculator() {
		startNewGame();
	}

	public void startNewGame() {
		game = new Game();
	}

	public void addThrowResult(int pinsHit)
			throws IllegalStateException, IllegalArgumentException{
		game.addThrowResult(pinsHit);
	}

	public int getTotalCurrentScore() {
		return game.getScore();
	}


}
