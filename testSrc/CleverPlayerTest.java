import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * <B>Tests for the CleverPlayer Class,</B>
 * featured in Exercise 2 of the new "Introduction to OOP" course,
 * HUJI, Winter 2021-2022 Semester.
 *
 * @author Erel Debel.
 */
class CleverPlayerTest {
	/**
	 * number of games to play times 100.
	 */
	public static final int GAMES = 100;

	/**
	 * distribution wiggle room.
	 */
	public static final int EPSILON = 0;

	/**
	 * Checks that the CleverPlayer beats the WhateverPlayer by at least the requested distribution - EPSILON.
	 * <p>
	 * It is recommended you temporarily remove the 'final' keyword from SIZE and WIN_STREAK and than
	 * uncomment the 4 commented out lines for a full check of all scenarios.
	 * <p>
	 * if not the check will be done only for your current SIZE and WIN_STREAK constants.
	 * <p>
	 * NOTICE:
	 * This test relies on probability, so you might fail it once in a few tries even if your code is correct.
	 */
	@Test
	void checkAllWinDistribution(){
		int initialSize = Board.SIZE, initialStreak = Board.WIN_STREAK;
		for (int streak = 3; streak < 8; ++streak) {
			for (int size = 3; size < 8; ++size) {
				if (streak > size) {
					continue;
				}
//				Board.WIN_STREAK = streak;  // TODO uncomment
//				Board.SIZE = size; // TODO uncomment
				checkWinDistribution();
			}
		}
//		Board.WIN_STREAK = initialStreak; // TODO uncomment
//		Board.SIZE = initialSize; // TODO uncomment
	}

	/**
	 * Checks that the CleverPlayer beats the Whatever player by at least the requested distribution - EPSILON.
	 * The check is done only for your current SIZE and WIN_STREAK constants.
	 * <p>
	 * NOTICE:
	 * This test relies on probability, so you might fail it once in a few tries even if your code is correct.
	 */
	private void checkWinDistribution() {
		int distributionTarget = currentDistributionTarget();
		Tournament tournament = new Tournament(
				GAMES * 100,
				new VoidRenderer(),
				new Player[]{ new CleverPlayer(), new WhateverPlayer()}
		);
		var results = tournament.playTournament();
		assert (results[0] > GAMES * distributionTarget - EPSILON);
	}

	private static int currentDistributionTarget() {
		int sizeStreakRatio = Board.SIZE - Board.WIN_STREAK;
		if (sizeStreakRatio < 2) {
			return 60;
		}
		if (sizeStreakRatio == 2) {
			return 90;
		}
		if (sizeStreakRatio == 3) {
			return 95;
		}
		if (sizeStreakRatio == 4) {
			return 99;
		}
		fail("current SIZE/WIN_STREAK ratio not expected.");
		return 0;
	}
}