package com.example.connetedfour;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Window;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private final Player playerOne = new Player("One", true, 1, Color.RED);
    private final Player playerTwo = new Player("Two", false, 2, Color.YELLOW);
    private final Player PLAYERAI = new Player("AI", false, 2, Color.YELLOW);
    private final VirtualGameBoard board = new VirtualGameBoard(playerOne, playerTwo);
    private Player currentPlayer = playerOne;
    @FXML
    private GridPane gridPane;
    @FXML
    private CheckMenuItem checkMenuPlayer;
    @FXML
    private CheckMenuItem checkMenuComputer;



    /*
    TranslateTransition trans = new TranslateTransition((Duration.millis(400)), testCl);
    testCl.setFill(Color.RED);
    circles[row][column].setTranslateY(-10);
    trans.setToY(row * (TITLE_SIZE + 5) + TITLE_SIZE / 4);
    trans.play();*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMenuListener(Arrays.asList(checkMenuPlayer, checkMenuComputer));

        setOnMouseClickedHandler();
        setOnMouseEnteredHandler();
        setOnMouseExitedHandler();

    }

    private void setOnMouseClickedHandler() {
        gridPane.getChildren().forEach(circle -> circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gameFlow(circle);
            }
        }));
    }

    private void gameFlow(Node circle) {
        int column = GridPane.getColumnIndex(circle);
        if (!board.hasColumnFreeRow(column)) {
            return;
        }
        int row = board.getLastFreeRowInColumn(column);
        System.out.println("ROW " + row + " - Columnn" + column);
        putToken(column, row);
        if(board.isGameOver()){
            initiateGameOver();
            return;
        }
        if (getGameMode() == GameMode.COMPUTER) {
            currentPlayer = PLAYERAI;
            column = (int) new MiniMax(PLAYERAI, playerOne).minimax(board, 5, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, true)[0];
            System.out.println("AI Column " + column);
            putToken(column, board.getLastFreeRowInColumn(column));
            if(board.isGameOver()){
                initiateGameOver();
                return;
            }
            currentPlayer = playerOne;
        }else{
            changePlayer();
        }
    }



    private void initiateGameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        setDefaultOwnerIfNotExisting(alert, gridPane.getScene().getWindow());
        alert.setTitle("Game over");
        alert.setHeaderText(null);
        alert.setContentText("Player " + currentPlayer.getNAME() + " has won. \nThe game will restart!");
        alert.setResizable(true);
        alert.onShownProperty().addListener(e -> {
            Platform.runLater(() -> alert.setResizable(false));
        });
        alert.showAndWait();
        restart();
    }

    private void setDefaultOwnerIfNotExisting(Dialog<?> dialog, Window parent) {
        if (dialog.getOwner() == null) {
            dialog.initOwner(parent);
        }
    }

    private void setOnMouseEnteredHandler() {
        gridPane.getChildren().forEach(circle -> circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Circle) circle).setFill(currentPlayer.getCOLOR());
            }
        }));

    }

    private void setOnMouseExitedHandler() {
        gridPane.getChildren().forEach(circle -> circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Circle) circle).setFill(Color.WHITE);
            }
        }));

    }


    private Node getNodeByRowColumnIndex(final int row, final int column, final GridPane gridPane) {
        return gridPane.getChildren().stream()
                .filter(node -> (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column))
                .findFirst()
                .orElse(null);
    }

    private void changePlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else currentPlayer = playerOne;

    }

    private void disable(Node node, EventHandler eventHandler) {
        node.removeEventHandler(MouseEvent.MOUSE_EXITED, eventHandler);
        node.removeEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler);
    }


    private void setMenuListener(List<CheckMenuItem> menuItems) {
        menuItems.stream()
                .forEach(menuItem -> menuItem.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                        if (menuItem.isSelected()) {
                            menuItem.setDisable(true);
                            enableMenuItem(getOtherMenuItem(menuItem, menuItems));
                            restart();
                        }
                    }
                }));

    }

    private void enableMenuItem(CheckMenuItem menuItem) {
        menuItem.setSelected(false);
        menuItem.setDisable(false);
    }


    private CheckMenuItem getOtherMenuItem(CheckMenuItem menuItem, List<CheckMenuItem> menuItems) {
        for (CheckMenuItem item : menuItems) {
            if (item != menuItem) {
                return item;
            }
        }
        return null;
    }

    private GameMode getGameMode() {
        if (checkMenuPlayer.isSelected()) {
            return GameMode.PLAYER;
        } else {
            return GameMode.COMPUTER;
        }
    }

    private void resetAllCircleColors() {
        gridPane.getChildren().forEach(circle -> ((Circle) circle).setFill(Color.WHITE));
    }

    private void restart() {
        resetAllCircleColors();
        setOnMouseEnteredHandler();
        setOnMouseExitedHandler();
        currentPlayer = playerOne;
        board.resetGameBoard();
    }

    private void putToken(int column, int row) {
        board.putToken(column, currentPlayer.getID());
        Node circleToPut = getNodeByRowColumnIndex(row, column, gridPane);
        circleToPut.setOnMouseExited(null);
        circleToPut.setOnMouseEntered(null);
        //disable(circleToPut, this);
        ((Circle) circleToPut).setFill(currentPlayer.getCOLOR());
    }


}