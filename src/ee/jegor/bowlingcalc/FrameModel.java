package ee.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

/**
 * Bowling frame model class
 *
 * @author Jegor Guzhvin
 */

class FrameModel {

	private final boolean isTenthFrame;
	private final List<RollModel> rolls;
	private int pinsLeftUp;
	private boolean hasStrike;
	private boolean hasSpare;

	FrameModel(boolean isTenthFrame) {
		this.isTenthFrame = isTenthFrame;
		rolls = new ArrayList<>();
		pinsLeftUp = GameModel.TOTAL_PINS;
		hasStrike = false;
		hasSpare = false;
	}

	public List<RollModel> getRolls() {
		return rolls;
	}

	void addBallRoll(int pinsDown) throws IllegalStateException, IllegalArgumentException {

		if (pinsDown > pinsLeftUp && pinsLeftUp < GameModel.TOTAL_PINS)
			throw new IllegalStateException(
					"The number of total pins knocked down in one frame cannot exceed " + GameModel.TOTAL_PINS
			);

		pinsLeftUp -= pinsDown;

		final boolean isSpare = pinsLeftUp == 0 && pinsDown < GameModel.TOTAL_PINS && getRollsCount() == 1;
		final RollModel roll = new RollModel(pinsDown, isSpare);
		rolls.add(roll);

		hasStrike = hasStrike || roll.isStrike;
		hasSpare = hasSpare || isSpare;
		if (pinsLeftUp == 0)
			pinsLeftUp = GameModel.TOTAL_PINS;
	}

	boolean isFinished() {
		boolean isFinished;
		int tenthFrameTotalRollsCount = hasStrike || hasSpare ? 3 : 2;
		if (isTenthFrame)
			isFinished = getRollsCount() >= tenthFrameTotalRollsCount;
		else
			isFinished = hasStrike || getRollsCount() == 2;
		return isFinished;
	}

	public int getRollsCount() {
		return rolls.size();
	}

	boolean hasStrike() {
		return hasStrike;
	}

	boolean hasSpare() {
		return hasSpare;
	}


	int howManyPinsKnockedDownInThisFrame() {
		int pinsDown = 0;
		for (RollModel roll : rolls) {
			pinsDown += roll.getPinsCount();
		}
		return pinsDown;
	}

	/**
	 * @param rollIndex 0 for getting first throw result, 1 - second, etc
	 * @return Number of pins hit as a result of one specific throw specified by rollIndex. null when there is no such throw
	 */
	Integer getOneRollResult(int rollIndex) {
		return getRollsCount() - 1 < rollIndex ? null : this.rolls.get(rollIndex).getPinsCount();
	}

	boolean isTenthFrame() {
		return isTenthFrame;
	}
}
