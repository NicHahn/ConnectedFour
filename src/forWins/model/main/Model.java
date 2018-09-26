package forWins.model.main;

public class Model {
	public static final int EMPTY = 0;
	public static final int RED = 1;
	public static final int YELLOW = 2;
	public static final int COLUMNS = 7;
	public static final int ROWS = 6;
	private int [] [] brett = new int[ROWS][COLUMNS]; 			
	// oberste Zeile des Spielfeld hat Index 0!
	
	private int  currentPlayer = RED;
	private int winner = 0;
	
	
	
	public int getWinner() {
		return winner;
	}
	
	
	
	private int getPosition(int row, int column) {
		return brett[row][column];
		
	}
	
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void changePlayer() {
		if (currentPlayer == RED) {
			currentPlayer = YELLOW;
		}else {
			currentPlayer = RED;
		}
	}
	
	private boolean putAllowed(int spalte) {
		
		return (spalte < COLUMNS && brett[0][spalte] == EMPTY );
	}
	
	public int putToken(int spalte) {
		int row = 5;		//Counter
		if(putAllowed(spalte)) {
			while(row >= 0){
				if (brett[row][spalte] == 0) {
					brett[row][spalte] = currentPlayer;
					return row;
				}
			row--;
			}
		}	
		return -1;
		
		
	}
	
	public int[][] getBrett() {
		return brett;
	}

	/**
	 * 
	 * @return true if no tokens are left
	 */
	public boolean NoTokensLeft() {
		int count = 0;
		for(int i = 0; i < COLUMNS; i++ ) {
			if(brett[0][i] != EMPTY) {
				
				count++;
			}
			
		}
		
		return count == 7;
	}
	
	public  boolean playerHasWon() {
		if(CheckTokensInRow() || CheckTokensInColumn() || CheckTokensDiagonal() || CheckTokensDiagonalRevert()) {
			return true;
		}
		
		return false;
	}
	
	private  boolean CheckTokensInRow() {
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS - 3; j++) {
				if (brett[i][j] != 0 && brett[i][j] == brett[i][j + 1] && brett[i][j + 1] == brett[i][j + 2] && brett[i][j + 2] == brett[i][j + 3]) {
					winner = brett[i][j + 3];
					return true;
				}
				
			}
		
		}
		
		return false;
	}
	
	private  boolean CheckTokensInColumn() {
		
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS - 3; j++) {
				if (brett[j][i] != 0 && brett[j][i] == brett[j + 1][i] && brett[j + 1][i] == brett[j + 2][i] && brett[j + 2][i] == brett[j + 3][i]) {
					winner = brett[j + 3][i];
					return true;
				}
				
			}
		
		}
		
		return false;
	}
	
	private  boolean CheckTokensDiagonal() {
		
		for (int i = 0; i < 4 ; i++) {   //Column
			for (int j = 5; j >= 3 ; j--) {	//Row
				if (brett[j][i] != 0 && brett[j][i] == brett[j - 1][i+1] && brett[j - 1][i+1] == brett[j - 2][i+2] && brett[j - 2][i+2] == brett[j - 3][i+3]) {
					winner = brett[j - 3][i + 3];
					return true;
				}
			}
		}
		
		return false;
	}
	
	private  boolean CheckTokensDiagonalRevert() {
		
		for (int i = 6; i >= 3; i--) {   //Column
			for (int j = 5; j >= 3 ; j--) {	//Row
				if (brett[j][i] != 0 && brett[j][i] == brett[j - 1][i-1] && brett[j - 1][i-1] == brett[j - 2][i-2] && brett[j - 2][i-2] == brett[j - 3][i-3]) {
					winner = brett[j - 3][i - 3];
					return true;
				}
			}
		}
		
		return false;
	}
	
}
