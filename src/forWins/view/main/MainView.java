package forWins.view.main;



import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.List;

import forWins.model.main.Model;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView extends Application{
	private static final int TITLE_SIZE = 80;
	private static final int COLUMNs = 7;
	private static final int ROWS = 6;
	private Model model;
	
//	public MainView (){
//		main();
//	}
//	
	public void getModel(Model model) {
		this.model = model;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		HBox hbox = new HBox();
		hbox.setTranslateY(560);
	    hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setMinWidth(640);
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #F5F5F5;");
	    
	    Text text = new Text("Current Player: ");
	    text.setFont(new Font(16));
	    text.setTranslateY(5);
	    
	    Button newGame = new Button("NewGame");
	    newGame.setPrefSize(100, 30);
	    newGame.setTranslateX(230);
	    newGame.setStyle("-fx-background-color: #DCDCDC;");

	    Button close = new Button("Close");
	    close.setPrefSize(100, 30);
	    close.setTooltip(null);
	    close.setTranslateX(250);
	    close.setOnMouseEntered(e -> close.setStyle("-fx-background-color: #FF0000;"));
	    close.setOnMouseExited(e -> close.setStyle("-fx-background-color: #DCDCDC;"));
	    close.setOnMouseClicked(e -> {
			try {
				Platform.exit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	    hbox.getChildren().addAll(text, newGame, close);
	    
		Shape shape = new Rectangle((COLUMNs + 1) * TITLE_SIZE, (ROWS +1) * TITLE_SIZE);
		Circle [][] list = new Circle[6][7];			//rows columns
		Group circleGroup = new Group();
		for(int y = 0; y < ROWS; y++) {
			for(int x = 0; x < COLUMNs; x++) {
				Circle circle = new Circle(TITLE_SIZE/2, Color.WHITE);
				circle.setCenterX(TITLE_SIZE/2);
				circle.setCenterY(TITLE_SIZE/2);
				circle.setTranslateX(x * (TITLE_SIZE + 5) + TITLE_SIZE/4);
				circle.setTranslateY(y * (TITLE_SIZE + 5) + TITLE_SIZE/4);
				circleGroup.getChildren().add(circle);
				list [y][x] = circle;
			}
		}	
		
		shape.setFill(Color.BLUE);
		Group root = new Group();
		root.getChildren().addAll(shape,circleGroup,hbox);	
		root.getChildren().addAll(makeColumns());
		stage.setScene(new Scene(root, 640, 620));
		stage.show();
		
		
	}
	List <Rectangle> list = new ArrayList<>();
	private List<Rectangle> makeColumns(){
		
		
		for (int x = 0; x < COLUMNs; x++) {
			Rectangle rect = new Rectangle(TITLE_SIZE, (ROWS + 1) * TITLE_SIZE);
			rect.setFill(Color.TRANSPARENT);
			rect.setTranslateX(x * (TITLE_SIZE + 5) + TITLE_SIZE/4);
			
			rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(000, 178, 238, 0.2)));
			rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
			
			final int column = x;
			rect.setOnMouseClicked(e -> putToken(column));
			
			
			list.add(rect);
		}
		
		return list;
	}

	private void putToken(int column) {
		
		
	}
	
	

}
