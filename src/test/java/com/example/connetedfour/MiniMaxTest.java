package com.example.connetedfour;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MiniMaxTest {
    private int [] testWindow = new int[]{2, 2, 2, 0};
    private Player player = new Player("Niclas", true, 1, Color.RED);
    private Player playerAI = new Player("AI", false, 2, Color.YELLOW);
    @Test
    void evaluateWindow() {
        MiniMax miniMax = new MiniMax(playerAI, player);
        var res = miniMax.evaluateWindow(testWindow,playerAI.getID());
        Assertions.assertEquals(5, res);
    }

    @Test
    void getScorePosition() {
    }

    @Test
    void countOccurrences() {
        MiniMax miniMax = new MiniMax(playerAI, player);
        String[] stringArray = new String[] { "A", "B", "C", "D" };
        var oo = Arrays.asList(stringArray);
        var test = miniMax.countOccurrences(testWindow, playerAI.getID());
        Assertions.assertEquals(3, test);
    }
}