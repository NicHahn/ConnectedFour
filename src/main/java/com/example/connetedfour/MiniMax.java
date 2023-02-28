package com.example.connetedfour;

import java.util.*;

public class MiniMax {

    private static final int ROW = 6;
    private static final int COLUMN = 7;
    private static final int EMPTY = 0;
    private static final int WINDOW_LENGTH = 4;
    private final Player PLAYERAI;
    private final Player PLAYEROPPONENT;

    public MiniMax(Player PLAYERAI, Player PLAYEROPPONENT) {
        this.PLAYERAI = PLAYERAI;
        this.PLAYEROPPONENT = PLAYEROPPONENT;

    }

    public float[] minimax(VirtualGameBoard board, int depth, float alpha, float beta, boolean maximizingPlayer) {
        List <Integer> valid_locations = board.getValidColumns();
        boolean is_terminal = board.is_terminal_node();
        if (is_terminal){
            if (board.hasPlayerWon(PLAYERAI)){
                return new float[]{-1, 10000000000000f};
            }
            else if (board.hasPlayerWon(PLAYEROPPONENT)){
                return new float[]{-1, -10000000000000f};
            }
            //#Game is over, no more valid moves
            else{
                return new float[]{-1, 0};
            }

        }
        //Depth is zero
        else if(depth == 0){
            return new float[] {-1 , getScorePosition(board.getBoard(), PLAYERAI.getID())};
        }

        if (maximizingPlayer) {
            float value = Float.NEGATIVE_INFINITY;
            int column = 0;  //getRandomListElement(valid_locations);
            for (int col : valid_locations){
                VirtualGameBoard boardCopy = board.copy();
                boardCopy.putToken(col, PLAYERAI.getID());
                float new_score = minimax(boardCopy, depth - 1, alpha, beta, false)[1];
                if (new_score > value){
                    value = new_score;
                    column = col;
                }
                //alpha = Math.max(alpha, value);
                if (new_score >= beta){
                    break;
                }
                alpha = Math.max(alpha, new_score);
            }
            return new float[]{column,value};
        }
        //#Minimizing player
        else {
            float value = Float.POSITIVE_INFINITY;
            int column = 0;
            for (int col : valid_locations){
                VirtualGameBoard boardCopy = board.copy();
                boardCopy.putToken(col, PLAYEROPPONENT.getID());
                float new_score = minimax(boardCopy, depth - 1, alpha, beta, true)[1];
                if (new_score < value){
                    value = new_score;
                    column = col;
                }
                //beta = Math.min(beta, value);
                if (new_score <= alpha){
                    break;
                }
                beta = Math.min(beta, new_score);
            }
            return new float[]{column,value};
        }
    }

    public int evaluateWindow(int[] window, int piece) {
        int score = 0;
        int oppPiece = PLAYEROPPONENT.getID();
        if (piece == PLAYEROPPONENT.getID()) {
            oppPiece = PLAYERAI.getID();
        }

        if (countOccurrences(window, piece) == 3 && countOccurrences(window, EMPTY) == 1) {
            score += 5;
        } else if (countOccurrences(window, piece) == 2 && countOccurrences(window, EMPTY) == 2) {
            score += 2;
        }else if (countOccurrences(window, oppPiece) == 3 && countOccurrences(window, EMPTY) == 1) {
            score -= 4;
        }

        return score;
    }

    public int countOccurrences(int[] window, int piece) {
        Integer[] boxedArray = Arrays.stream(window) // IntStream
                .boxed()                // Stream<Integer>
                .toArray(Integer[]::new);
        return Collections.frequency(Arrays.asList(boxedArray), piece);
    }

    public int getScorePosition(int[][] board, int piece) {
        int score = 0;

        // Score center column
        int centerColumn = countOccurrences(board[COLUMN/2], piece);
        score += centerColumn * 3;

        // Score Vertical
        for (int c = 0; c < COLUMN; c++) {
            for (int r = 0; r < ROW - 3; r++) {
                int[] window = Arrays.copyOfRange(board[c], r, r + WINDOW_LENGTH);
                score += evaluateWindow(window, piece);
            }
        }

        // Score Horizontal
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COLUMN - 3; c++) {
                int[] window = getHorizontalWindow(board, c, r);
                score += evaluateWindow(window, piece);
            }
        }

        // Score positive sloped diagonal
        for (int c = 0; c < COLUMN - 3; c++) {
            for (int r = 0; r < ROW - 3; r++) {
                int[] window = new int[WINDOW_LENGTH];
                for (int i = 0; i < WINDOW_LENGTH; i++) {
                    window[i] = board[c + i][r + i];
                }
                score += evaluateWindow(window, piece);
            }
        }

        // Score negative sloped diagonal
        for (int c = COLUMN -1; c > COLUMN - 5 ; c--) {
            for (int r = 0; r < ROW - 3; r++){
                int[] window = new int[WINDOW_LENGTH];
                for (int i = 0; i < WINDOW_LENGTH; i++) {
                    window[i] = board[c - i][r + i];
                }
                score += evaluateWindow(window, piece);
            }
        }

        return score;
    }

    private int[] getHorizontalWindow(int[][] board, int col, int row){
        int [] window = new int[WINDOW_LENGTH];
        for (int i = 0; i < WINDOW_LENGTH; i++) {
            window[i] = board[col + i ][row];
        }
        return window;
    }

}
