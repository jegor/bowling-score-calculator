package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private List<Frame> frames;

	public Game() {
		frames = new ArrayList<>();
	}

	public int getScore() {
		int score = 0;
		for (int i = 0; i < frames.size(); i++) {
			final Frame thisFrame = frames.get(i);
			if (!thisFrame.isFinished())
				break;

			final int currentFramePinsHit = thisFrame.howManyPinsHit();
			if (thisFrame.isStrike()) {
				Integer pinsHitInTwoNextThrows = pinsHitInTwoNextThrows(i);
				if (pinsHitInTwoNextThrows != null) {
					score += currentFramePinsHit + pinsHitInTwoNextThrows;
				}
			} else {
				score += currentFramePinsHit;
			}
		}
		return score;
	}

	private Integer pinsHitInFrameFirstThrow(int currentFrameIndex) {
		Frame frame = frames.get(currentFrameIndex);
		return frame.getBallThrows().size() < 1 ? null : frame.getBallThrows().get(0).getPinsHit();
	}

	private Integer pinsHitInTwoNextThrows(int currentFrameIndex) {

		if (isLastFrame(currentFrameIndex))
			return null;

		Integer firstThrowPinsHit = pinsHitInFrameFirstThrow(currentFrameIndex + 1);
		if (firstThrowPinsHit == null)
			return null;

		Integer secondThrowPinsHit = null;
		Frame nextFrame = frames.get(currentFrameIndex + 1);
		if (nextFrame.getBallThrows().size() >= 2)
			secondThrowPinsHit = nextFrame.getBallThrows().get(1).getPinsHit();
		else if (!isLastFrame(currentFrameIndex + 1))
			secondThrowPinsHit = pinsHitInFrameFirstThrow(currentFrameIndex + 2);

		if (secondThrowPinsHit == null)
			return null;

		return firstThrowPinsHit + secondThrowPinsHit;


	}

	private boolean isLastFrame(int strikeFrameIndex) {
		return strikeFrameIndex == frames.size() - 1;
	}

	public void addThrow(int pinsHit) {
		loadActiveFrame().addBallThrow(pinsHit);
	}

	private Frame loadActiveFrame() {
		if (frames.isEmpty() || frames.get(frames.size() - 1).isFinished())
			frames.add(new Frame(false));
		return frames.get(frames.size() - 1);
	}

}
