package com.example.connetedfour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class VirtualGameBoard implements Cloneable {
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private int [] [] board = new int [COLUMNS] [ROWS];
    private final Player PLAYERONE;
    private final Player PLAYERTWO;

    public VirtualGameBoard(Player playerOne, Player playerTwo) {
        this.PLAYERONE = playerOne;
        this.PLAYERTWO = playerTwo;
    }

    public int[][] getBoard() {
        return board;
    }

    public VirtualGameBoard copy (){
        VirtualGameBoard virtualGameBoard = this.clone();
        virtualGameBoard.setBoard(this.copyBoard());
        return virtualGameBoard;

    }
    @Override
    public VirtualGameBoard clone (){
        VirtualGameBoard gameBoard = null;
        try {
            gameBoard = (VirtualGameBoard) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return gameBoard;
    }

    private int [][] copyBoard(){
        return Arrays.stream(this.board)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getLastFreeRowInColumn (int column){
        for(int i = ROWS - 1; i >= 0; i--){
            if(board[column][i] == 0){
                return i;
            }
        }
        return -1;
    }

    public void putToken(int column, int playerID){
        board[column][getLastFreeRowInColumn (column)] = playerID;
    }

    public boolean hasColumnFreeRow(int column){
        return getLastFreeRowInColumn(column) != -1;
    }

    public void resetGameBoard() {
        for (int [] row: board) {
            Arrays.fill(row, 0);
        }
    }


    public boolean isBoardFull(){
        IntStream stream = Arrays.stream(board).flatMapToInt(x -> Arrays.stream(x));
        return stream.filter(item -> item == 0).toArray().length == 0;
    }

    public boolean isGameOver(){
       return hasPlayerWon(PLAYERONE) || hasPlayerWon(PLAYERTWO);
    }

    public boolean hasPlayerWon(Player player){
        return checkVerticalWin(player) || checkHorizontalWin(player) || checkDiagonalWin(player) || checkDiagonalBackwardsWin(player);
    }

    private boolean checkHorizontalWin(Player player) {
        for (int i = 0; i < COLUMNS - 3; i++) {
            for (int j = 0; j < ROWS; j++){
                if(checkFourInLine(i, j, 1, 0, player )){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin(Player player ) {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS - 3; j++){
                if(checkFourInLine(i, j, 0, 1, player )){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(Player player ) {
        for (int i = 0; i < COLUMNS -3 ; i++) {
            for (int j = 0; j < ROWS - 3; j++){
                if(checkFourInLine(i, j, 1, 1, player )){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalBackwardsWin(Player player) {
        for (int i = COLUMNS -1; i > COLUMNS - 5 ; i--) {
            for (int j = 0; j < ROWS - 3; j++){
                if(checkFourInLine(i, j, -1, 1, player)){
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean checkFourInLine(int column, int row, int offsetColumn, int offsetRow, Player player ) {
       return  board[column][row] == player.getID() &&
               board[column][row] == board[column + offsetColumn ][row + offsetRow] &&
               board[column + offsetColumn ][row + offsetRow]  == board[column + (offsetColumn * 2)][row + (offsetRow * 2)] &&
               board[column + (offsetColumn * 2)][row + (offsetRow * 2)]  == board[column + (offsetColumn * 3)][row + (offsetRow * 3)] ;
    }

    public List<Integer> getValidColumns(){
        List<Integer> validColumns = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++){
            if(hasColumnFreeRow(i)){
                validColumns.add(i);
            }
        }
        return validColumns;
    }

    public boolean is_terminal_node() {
        return isBoardFull() || isGameOver();
    }


}
