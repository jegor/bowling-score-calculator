package com.jegor.bowlingcalc;

class BallThrow {

	private int pinsHit;
	public static final int MIN_PINS_HIT = 0;
	public static final int MAX_PINS_HIT = 10;

	BallThrow(int pinsHit) {
		if (pinsHit < MIN_PINS_HIT || pinsHit > MAX_PINS_HIT)
			throw new IllegalArgumentException("The number of pins hit should be int between " + MIN_PINS_HIT + " and " + MAX_PINS_HIT);
		this.pinsHit = pinsHit;
	}

	int getPinsHit() {
		return pinsHit;
	}
}
