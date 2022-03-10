package com.example.gamecenter;

public class Tile2048 {

    private int value=0;

    public Tile2048() {
        value=0;
    }

    public Tile2048(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



    public String toString() {
        return String.valueOf(getValue());
    }

}
