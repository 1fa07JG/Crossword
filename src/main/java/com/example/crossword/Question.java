package com.example.crossword;

import java.util.Arrays;

public class Question {
    public String question;
    public String anwser;
    public Coordinates startingPosition;

    Question(String q, String a) {
        question = q;
        anwser = a;
        startingPosition = findWord(a);
    }

    Question(String a) {
        anwser = a;
        startingPosition = findWord(a);
    }

    public static Coordinates findWord(String s) {
        char firstChar = s.charAt(0);
        Coordinates wordBeginning;
        for (int y = 0; y < Crossword.getHeight(); y++) {
            for (int x = 0; x < Crossword.getWidth(); x++) {
                if (Crossword.getRaster()[x][y] == firstChar) {
                    wordBeginning = new Coordinates(x, y);
                    char[] compare = new char[s.length()];
                    if (Crossword.getRaster()[x + 1][y] == ' ') {
                        System.arraycopy(Crossword.getRaster()[x], y, compare, 0, s.length());
                    } else {
                        for (int z = 0; z < s.length(); z++) {
                            compare[z] = Crossword.getRaster()[x + z][y];
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
}
