package com.intuit.parkinglot.dao.entity;

import java.io.Serializable;

public class SpotIndex implements Serializable {

    private int begin;
    private int end;

    public SpotIndex(int begin, int end){
        this.begin = begin;
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }
}
