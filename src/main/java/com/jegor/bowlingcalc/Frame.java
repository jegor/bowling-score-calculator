package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class Frame {

	private final boolean isTenthFrame;
	private final List<BallRoll> ballRolls;
	private int pinsLeftUp;
	private boolean isStrike;

	Frame(boolean isTenthFrame) {
		this.isTenthFrame = isTenthFrame;
		ballRolls = new ArrayList<>();
		pinsLeftUp = Game.TOTAL_PINS;
		isStrike = false;
	}

	void addBallRoll(int pinsDown)
			throws IllegalStateException, IllegalArgumentException {

		ballRolls.add(new BallRoll(pinsDown));

		if (pinsDown > pinsLeftUp)
			throw new IllegalStateException("The number of total pins knocked down in one frame cannot exceed " + Game.TOTAL_PINS);

		pinsLeftUp -= pinsDown;
		if (pinsLeftUp == 0 && countBallRolls() == 1)
			isStrike = true;
		if (isTenthFrame && isStrike) {
			pinsLeftUp = Game.TOTAL_PINS;
		}
	}

	boolean isFinished() {
		boolean isFinished;
		if (!isTenthFrame)
			isFinished = isStrike || countBallRolls() == 2;
		else
			isFinished = !isStrike && countBallRolls() > 1 || countBallRolls() > 2;
		return isFinished;
	}

	private int countBallRolls() {
		return ballRolls.size();
	}

	boolean isStrike() {
		return isStrike;
	}

	int howManyPinsKnockedDown() {
		int pinsDown = 0;
		for (BallRoll ballRoll : ballRolls) {
			pinsDown += ballRoll.getPinsDown();
		}
		return pinsDown;
	}

	/**
	 * @param rollIndex 0 for getting first throw result, 1 - second, etc
	 * @return Number of pins hit as a result of one specific throw specified by rollIndex. null when there is no such throw
	 */
	Integer getNumberOfPinsKnockedDownInOneRoll(int rollIndex) {
		return countBallRolls() - 1 < rollIndex ? null : this.ballRolls.get(rollIndex).getPinsDown();
	}

	boolean isTenthFrame() {
		return isTenthFrame;
	}
}
