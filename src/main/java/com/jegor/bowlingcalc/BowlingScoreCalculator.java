package com.jegor.bowlingcalc;

/**
 * Main bowling score calculator controller class
 *
 * @author Jegor Guzhvin
 */

public class BowlingScoreCalculator {

	private GameModel game;

	/**
	 * Initialize calculator and start new game
	 */
	public BowlingScoreCalculator() {
		startNewGame();
	}

	/**
	 * Add a ball roll
	 *
	 * @param pinsDownCount Number of pins knocked down as a result of current roll. Must be in a range 0-10.
	 * @throws IllegalStateException    When game is over, or argument is larger than number of the pins before roll
	 * @throws IllegalArgumentException When argument is out of the range 0-10
	 */
	public void addBallRollResult(int pinsDownCount)
			throws IllegalStateException, IllegalArgumentException {
		game.addBallRollResult(pinsDownCount);
	}

	/**
	 * Returns a game total score (current total score if the play is not finished)
	 *
	 * @return
	 */
	public int getTotalCurrentScore() {
		return game.getScore();
	}

	/**
	 * Return a game result table object
	 *
	 * @return
	 */
	public BowlingResultTable getResultTable() {
		return new BowlingResultTable(game);
	}

	/**
	 * Is current game finished or not
	 *
	 * @return
	 */
	public boolean isGameOver() {
		return game.isFinished();
	}

	/**
	 * Returns the index of the current frame (for the next roll). The first frame index is 0, the second - 1, etc)
	 *
	 * @return null if the game is over
	 */
	public Integer getActiveFrameIndex() {
		return game.getActiveFrameIndex();
	}

	/**
	 * Reset current game and start the new one
	 */
	private void startNewGame() {
		game = new GameModel();
	}

}
