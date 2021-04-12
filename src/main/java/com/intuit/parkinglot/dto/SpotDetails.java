package com.intuit.parkinglot.dto;

import com.intuit.parkinglot.dao.enums.SpotType;

public class SpotDetails {
    private int level;
    private int row;
    private SpotType spotType;

    public SpotDetails(int level, int row, SpotType spotType) {
        this.level = level;
        this.row = row;
        this.spotType = spotType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }
}
