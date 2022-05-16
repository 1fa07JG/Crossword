package com.example.crossword;

public class Coordinates {
    int horizontal;
    int vertikal;

    Coordinates(int x, int y) {
        horizontal = x;
        vertikal = y;
    }

    @Override
    public String toString() {
        return "[" + horizontal + "|" + vertikal + "]";
    }
}
