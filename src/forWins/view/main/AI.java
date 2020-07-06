package forWins.view.main;

import forWins.model.main.Model;

public class AI extends Model {
	
//	private Model model;
//	
//	public AI(Model model) {
//		this.model = model;
//	}
	
	public boolean checkForLose() {
		boolean check = false;
		for (int i = COLUMNS - 4; i <= COLUMNS - 2; i--) {
			for (int j = ROWS - 3; j < ROWS - 2; j--) {
				check =  checkCorners(j, i);

			}
			
		
		}
		return check;
	}

	private boolean checkCorners(int i, int j) {
		boolean check = false;
		if (brett[j][i] == brett[j + 1][i + 1]) {
			if (brett[j + 1][i + 1] == brett[j + 2][i + 2] && brett[j + 2][i + 2] == 0) {
				check = true;
			}
		} else if (brett[j][i] == brett[j - 1][i + 1]) {
			if (brett[j - 1][i + 1] == brett[j - 2][i + 2] && brett[j - 2][i + 2] == 0) {
				check = true;
			}
		} else if (brett[j][i] == brett[j + 1][i - 1]) {
			if (brett[j + 1][i - 1] == brett[j + 2][i - 2] && brett[j + 2][i - 2] == 0) {
				check = true;
			}
		} else if (brett[j][i] == brett[j - 1][i - 1]) {
			if (brett[j - 1][i - 1] == brett[j - 2][i - 2] && brett[j - 2][i - 2] == 0) {
				check = true;
			}
		}
		return check;
		
	}
	
	private boolean checkHorizontal() {
		for (int i = 0; i < COLUMNS ; i++) {
			for (int j = 0; j <= ROWS - 3; j++) {
				if (brett[j][i] != 0 && brett[j][i] == brett[j + 1][i] && brett[j + 1][i] == brett[j + 2][i] && brett[j + 3][i] == 0) {
					return true;
				} 

			}
		}
		return false;
		
	}
	
	private boolean checkVertical() {
		for (int i = 0; i < ROWS ; i++) {
			for (int j = 0; j <= COLUMNS - 3; j++) {
				if (brett[i][j] != 0 && brett[i][j] == brett[i][j + 1] && brett[i][j + 1] == brett[i][j + 2] && brett[i][j + 3] == 0) {
					return true;
				} 

			}
		}
		return false;
		
	}
}
