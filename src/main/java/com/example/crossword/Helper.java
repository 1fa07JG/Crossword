package com.example.crossword;

import com.example.crossword.playground.PrintCrosswordPlayground;

public class Helper {
    public static int findStopX() {
        int temporaryRowIndex = 0;
        char[][] table = Crossword.getRaster();
        for (int i = 0; i < Crossword.getWidth(); i++) {
            if (!PrintCrosswordPlayground.columnIsEmpty(table, i)) {
                temporaryRowIndex = i;
            }
        }
        return temporaryRowIndex;
    }

    public static Coordinates getStartCoordinates() {
        return new Coordinates(findStartX(), findStartY());
    }

    public static Coordinates getStopCoordinates() {
        return new Coordinates(findStopX(), findStopY());
    }

    public static int findStartY() {
        char[][] table = Crossword.getRaster();
        for (int i = 0; i < Crossword.getHeight(); i++) {
            if (PrintCrosswordPlayground.rowIsEmpty(table, i)) {
                return i;
            }
        }
        return Crossword.getHeight();
    }

    public static int findStartX() {
        char[][] table = Crossword.getRaster();
        for (int i = 0; i < Crossword.getWidth(); i++) {
            if (!PrintCrosswordPlayground.columnIsEmpty(table, i)) {
                return i;
            }
        }
        return Crossword.getWidth();
    }

    public static int findStopY() {
        int temporaryColumnIndex = 0;
        char[][] table = Crossword.getRaster();
        for (int i = 0; i < Crossword.getHeight(); i++) {
            if (PrintCrosswordPlayground.rowIsEmpty(table, i)) {
                temporaryColumnIndex = i;
            }
        }
        return temporaryColumnIndex;
    }
}
