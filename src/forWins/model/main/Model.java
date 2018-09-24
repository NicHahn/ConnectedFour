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
	
	public Model() {
		
	}
	
	public int getWinner() {
		return winner;
	}


//	public static void main(String[] args) {
//		Model model = new Model();
//		//model.brett[5][0] = RED;
//		System.out.println(model.brett[5][0]);	
//		//System.out.println(model.putAllowed(0));	
//		System.out.println(model.putToken(0));	
//		System.out.println(model.getCurrentPlayer());
//		System.out.println(model.putToken(0));	
//		System.out.println(model.getCurrentPlayer());
//		System.out.println(model.brett[4][0]);	
//		
//		
//	}
	
	
	
	private int getPosition(int row, int column) {
		return brett[row][column];
		
	}
	
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	private void changePlayer() {
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
					changePlayer();
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
