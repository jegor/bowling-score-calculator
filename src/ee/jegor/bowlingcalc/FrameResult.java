package ee.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

public class FrameResult extends ResultAbstract {

	public final Integer frameScore;
	public final List<RollResult> rollResults = new ArrayList<>();

	public FrameResult(FrameModel frame, Integer frameScore) {
		this.frameScore = frameScore;
		for (int i = 0; i < frame.getRollsCount(); i++) {
			rollResults.add(i, new RollResult(frame.getRolls().get(i)));
		}
	}
}
