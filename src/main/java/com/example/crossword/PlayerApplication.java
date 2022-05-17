package com.example.crossword;

import java.io.IOException;

public class PlayerApplication {
    static char[][] anwserRaster = new char[Crossword.width][Crossword.height];

    public static void main(String[] args) throws IOException {
        Crossword.exampleSetup();
        //getAnwserRaster();
        PrintCrossword.producePdfCrossword("");

    }

    public static void getAnwserRaster() {
        for (int y = 0; y < Crossword.height; y++) {
            for (int x = 0; x < Crossword.width; x++) {
                if (Crossword.raster[x][y] != ' ') {
                    anwserRaster[x][y] = '_';
                }
            }
        }
    }
}
