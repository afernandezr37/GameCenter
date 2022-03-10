package com.example.gamecenter;

public class Peg {
    private int value;

    public Peg(int value) {
        this.value = value;
    }

    public Peg() {
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
