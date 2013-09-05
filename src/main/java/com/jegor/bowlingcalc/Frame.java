package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class Frame {

	public int score;
	private final boolean isLastInGame;

	List<BallThrow> getBallThrows() {
		return ballThrows;
	}

	private List<BallThrow> ballThrows;
	private int pinsLeft;
	private boolean isStrike;

	Frame(boolean isLastInGame) {
		this.isLastInGame = isLastInGame;
		score = 0;
		ballThrows = new ArrayList<>();
		pinsLeft = BallThrow.maxPinsHit;
		isStrike = false;
	}

	public void addBallThrow(int pinsHit) {
		BallThrow ballThrow = new BallThrow(pinsHit);
		if (pinsHit > pinsLeft)
			throw new IllegalStateException("The number of pins hit in one frame cannot exceed " + BallThrow.maxPinsHit);
		ballThrows.add(ballThrow);
		pinsLeft = pinsLeft - pinsHit;
		if (pinsLeft == 0 && ballThrows.size() == 1)
			isStrike = true;
	}

	public boolean isFinished() {
		return isStrike || ballThrows.size() >= 3 || !isLastInGame && ballThrows.size() == 2;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public int howManyPinsHit() {
		int pinsHit = 0;
		for (BallThrow ballThrow : ballThrows) {
			pinsHit += ballThrow.getPinsHit();
		}
		return pinsHit;
	}
}
