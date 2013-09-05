package com.jegor.bowlingcalc;

class BallThrow {

	private int pinsHit;
	public static final int MAX_PINS_HIT = 10;

	BallThrow(int pinsHit) throws IllegalArgumentException {
		if (pinsHit < 0 || pinsHit > MAX_PINS_HIT)
			throw new IllegalArgumentException("The number of pins hit should be int between 0 and " + MAX_PINS_HIT);
		this.pinsHit = pinsHit;
	}

	int getPinsHit() {
		return pinsHit;
	}
}
