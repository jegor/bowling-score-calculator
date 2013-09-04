package com.jegor.bowlingcalc;

class BallThrow {

	public Integer pinsHit;
	public static final int minPinsHit = 0;
	public static final int maxPinsHit = 10;

	BallThrow(Integer pinsHit) {
		if (pinsHit == null || pinsHit < minPinsHit || pinsHit > maxPinsHit)
			throw new IllegalArgumentException("The number of pins hit should be int between " + minPinsHit + " and " + maxPinsHit);
		this.pinsHit = pinsHit;
	}
}
