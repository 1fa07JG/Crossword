package com.example.crossword;

import java.util.Arrays;

public class Bin {

    public static Coordinates findWord(String s) {
        char firstChar = s.charAt(0);
        Coordinates wordBeginning;
        for (int y = 0; y < Crossword.height; y++) {
            for (int x = 0; x < Crossword.width; x++) {
                if (Crossword.raster[x][y] == firstChar) {
                    wordBeginning = new Coordinates(x, y);
                    char[] compare = new char[s.length()];
                    if (Crossword.raster[x + 1][y] == ' ') {
                        System.arraycopy(Crossword.raster[x], y, compare, 0, s.length());
                    } else {
                        for (int z = 0; z < s.length(); z++) {
                            compare[z] = Crossword.raster[x + z][y];
                        }
                    }
                    if (Arrays.equals(compare, s.toCharArray())) {

                        return wordBeginning;
                    }
                }
            }

        }
        return null;
    }

    public static boolean canAddWordHere(Coordinates cor) {
        boolean a = Crossword.raster[cor.horizontal][cor.vertikal - 1] == ' ';
        boolean b = Crossword.raster[cor.horizontal][cor.vertikal + 1] == ' ';
        boolean c = Crossword.raster[cor.horizontal - 1][cor.vertikal] == ' ';
        boolean d = Crossword.raster[cor.horizontal + 1][cor.vertikal] == ' ';
        if (cor != null) {
            return (!a && !b)
                    || (!c && !d);
            //funktion hinzufügen die überprüft, ob die ganze, strecke auf die hinzugefügt werden kann frei ist
        } else {
            return false;
        }

    }

    public static char findConnection(String s1, String s2) {
        char connectingChar = '-';
        char[] word1 = s1.toCharArray();
        char[] word2 = s2.toCharArray();
        for (char c1 : word1) {
            for (char c2 : word2) {
                if (c1 == c2) {
                    connectingChar = c2;
                    break;
                }
            }
        }

        return connectingChar;
    }
}
