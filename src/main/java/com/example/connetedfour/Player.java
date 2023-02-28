package com.example.connetedfour;

import javafx.scene.paint.Color;

public class Player {

    private final String NAME;
    private final Boolean ISSTARTPLAYER;
    private final Integer ID;
    private final Color COLOR;

    public Player(String name, boolean isStartPlayer, int id, Color color) {
        this.NAME = name;
        this.ISSTARTPLAYER = isStartPlayer;
        this.ID = id;
        this.COLOR = color;
    }

    public Boolean getStartPlayer() {
        return ISSTARTPLAYER;
    }

    public String getNAME() {
        return NAME;
    }

    public Integer getID() {
        return ID;
    }

    public Color getCOLOR() {
        return COLOR;
    }


}
