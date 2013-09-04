package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class Frame {

	public Integer score;
	private final Boolean isLastInGame;
	private List<BallThrow> ballThrows;
	private Integer pinsLeft;

	Frame(Boolean isLastInGame) {
		this.isLastInGame = isLastInGame;
		score = 0;
		ballThrows = new ArrayList<>();
		pinsLeft = BallThrow.maxPinsHit;
	}

	public void addBallThrow(Integer pinsHit) {
		BallThrow ballThrow = new BallThrow(pinsHit);
		if (pinsHit > pinsLeft)
			throw new IllegalStateException("The number of pins hit in one frame cannot exceed " + BallThrow.maxPinsHit);
		ballThrows.add(ballThrow);
		pinsLeft = pinsLeft - pinsHit;

	}

	public Boolean isClosed() {
		return ballThrows.size() >= 3 || !isLastInGame && ballThrows.size() == 2;
	}
}
