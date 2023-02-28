package com.example.connectedfour;

import com.example.connetedfour.Player;
import com.example.connetedfour.VirtualGameBoard;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ConnectedFourTests {
    private Player playerOne = new Player("Niclas", true, 1, Color.RED);
    private Player playerTwo = new Player("Gegner", false, 2, Color.YELLOW);

    @Test
    void testIsGameOver() {
        VirtualGameBoard model = new VirtualGameBoard(playerOne, playerTwo);
        int[][] board = {
                {1, 1, 0, 1, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        model.setBoard(board);
        Assertions.assertTrue(model.isGameOver());
    }

    @Test
    void testIsBoardFull() {
        VirtualGameBoard model = new VirtualGameBoard(playerOne, playerTwo);
        int[][] board = new int [6][7];
        for (int [] row: board) {
            Arrays.fill(row, 1);
        }
        model.setBoard(board);
        Assertions.assertTrue(model.isBoardFull());

        int[][] board2 = {
                {1, 1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        model.setBoard(board2);
        Assertions.assertFalse(model.isBoardFull());
    }

}
