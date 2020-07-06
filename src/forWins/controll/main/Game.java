package forWins.controll.main;

import forWins.view.main.View;

public class Game implements Runnable {
	private static View view = null;
	public enum Spielmodus {
		
		ONEVSONE, ONEVSComputer

	}
	private Spielmodus spielModus = Spielmodus.ONEVSONE;

	public static void main(String[] args) {
		Game game = new Game();
		view = new View();
		view.main(args);
		new Thread(game).start();

	}

	@Override
	public void run() {
		boolean isRunning = true;
		
		while(isRunning) {
			
			switch(spielModus) 
			{
				case(ONEVSONE):
					
				case(oneVsComputer):
					
			}
			
		}
	}
}
