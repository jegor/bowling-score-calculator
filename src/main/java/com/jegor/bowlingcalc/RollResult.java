package com.jegor.bowlingcalc;

class RollResult extends ResultAbstract {

	public final String result;

	public RollResult(Roll roll) {
		if (roll.isStrike)
			result = "X";
		else if (roll.isSpare)
			result = "/";
		else
			result = String.valueOf(roll.getPinsDown());
	}


}
