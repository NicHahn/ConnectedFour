package forWins.view.main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Text text = new Text(300, 300, "Hallo du ");
		text.setFont(new Font(50));
		
		Line line1 = new Line(600, 200, 600, 1400);
		line1.setStrokeWidth(12);
		line1.setStroke(Color.GRAY);
		
		Line line2 = new Line(600, 1400, 2000, 1400);
		line2.setStrokeWidth(12);
		line2.setStroke(Color.GRAY);
		
		Line line3 = new Line(2000, 200, 2000, 1400);
		line3.setStrokeWidth(12);
		line3.setStroke(Color.GRAY);
		
		Line line4 = new Line(800, 200, 800, 1400);
		Line line5 = new Line(1000, 200, 1000, 1400);
		Line line6 = new Line(1200, 200, 1200, 1400);
		Line line7 = new Line(1400, 200, 1400, 1400);
		Line line8 = new Line(1600, 200, 1600, 1400);
		Line line9 = new Line(1800, 200, 1800, 1400);
		
		Line line12 = new Line(600, 400, 2000, 400);
		Line line13 = new Line(600, 600, 2000, 600);
		Line line14 = new Line(600, 800, 2000, 800);
		Line line15 = new Line(600, 1000, 2000, 1000);
		Line line16 = new Line(600, 1000, 2000, 1000);
		Line line17 = new Line(600, 1200, 2000, 1200);
		
		
		ObservableList list = root.getChildren();
		list.addAll(text,line1, line2, line4, line5, line6, line7, line8, line9, line12, line13, line3, line14, line15, line16, line17);
		
		
		
		
		
		
		
		
		Scene scene = new Scene(root, 600, 600);
		stage.setScene(scene);
		stage.setTitle("ForWins");
		stage.setFullScreen(true);
		stage.show();
			
		
	}

}
