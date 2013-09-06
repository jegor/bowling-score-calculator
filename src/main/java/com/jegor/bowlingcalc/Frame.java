package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class Frame {

	private final boolean isTenthFrame;

	public List<Roll> getRolls() {
		return rolls;
	}

	private final List<Roll> rolls;
	private int pinsLeftUp;
	private boolean hasStrike;
	private boolean hasSpare;

	Frame(boolean isTenthFrame) {
		this.isTenthFrame = isTenthFrame;
		rolls = new ArrayList<>();
		pinsLeftUp = Game.TOTAL_PINS;
		hasStrike = false;
		hasSpare = false;
	}

	void addBallRoll(int pinsDown)
			throws IllegalStateException, IllegalArgumentException {

		if (pinsDown > pinsLeftUp && pinsLeftUp < Game.TOTAL_PINS)
			throw new IllegalStateException("The number of total pins knocked down in one frame cannot exceed " + Game.TOTAL_PINS);

		pinsLeftUp -= pinsDown;

		final boolean isSpare = pinsLeftUp == 0 && pinsDown < Game.TOTAL_PINS && countBallRolls() == 1;
		final Roll roll = new Roll(pinsDown, isSpare);
		rolls.add(roll);
		hasStrike = roll.isStrike;
		hasSpare = hasSpare || isSpare;
		if (pinsLeftUp == 0) {
			pinsLeftUp = Game.TOTAL_PINS;
		}
	}

	boolean isFinished() {
		boolean isFinished;
		int tenthFrameRollsMax = hasStrike || hasSpare ? 3 : 2;
		if (isTenthFrame)
			isFinished = countBallRolls() >= tenthFrameRollsMax;
		else
			isFinished = hasStrike || countBallRolls() == 2;
		return isFinished;
	}

	public int countBallRolls() {
		return rolls.size();
	}

	boolean hasStrike() {
		return hasStrike;
	}

	boolean hasSpare() {
		return hasSpare;
	}

	int howManyPinsKnockedDown() {
		int pinsDown = 0;
		for (Roll roll : rolls) {
			pinsDown += roll.getPinsDown();
		}
		return pinsDown;
	}

	/**
	 * @param rollIndex 0 for getting first throw result, 1 - second, etc
	 * @return Number of pins hit as a result of one specific throw specified by rollIndex. null when there is no such throw
	 */
	Integer getNumberOfPinsKnockedDownInOneRoll(int rollIndex) {
		return countBallRolls() - 1 < rollIndex ? null : this.rolls.get(rollIndex).getPinsDown();
	}

	boolean isTenthFrame() {
		return isTenthFrame;
	}
}
