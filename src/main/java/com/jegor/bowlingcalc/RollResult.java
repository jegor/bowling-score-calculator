package com.jegor.bowlingcalc;

public class RollResult extends ResultAbstract {

	public final String result;

	RollResult(RollModel roll) {
		if (roll.isStrike)
			result = "X";
		else if (roll.isSpare)
			result = "/";
		else
			result = String.valueOf(roll.getPinsDown());
	}


}
