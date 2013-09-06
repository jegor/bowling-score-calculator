package com.jegor.bowlingcalc;

class BallRoll {

	private int pinsDown;

	BallRoll(int pinsDown) throws IllegalArgumentException {
		if (pinsDown < 0 || pinsDown > Game.TOTAL_PINS)
			throw new IllegalArgumentException("The number of pins hit should be int between 0 and " + Game.TOTAL_PINS);
		this.pinsDown = pinsDown;
	}

	int getPinsDown() {
		return pinsDown;
	}
}
