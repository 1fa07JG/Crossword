package com.example.crossword;

public class Coordinates {
    int horizontal;
    int vertikal;

    public Coordinates(int x, int y) {
        horizontal = x;
        vertikal = y;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertikal() {
        return vertikal;
    }

    @Override
    public String toString() {
        return "[" + horizontal + "|" + vertikal + "]";
    }
}
