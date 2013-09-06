package com.jegor.bowlingcalc;

class Roll {

	private final int pinsDown;
	public final boolean isStrike;
	public final boolean isSpare;

	Roll(int pinsDown, boolean isSpare) throws IllegalArgumentException {
		if (pinsDown < 0 || pinsDown > Game.TOTAL_PINS)
			throw new IllegalArgumentException("The number of pins hit should be int between 0 and " + Game.TOTAL_PINS);
		this.pinsDown = pinsDown;
		this.isSpare = isSpare;
		this.isStrike = pinsDown == Game.TOTAL_PINS;
	}

	int getPinsDown() {
		return pinsDown;
	}
}
