package com.example.crossword;

import java.util.Arrays;

public class Question {
    public String question;
    public String anwser;
    public Coordinates startingPosition;
    public boolean isHorizontal;

    public Question(String q, String a) {
        question = q;
        anwser = a;
        startingPosition = findWord(a);
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public Question(String anwser, String question, Coordinates startingPosition, boolean horziontal) {
        this.question = question;
        this.anwser = anwser;
        this.startingPosition = startingPosition;
        this.isHorizontal = horziontal;
    }

    public Question(String anwser, String question, Coordinates startingPosition) {
        this.question = question;
        this.anwser = anwser;
        this.startingPosition = startingPosition;
    }

    public Question(String a) {
        anwser = a;
        startingPosition = findWord(a);
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public Question(String anwser, Coordinates startingPosition) {
        this.anwser = anwser;
        this.startingPosition = startingPosition;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnwser() {
        return anwser;
    }

    public Coordinates getStartingPosition() {
        return startingPosition;
    }



    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", anwser='" + anwser + '\'' +
                ", startingPosition=" + startingPosition +
                '}';
    }

    public static Coordinates findWord(String s) {
        //findet bestimmte worte nicht z.b. WAAL SHIP und LONDON
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
