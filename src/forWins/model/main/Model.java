package forWins.model.main;

public class Model {
	public static final int EMPTY = 0;
	public static final int RED = 1;
	public static final int YELLOW = 2;
	public static final int COLUMNS = 7;
	public static final int ROWS = 6;
	protected int[][] brett = new int[ROWS][COLUMNS];
	// oberste Zeile des Spielfeld hat Index 0!

	private static int currentPlayer = RED;
	private static int winner = 0;
	private static boolean modus = false;   // false is for 1 vs 1 and true is for 1 vs Pc

	public static boolean isModus() {
		return modus;
	}

	public static void setModus(boolean modus) {
		Model.modus = modus;
	}


	public int getWinner() {
		return winner;
	}
	

	private int getPosition(int row, int column) {
		return brett[row][column];
	}

	/**
	 * Set all start in the grid to 0
	 */
	public void newGame() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				brett[i][j] = 0;
			}
		}
		currentPlayer = RED;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void changePlayer() {
		if (currentPlayer == RED) {
			currentPlayer = YELLOW;
		} else {
			currentPlayer = RED;
		}
	}
	
	/**
	 * Return true if it is allowed to put a token in a given column
	 * @param spalte
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	private boolean putAllowed(int spalte) throws IndexOutOfBoundsException {

		return (spalte < COLUMNS && brett[0][spalte] == EMPTY);
	}

	/**
	 * Put the token in the correct row by given column
	 * @param column
	 *            The column in which the token is put
	 * @return the row of the column
	 */
	public int putToken(int column) throws IndexOutOfBoundsException {
		int row = 5; // Counter
		if (putAllowed(column)) {
			while (row >= 0) {
				if (brett[row][column] == 0) {
					brett[row][column] = currentPlayer;
					return row;
				}
				row--;
			}

		}
		return -1;

	}

	/**
	 * Check if all tokens are placed which shows that the game is over with no winner.
	 * @return true if no tokens are left
	 */
	public boolean NoTokensLeft() {
		int count = 0;
		for (int i = 0; i < COLUMNS; i++) {
			if (brett[0][i] != EMPTY) {

				count++;
			}

		}

		return count == 7;
	}
	
	/**
	 * Checks if a player has won.
	 * @return true if player has 4 token in a row, column or vertical
	 */
	public boolean playerHasWon() {
		if (CheckTokensInRow() || CheckTokensInColumn() || CheckTokensDiagonal() || CheckTokensDiagonalRevert()) {
			return true;
		}

		return false;
	}

	private boolean CheckTokensInRow() {

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS - 3; j++) {
				if (brett[i][j] != 0 && brett[i][j] == brett[i][j + 1] && brett[i][j + 1] == brett[i][j + 2]
						&& brett[i][j + 2] == brett[i][j + 3]) {
					winner = brett[i][j + 3];
					return true;
				}

			}

		}

		return false;
	}

	private boolean CheckTokensInColumn() {

		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS - 3; j++) {
				if (brett[j][i] != 0 && brett[j][i] == brett[j + 1][i] && brett[j + 1][i] == brett[j + 2][i]
						&& brett[j + 2][i] == brett[j + 3][i]) {
					winner = brett[j + 3][i];
					return true;
				}

			}

		}

		return false;
	}

	private boolean CheckTokensDiagonal() {

		for (int i = 0; i < 4; i++) { // Column
			for (int j = 5; j >= 3; j--) { // Row
				if (brett[j][i] != 0 && brett[j][i] == brett[j - 1][i + 1] && brett[j - 1][i + 1] == brett[j - 2][i + 2]
						&& brett[j - 2][i + 2] == brett[j - 3][i + 3]) {
					winner = brett[j - 3][i + 3];
					return true;
				}
			}
		}

		return false;
	}

	private boolean CheckTokensDiagonalRevert() {

		for (int i = 6; i >= 3; i--) { // Column
			for (int j = 5; j >= 3; j--) { // Row
				if (brett[j][i] != 0 && brett[j][i] == brett[j - 1][i - 1] && brett[j - 1][i - 1] == brett[j - 2][i - 2]
						&& brett[j - 2][i - 2] == brett[j - 3][i - 3]) {
					winner = brett[j - 3][i - 3];
					return true;
				}
			}
		}

		return false;
	}

	

}
