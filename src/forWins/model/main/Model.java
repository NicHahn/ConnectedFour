package forWins.model.main;

public class Model {
	public static final int EMPTY = 0;
	public static final int PLAYER1 = 1;
	public static final int PLAYER2 = 2;
	public static final int COLUMNS = 7;
	public static final int ROWS = 6;
	private static int [] [] brett = new int[ROWS][COLUMNS]; 			
	// oberste Zeile des Spielfeld hat Index 0!
	
	private static int  currentPlayer = PLAYER1;
	private int winner = 0;
	
	public Model() {
		
	}
	
	public int getWinner() {
		return winner;
	}


//	public static void main(String[] args) {
//		System.out.println(brett[0][3]);	
//		brett[0][0] = PLAYER2;
//		brett[0][1] = PLAYER2;
//		brett[0][2] = PLAYER2;
//		brett[0][3] = PLAYER2;
//		brett[0][4] = PLAYER2;
//		brett[0][5] = PLAYER2;
//		brett[0][6] = PLAYER2;
//		
//	}
	
	
	
	private int getPosition(int row, int column) {
		return brett[row][column];
		
	}
	
	
	private void changePlayer() {
		if (currentPlayer == 1) {
			currentPlayer = PLAYER2;
		}else {
			currentPlayer = PLAYER1;
		}
	}
	
	private boolean putAllowed(int spalte) {
		
		return (spalte < COLUMNS && brett[0][spalte] == EMPTY );
	}
	
	public void putToken(int spalte) {
		if(putAllowed(spalte)) {
			int row = 5;		//Counter
			for(int i = 5; i >=0 ; i--) {
				if(brett[i][spalte] != EMPTY) {
					row--;
				}
				
			}
			
			brett[row][spalte] = currentPlayer;
			changePlayer();
		}else{System.out.println("Die spalte ist schon voll");}
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
		if(CheckTokensInRow() | CheckTokensInColumn() | CheckTokensDiagonal() | CheckTokensDiagonalRevert()) {
			return true;
		}
		
		return false;
	}
	
	private  boolean CheckTokensInRow() {
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS - 3; j++) {
				if (brett[j][i] != 0 && brett[j][i] == brett[j][i + 1] && brett[1][i + 1] == brett[j][i + 2] && brett[j][i + 2] == brett[j][i + 3]) {
					winner = brett[j][i + 3];
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
		
		for (int i = 3; i >= 0; i--) {   //Column
			for (int j = 5; j >= 3 ; j--) {	//Row
				if (brett[j][i] != 0 && brett[j][i] == brett[j + 1][i+1] && brett[j + 1][i+1] == brett[j + 2][i+2] && brett[j + 2][i+2] == brett[j + 3][i+3]) {
					winner = brett[j + 3][i + 3];
					return true;
				}
			}
		}
		
		return false;
	}
	
	private  boolean CheckTokensDiagonalRevert() {
		
		for (int i = 6; i >= 3; i--) {   //Column
			for (int j = 5; j >= 3 ; j--) {	//Row
				if (brett[j][i] != 0 && brett[j][i] == brett[j + 1][i-1] && brett[j + 1][i-1] == brett[j + 2][i-2] && brett[j + 2][i-2] == brett[j + 3][i-3]) {
					winner = brett[j + 3][i - 3];
					return true;
				}
			}
		}
		
		return false;
	}
	
}
