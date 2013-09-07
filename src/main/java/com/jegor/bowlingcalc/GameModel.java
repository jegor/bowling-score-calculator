/**
 * Main bowling score model and control class
 *
 * @author Jegor Guzhvin
 */
package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

class GameModel {

	final static int TOTAL_PINS = 10;
	final static public int MAX_FRAMES = 10;
	private final List<FrameModel> frames;

	GameModel() {
		frames = new ArrayList<>(MAX_FRAMES);
	}

	void addBallRollResult(int pinsDown) throws IllegalStateException, IllegalArgumentException {
		if (isFinished())
			throw new IllegalStateException("The game is over. Please start the new one.");

		loadActiveFrame().addBallRoll(pinsDown);
	}

	public int getScore() {
		int score = 0;
		for (int i = 0; i < frameCount(); i++) {
			Integer frameScore = getFrameScore(i);
			if (frameScore != null)
				score += frameScore;
		}
		return score;
	}

	public boolean isFinished() {
		return frameCount() == MAX_FRAMES && getLastFrame().isFinished();
	}

	public Integer getActiveFrameIndex() {
		if (isFinished())
			return null;
		loadActiveFrame();
		return frameCount() - 1;
	}

	private FrameModel loadActiveFrame() {
		if (frames.isEmpty() || getLastFrame().isFinished())
			startNewFrame();
		return getLastFrame();
	}

	public Integer getFrameScore(int frameIndex) {
		Integer thisFrameScore = null;
		final FrameModel thisFrame = getFrame(frameIndex);
		if (thisFrame.isFinished()) {
			if (thisFrame.hasStrike()) {
				Integer pinsDownInTwoNextRolls = pinsDownInTwoNextRolls(frameIndex, 0);
				if (pinsDownInTwoNextRolls != null)
					thisFrameScore = TOTAL_PINS + pinsDownInTwoNextRolls;
			} else if (thisFrame.hasSpare()) {
				Integer pinsDownInNextRoll = pinsDownInNextRollAfterCurrent(frameIndex, 1);
				if (pinsDownInNextRoll != null)
					thisFrameScore = TOTAL_PINS + pinsDownInNextRoll;
			} else {
				thisFrameScore = thisFrame.howManyPinsKnockedDown();
			}
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
		FrameModel thisFrame = getFrame(currentFrameIndex);
		if (thisFrame.isTenthFrame()) {
			nextRollPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(currentRollIndex + 1);
		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {
			FrameModel nextFrame = getFrame(currentFrameIndex + 1);
			nextRollPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(0);
		}
		return nextRollPinsHit;
	}

	private Integer pinsDownInTheSecondNextRollAfterCurrent(int currentFrameIndex) {
		Integer secondRollPinsHit = null;
		FrameModel thisFrame = getFrame(currentFrameIndex);
		if (thisFrame.isTenthFrame()) {
			secondRollPinsHit = thisFrame.getNumberOfPinsKnockedDownInOneRoll(2);
		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {
			FrameModel nextFrame = getFrame(currentFrameIndex + 1);
			secondRollPinsHit = nextFrame.getNumberOfPinsKnockedDownInOneRoll(1);
			if (secondRollPinsHit == null && hasMoreFramesAfterThisOne(currentFrameIndex + 1))
				secondRollPinsHit = getFrame(currentFrameIndex + 2).getNumberOfPinsKnockedDownInOneRoll(0);
		}
		return secondRollPinsHit;
	}

	public int frameCount() {
		return frames.size();
	}

	public FrameModel getFrame(int frameIndex) {
		return frames.get(frameIndex);
	}

	private boolean hasMoreFramesAfterThisOne(int currentFrameIndex) {
		return currentFrameIndex < frameCount() - 1;
	}

	private void startNewFrame() {
		final boolean isTenthFrame = frameCount() == MAX_FRAMES - 1;
		frames.add(new FrameModel(isTenthFrame));
	}

	private FrameModel getLastFrame() {
		return getFrame(frameCount() - 1);
	}

}
