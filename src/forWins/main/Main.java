package forWins.main;

import forWins.controll.main.Controll;
import forWins.model.main.Model;
import forWins.view.main.MainView;

public class Main {
	
	
	public static void main(String[] args) {
		Model model = new Model();
		
		MainView view = new MainView();
		view.main(args);
		view.setModel(model);
		//Controll controll = new Controll(model,view);

	}

}
