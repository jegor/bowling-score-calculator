package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class Game {

	private final List<Frame> frames;
	final public static int MAX_FRAMES = 10;

	Game() {
		frames = new ArrayList<>();
	}

	public int getScore() {
		int score = 0;
		for (int currentFrameIndex = 0; currentFrameIndex < frames.size(); currentFrameIndex++) {
			final Frame thisFrame = frames.get(currentFrameIndex);
			if (!thisFrame.isFinished())
				break;

			final int currentFramePinsHit = thisFrame.howManyPinsHit();
			if (thisFrame.isStrike()) {
				Integer pinsHitInTwoNextThrows = pinsHitInTwoNextThrows(currentFrameIndex);
				if (pinsHitInTwoNextThrows != null)
					score += BallThrow.MAX_PINS_HIT + pinsHitInTwoNextThrows;
			} else {
				score += currentFramePinsHit;
			}
		}
		return score;
	}


	private Integer pinsHitInTwoNextThrows(int currentFrameIndex) {
		Integer firstThrowPinsHit = null;
		Integer secondThrowPinsHit = null;
		Frame thisFrame = frames.get(currentFrameIndex);

		if (thisFrame.isTenthFrame()) {

			firstThrowPinsHit = thisFrame.getOneThrowPinsHit(1);
			secondThrowPinsHit = thisFrame.getOneThrowPinsHit(2);

		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {

			Frame nextFrame = frames.get(currentFrameIndex + 1);
			firstThrowPinsHit = nextFrame.getOneThrowPinsHit(0);
			secondThrowPinsHit = nextFrame.getOneThrowPinsHit(1);

			if (secondThrowPinsHit == null && hasMoreFramesAfterThisOne(currentFrameIndex + 1)) {
				secondThrowPinsHit = frames.get(currentFrameIndex + 2).getOneThrowPinsHit(0);
			}
		}
		if (firstThrowPinsHit == null || secondThrowPinsHit == null)
			return null;
		return firstThrowPinsHit + secondThrowPinsHit;

	}

	private boolean hasMoreFramesAfterThisOne(int strikeFrameIndex) {
		return strikeFrameIndex < frames.size() - 1;
	}

	public void addThrowResult(int pinsHit)
			throws IllegalStateException, IllegalArgumentException {
		if (isFinished())
			throw new IllegalStateException("The game is over. Please start the new one.");
		else
			loadActiveFrame().addBallThrow(pinsHit);

	}

	public boolean isFinished() {
		return frames.size() == MAX_FRAMES && frames.get(frames.size() - 1).isFinished();
	}

	private Frame loadActiveFrame() {
		if (frames.isEmpty() || frames.get(frames.size() - 1).isFinished()) {
			final boolean isTenthFrame = frames.size() == MAX_FRAMES - 1;
			frames.add(new Frame(isTenthFrame));
		}
		return frames.get(frames.size() - 1);
	}


}
