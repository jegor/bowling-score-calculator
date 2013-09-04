package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Integer score;
	private List<Frame> frames;

	public Game() {
		score = 0;
		frames = new ArrayList<>();
	}

	public Integer getScore() {
		return score;
	}

	public void addThrow(Integer pinsHit) {
		loadActiveFrame().addBallThrow(pinsHit);
		score = score + pinsHit;
	}

	private Frame loadActiveFrame() {
		if (frames.isEmpty() || frames.get(frames.size() - 1).isClosed())
			frames.add(new Frame(false));
		return frames.get(frames.size() - 1);
	}

}
