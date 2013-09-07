package com.jegor.bowlingcalc;

/**
 * Bowling ball roll (throw) result model class
 *
 * @author Jegor Guzhvin
 */

class RollModel {

	private final int pinsDown;
	public final boolean isStrike;
	public final boolean isSpare;

	RollModel(int pinsDown, boolean isSpare) throws IllegalArgumentException {
		if (pinsDown < 0 || pinsDown > GameModel.TOTAL_PINS)
			throw new IllegalArgumentException("The number of pins hit should be int between 0 and " + GameModel.TOTAL_PINS);
		this.pinsDown = pinsDown;
		this.isSpare = isSpare;
		this.isStrike = pinsDown == GameModel.TOTAL_PINS;
	}

	int getPinsDown() {
		return pinsDown;
	}
}
