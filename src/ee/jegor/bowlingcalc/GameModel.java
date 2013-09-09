package ee.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

/**
 * Main bowling score model and control class
 *
 * @author Jegor Guzhvin
 */

class GameModel {

	final static int TOTAL_PINS = 10;
	final static public int MAX_FRAMES = 10;
	private final List<FrameModel> frames;

	GameModel() {
		frames = new ArrayList<>(MAX_FRAMES);
	}

	void addRollResult(int pinsDown) throws IllegalStateException, IllegalArgumentException {
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
				Integer nextTwoRollsResult = getNextTwoRollsResult(frameIndex, 0);
				if (nextTwoRollsResult != null)
					thisFrameScore = TOTAL_PINS + nextTwoRollsResult;
			} else if (thisFrame.hasSpare()) {
				Integer nextRollResult = getNextRollResult(frameIndex, 1);
				if (nextRollResult != null)
					thisFrameScore = TOTAL_PINS + nextRollResult;
			} else {
				thisFrameScore = thisFrame.howManyPinsKnockedDownInThisFrame();
			}
		}
		return thisFrameScore;
	}

	private Integer getNextTwoRollsResult(int currentFrameIndex, int currentRollIndex) {

		final Integer firstRollPinsHit = getNextRollResult(currentFrameIndex, currentRollIndex);
		final Integer secondRollPinsHit = getAfterNextRollResult(currentFrameIndex);

		return (firstRollPinsHit == null || secondRollPinsHit == null) ? null : firstRollPinsHit + secondRollPinsHit;
	}

	private Integer getNextRollResult(int currentFrameIndex, int currentRollIndex) {
		Integer nextRollResult = null;
		FrameModel thisFrame = getFrame(currentFrameIndex);
		if (thisFrame.isTenthFrame()) {
			nextRollResult = thisFrame.getOneRollResult(currentRollIndex + 1);
		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {
			FrameModel nextFrame = getFrame(currentFrameIndex + 1);
			nextRollResult = nextFrame.getOneRollResult(0);
		}
		return nextRollResult;
	}

	private Integer getAfterNextRollResult(int currentFrameIndex) {
		Integer afterNextRollResult = null;
		FrameModel thisFrame = getFrame(currentFrameIndex);
		if (thisFrame.isTenthFrame()) {
			afterNextRollResult = thisFrame.getOneRollResult(2);
		} else if (hasMoreFramesAfterThisOne(currentFrameIndex)) {
			FrameModel nextFrame = getFrame(currentFrameIndex + 1);
			afterNextRollResult = nextFrame.getOneRollResult(1);
			if (afterNextRollResult == null && hasMoreFramesAfterThisOne(currentFrameIndex + 1))
				afterNextRollResult = getFrame(currentFrameIndex + 2).getOneRollResult(0);
		}
		return afterNextRollResult;
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
