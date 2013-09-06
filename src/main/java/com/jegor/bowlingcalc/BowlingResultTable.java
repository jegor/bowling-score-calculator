package com.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

public class BowlingResultTable extends ResultAbstract {

	public final int totalScore;
	public final Boolean gameIsOver;
	public final Integer activeFrameIndex;
	public final List<FrameResult> frameResults = new ArrayList<>(Game.MAX_FRAMES);

	public BowlingResultTable(Game game) {
		totalScore = game.getScore();
		gameIsOver = game.isFinished();
		activeFrameIndex = game.getActiveFrameIndex();
		for (int i = 0; i < game.frameCount(); i++) {
			frameResults.add(i, new FrameResult(game.getFrame(i), game.getFrameScore(i)));
		}
	}

}
