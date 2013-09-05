package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class Frame {

	private final boolean isTenthFrame;
	private List<BallThrow> ballThrows;
	private int pinsLeft;
	private boolean isStrike;

	Frame(boolean isTenthFrame) {
		this.isTenthFrame = isTenthFrame;
		ballThrows = new ArrayList<>();
		pinsLeft = BallThrow.MAX_PINS_HIT;
		isStrike = false;
	}

	void addBallThrow(int pinsHit) {
		BallThrow ballThrow = new BallThrow(pinsHit);
		if (pinsHit > pinsLeft)
			throw new IllegalStateException("The number of pins hit in one frame cannot exceed " + BallThrow.MAX_PINS_HIT);
		ballThrows.add(ballThrow);
		pinsLeft = pinsLeft - pinsHit;
		if (pinsLeft == 0 && ballThrows.size() == 1)
			isStrike = true;
		if (isTenthFrame && isStrike) {
			pinsLeft = BallThrow.MAX_PINS_HIT;
		}
	}

	boolean isFinished() {
		boolean isFinished;
		if (!isTenthFrame)
			isFinished = isStrike || ballThrows.size() == 2;
		else
			isFinished = !isStrike && ballThrows.size() == 2 || ballThrows.size() == 3;
		return isFinished;
	}

	boolean isStrike() {
		return isStrike;
	}

	int howManyPinsHit() {
		int pinsHit = 0;
		for (BallThrow ballThrow : ballThrows) {
			pinsHit += ballThrow.getPinsHit();
		}
		return pinsHit;
	}

	/**
	 * @param throwIndex - 0 for getting first throw result, 1 - second, etc
	 * @return
	 */
	Integer getOneThrowPinsHit(int throwIndex) {
		return this.ballThrows.size() - 1 < throwIndex ? null : this.ballThrows.get(throwIndex).getPinsHit();
	}

	boolean isTenthFrame() {
		return isTenthFrame;
	}
}
