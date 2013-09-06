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

	void addBallRollResult(int pinsDown) throws IllegalStateException, IllegalArgumentException {
		if (isFinished())
			throw new IllegalStateException("The game is over. Please start the new one.");

		loadActiveFrame().addBallRoll(pinsDown);
	}

	int getScore() {
		int score = 0;
		for (int i = 0; i < frameCount(); i++)
			score += getFrameScore(i);
		return score;
	}

	boolean isFinished() {
		return frameCount() == MAX_FRAMES && getLastFrame().isFinished();
	}

	private Frame loadActiveFrame() {
		if (frames.isEmpty() || getLastFrame().isFinished())
			startNewFrame();
		return getLastFrame();
	}

	private int getFrameScore(int frameIndex) {
		int thisFrameScore = 0;
		final Frame thisFrame = getFrame(frameIndex);
		if (thisFrame.isFinished()) {
			if (thisFrame.isStrike()) {
				Integer pinsDownInTwoNextRolls = pinsDownInTwoNextRolls(frameIndex, 0);
				if (pinsDownInTwoNextRolls != null)
					thisFrameScore = TOTAL_PINS + pinsDownInTwoNextRolls;
			} else if (thisFrame.isSpare()) {
				Integer pinsDownInNextRoll = pinsDownInNextRollAfterCurrent(frameIndex, 1);
				if (pinsDownInNextRoll != null)
					thisFrameScore = TOTAL_PINS + pinsDownInNextRoll;
			} else {
				thisFrameScore = thisFrame.howManyPinsKnockedDown();
			}
		} else {
			thisFrameScore = 0;
		}
		return thisFrameScore;
	}

	private Integer pinsDownInTwoNextRolls(int currentFrameIndex, int currentRollIndex) {

		final Integer firstRollPinsHit = pinsDownInNextRollAfterCurrent(currentFrameIndex, currentRollIndex);
		final Integer secondRollPinsHit = pinsDownInTheSecondNextRollAfterCurrent(currentFrameIndex);

		return (firstRollPinsHit == null || secondRollPinsHit == null) ? null : firstRollPinsHit + secondRollPinsHit;
	}

	private Integer pinsDownInNextRollAfterCurrent(int currentFrameIndex, int currentRollIndex) {
		Integer nextRollPinsHit = null;
		Frame thisFrame = getFrame(currentFrameIndex);
		if (thisFrame.isTenthFrame()) {
			nextRollPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(currentRollIndex + 1);
		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {
			Frame nextFrame = getFrame(currentFrameIndex + 1);
			nextRollPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(0);
		}
		return nextRollPinsHit;
	}

	private Integer pinsDownInTheSecondNextRollAfterCurrent(int currentFrameIndex) {
		Integer secondRollPinsHit = null;
		Frame thisFrame = getFrame(currentFrameIndex);
		if (thisFrame.isTenthFrame()) {
			secondRollPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(2);
		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {
			Frame nextFrame = getFrame(currentFrameIndex + 1);
			secondRollPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(1);
			if (secondRollPinsHit == null && hasMoreFramesAfterThisOne(currentFrameIndex + 1))
				secondRollPinsHit = getFrame(currentFrameIndex + 2).getNumberOfPinsKnockedDownInOneRoll(0);
		}
		return secondRollPinsHit;
	}

	private int frameCount() {
		return frames.size();
	}

	private Frame getFrame(int frameIndex) {
		return frames.get(frameIndex);
	}

	private boolean hasMoreFramesAfterThisOne(int currentFrameIndex) {
		return currentFrameIndex < frameCount() - 1;
	}

	private void startNewFrame() {
		final boolean isTenthFrame = frameCount() == MAX_FRAMES - 1;
		frames.add(new Frame(isTenthFrame));
	}

	private Frame getLastFrame() {
		return getFrame(frameCount() - 1);
	}

}
