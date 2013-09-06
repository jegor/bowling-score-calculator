package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class Game {

	final static int TOTAL_PINS = 10;
	final static private int MAX_FRAMES = 10;
	private final List<Frame> frames;

	Game() {
		frames = new ArrayList<>();
	}

	int getScore() {
		int score = 0;
		for (int currentFrameIndex = 0; currentFrameIndex < frameCount(); currentFrameIndex++)
			score += getFrameScore(currentFrameIndex);
		return score;
	}

	void addBallRollResult(int pinsDown)
			throws IllegalStateException, IllegalArgumentException {
		if (isFinished())
			throw new IllegalStateException("The game is over. Please start the new one.");
		else
			loadActiveFrame().addBallRoll(pinsDown);

	}

	boolean isFinished() {
		return frameCount() == MAX_FRAMES && getLastFrame().isFinished();
	}

	private int frameCount() {
		return frames.size();
	}

	private Integer pinsDownInTwoNextThrows(int currentFrameIndex) {
		Integer firstThrowPinsHit = null;
		Integer secondThrowPinsHit = null;
		Frame thisFrame = frames.get(currentFrameIndex);

		if (thisFrame.isTenthFrame()) {

			firstThrowPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(1);
			secondThrowPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(2);

		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {

			Frame nextFrame = frames.get(currentFrameIndex + 1);
			firstThrowPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(0);
			secondThrowPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(1);

			if (secondThrowPinsHit == null && hasMoreFramesAfterThisOne(currentFrameIndex + 1)) {
				secondThrowPinsHit = frames.get(currentFrameIndex + 2).getNumberOfPinsKnockedDownInOneRoll(0);
			}
		}
		if (firstThrowPinsHit == null || secondThrowPinsHit == null)
			return null;
		return firstThrowPinsHit + secondThrowPinsHit;

	}

	private boolean hasMoreFramesAfterThisOne(int currentFrameIndex) {
		return currentFrameIndex < frameCount() - 1;
	}

	private Frame loadActiveFrame() {
		if (frames.isEmpty() || getLastFrame().isFinished()) {
			final boolean isTenthFrame = frameCount() == MAX_FRAMES - 1;
			frames.add(new Frame(isTenthFrame));
		}
		return getLastFrame();
	}

	private Frame getLastFrame() {
		return frames.get(frameCount() - 1);
	}

	private int getFrameScore(int frameIndex) {
		int thisFrameScore = 0;
		final Frame thisFrame = frames.get(frameIndex);
		if (thisFrame.isFinished()) {
			final int currentFramePinsKnockedDown = thisFrame.howManyPinsKnockedDown();
			if (thisFrame.isStrike()) {
				Integer pinsDownInTwoNextThrows = pinsDownInTwoNextThrows(frameIndex);
				if (pinsDownInTwoNextThrows != null)
					thisFrameScore = TOTAL_PINS + pinsDownInTwoNextThrows;
			} else {
				thisFrameScore = currentFramePinsKnockedDown;
			}
		} else {
			thisFrameScore = 0;
		}
		return thisFrameScore;
	}

}
