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
		for (int i = 0; i < frameCount(); i++)
			score += getFrameScore(i);
		return score;
	}

	void addBallRollResult(int pinsDown) throws IllegalStateException, IllegalArgumentException {
		if (isFinished())
			throw new IllegalStateException("The game is over. Please start the new one.");

		loadActiveFrame().addBallRoll(pinsDown);

	}

	boolean isFinished() {
		return frameCount() == MAX_FRAMES && getLastFrame().isFinished();
	}

	private int frameCount() {
		return frames.size();
	}

	private int pinsDownInNextRoll(int currentFrameIndex) {
		Integer nextRollPinsHit = null;
		Frame thisFrame = frames.get(currentFrameIndex);
		if (thisFrame.isTenthFrame()) {
			nextRollPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(2);
		} else {
			Frame nextFrame = frames.get(currentFrameIndex + 1);
			nextRollPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(0);
		}
		return nextRollPinsHit;
	}

	private Integer pinsDownInTwoNextRolls(int currentFrameIndex) {
		Integer firstRollPinsHit = null;
		Integer secondRollPinsHit = null;
		Frame thisFrame = frames.get(currentFrameIndex);

		if (thisFrame.isTenthFrame()) {

			firstRollPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(1);
			secondRollPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(2);

		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {

			Frame nextFrame = frames.get(currentFrameIndex + 1);
			firstRollPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(0);
			secondRollPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(1);
			if (secondRollPinsHit == null && hasMoreFramesAfterThisOne(currentFrameIndex + 1))
				secondRollPinsHit = frames.get(currentFrameIndex + 2).getNumberOfPinsKnockedDownInOneRoll(0);

		}

		if (firstRollPinsHit == null || secondRollPinsHit == null)
			return null;

		return firstRollPinsHit + secondRollPinsHit;
	}

	private boolean hasMoreFramesAfterThisOne(int currentFrameIndex) {
		return currentFrameIndex < frameCount() - 1;
	}

	private Frame loadActiveFrame() {
		if (frames.isEmpty() || getLastFrame().isFinished())
			startNewFrame();
		return getLastFrame();
	}

	private void startNewFrame() {
		final boolean isTenthFrame = frameCount() == MAX_FRAMES - 1;
		frames.add(new Frame(isTenthFrame));
	}

	private Frame getLastFrame() {
		return frames.get(frameCount() - 1);
	}

	private int getFrameScore(int frameIndex) {
		int thisFrameScore = 0;
		final Frame thisFrame = frames.get(frameIndex);
		if (thisFrame.isFinished()) {
			if (thisFrame.isStrike()) {
				Integer pinsDownInTwoNextThrows = pinsDownInTwoNextRolls(frameIndex);
				if (pinsDownInTwoNextThrows != null)
					thisFrameScore = TOTAL_PINS + pinsDownInTwoNextThrows;
			} else if (thisFrame.isSpare()) {
				thisFrameScore = TOTAL_PINS + pinsDownInNextRoll(frameIndex);
			} else {
				thisFrameScore = thisFrame.howManyPinsKnockedDown();
			}
		} else {
			thisFrameScore = 0;
		}
		return thisFrameScore;
	}


}
