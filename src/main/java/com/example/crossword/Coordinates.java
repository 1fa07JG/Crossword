package com.example.crossword;

public class Coordinates {
    int horizontal;
    int vertikal;

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertikal() {
        return vertikal;
    }

    public void setVertikal(int vertikal) {
        this.vertikal = vertikal;
    }

    public Coordinates(int x, int y) {
        horizontal = x;
        vertikal = y;
    }

    @Override
    public String toString() {
        return "[" + horizontal + "|" + vertikal + "]";
    }
}
