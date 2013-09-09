package ee.jegor.bowlingcalc;

import java.util.ArrayList;
import java.util.List;

/**
 * Bowling result table presentation class
 *
 * @author Jegor Guzhvin
 */

public class BowlingResultTable extends ResultAbstract {

	public final int totalScore;
	public final Boolean gameIsOver;

	/**
	 * Indicates index of active frame (to which the next roll result would go). Is a null when the game is over
	 */
	public final Integer activeFrameIndex;

	/**
	 * List with results of each bowling frame. Doesn't contain elements for nonexistent (future frames)
	 */
	public final List<FrameResult> frameResults = new ArrayList<>(GameModel.MAX_FRAMES);

	BowlingResultTable(GameModel game) {
		totalScore = game.getScore();
		gameIsOver = game.isFinished();
		activeFrameIndex = game.getActiveFrameIndex();
		for (int i = 0; i < game.frameCount(); i++)
			frameResults.add(i, new FrameResult(game.getFrame(i), game.getFrameScore(i)));
	}

}
