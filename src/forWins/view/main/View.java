package forWins.view.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import forWins.model.main.Model;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View extends Application{
	
	private static final int TITLE_SIZE = 80;
	private static final int COLUMNS = 7;
	private static final int ROWS = 6;
	private String playerOne = "player1";
	private String playerTwo = "player2";
	private List <Rectangle> list = new ArrayList<>();
	private Circle [][] circles = new Circle[6][7];			//rows columns
	private Model model = new Model();
	private Text text;
	
	

	
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
	    
	    MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
       
        Menu editMenu = new Menu("Edit");
        Menu modus = new Menu("Modus");
        MenuItem player1 = new MenuItem("Player 1");
        MenuItem player2 = new MenuItem("Player 2");
        CheckMenuItem oneVsOne = new CheckMenuItem("1 vs 1");
        oneVsOne.setSelected(true);
        MenuItem vsPc = new MenuItem("1 vs Computer");
	    editMenu.getItems().addAll(player1, player2);
	    modus.getItems().addAll(oneVsOne, vsPc);
	    menuBar.getMenus().addAll(editMenu, modus);
	    player1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TextInputDialog dialog = new TextInputDialog("Name");
				dialog.setTitle("Name");
				dialog.setHeaderText("Name of Player 1");
				dialog.setContentText("Player 1: ");

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    playerOne = result.get();
				}
				setPlayerName();
			
			}
		});
	    player2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TextInputDialog dialog = new TextInputDialog("Name");
				dialog.setTitle("Name of Player 2");
				dialog.setContentText("Player 2:");

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    playerTwo = result.get();
				}
				setPlayerName();
			
			}
		});
	    
	    
	    setPlayerName();
	    text.setFont(new Font(16));
	    text.setTranslateY(5);
	    
	    // Button for new Game
	    Button newGame = new Button("NewGame");
	    newGame.setPrefSize(100, 30);
	    newGame.setOnMouseClicked(e -> {
			try {
				newGame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

	    // Button for close the game
	    Button close = new Button("Close");
	    close.setPrefSize(100, 30);
	    close.setOnMouseClicked(e -> {
			try {
				Platform.exit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	    
	    HBox rightButtons = new HBox(newGame, close);
	    close.setTranslateX(5);
        rightButtons.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightButtons, Priority.ALWAYS);
	    hbox.getChildren().add(text);
	    hbox.getChildren().addAll(rightButtons);
	    
		Shape shape = new Rectangle((COLUMNS + 1) * TITLE_SIZE, (ROWS +1) * TITLE_SIZE);
		
		Group circleGroup = new Group();
		for(int y = 0; y < ROWS; y++) {
			for(int x = 0; x < COLUMNS; x++) {
				Circle circle = new Circle(TITLE_SIZE/2, Color.WHITE);
				circle.setCenterX(TITLE_SIZE/2);
				circle.setCenterY(TITLE_SIZE/2);
				circle.setTranslateX(x * (TITLE_SIZE + 5) + TITLE_SIZE/4);
				circle.setTranslateY(y * (TITLE_SIZE + 5) + TITLE_SIZE/4);
				circleGroup.getChildren().add(circle);
				circles [y][x] = circle;
			}
		}	
		
		
		shape.setFill(Color.BLUE);
		Group root = new Group();
		root.getChildren().addAll(shape,circleGroup,hbox);	
		root.getChildren().addAll(makeColumns());
		root.getChildren().addAll(menuBar);
		stage.setScene(new Scene(root, 640, 620));
		stage.show();
		
		
	}
	
	public List<Rectangle> makeColumns(){
		
		
		for (int x = 0; x < COLUMNS; x++) {
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
	
	/**
	 * Visualize the token which was placed
	 * @param column in which the token was placed 
	 */
	private void putToken(int column) {	
		int row = model.putToken(column);
		if (row >= 0) {
		TranslateTransition trans = new TranslateTransition((Duration.millis(400)),circles[row][column] );
			if (model.getCurrentPlayer() == 1) {
				
				circles[row][column].setFill(Color.RED);
				
				circles[row][column].setTranslateY(-10);
				
				
				trans.setToY(row * (TITLE_SIZE + 5) + TITLE_SIZE/4);
				
				trans.play();
				
			} else {
				circles[row][column].setFill(Color.YELLOW);
				circles[row][column].setTranslateY(5);
				trans.setToY(row * (TITLE_SIZE + 5) + TITLE_SIZE/4);
				
				trans.play();
			}
			
			
			model.changePlayer();
			setPlayerName();
			if(model.playerHasWon()) {
				showWinner();
			}
			if(model.NoTokensLeft()) {
				showGameEnd();
			}	
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Column overloaded");
			alert.setContentText("You can`t put any Token in this colum because it is full");
			alert.showAndWait();
		}
	
		
	}
	
	public void showWinner() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("The Winner is:");
		alert.setContentText((model.getWinner() == 1) ? playerOne : playerTwo);
		alert.showAndWait();
		newGame();
	}
	
	public void showGameEnd() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("GameOver. No Winner!");
		alert.showAndWait();
		newGame();
	}
	
	private void newGame() {
		model.newGame();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				circles[i][j].setFill(Color.WHITE);
			}
		}
		setPlayerName();
	}
	
	public void setPlayerName() {
		if((text == null)) {
			text = new Text("Current Player: " + model.getCurrentPlayer());
		}else {
			if(model.getCurrentPlayer() == 1) {
				text.setText("Current Player: " + playerOne);
			}else {
				text.setText("Current Player: " +playerTwo);
			}
		}
		
		
	
	}
	
	
	

}
