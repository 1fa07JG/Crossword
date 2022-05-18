package com.example.crossword;

import java.util.Arrays;

public class Question {
    public String question;
    public String answer;
    public Coordinates startingPosition;
    public boolean isHorizontal;

    public Question(String q, String a) {
        question = q;
        answer = a;
        startingPosition = findWord(a);
    }

    public Question(String answer, String question, Coordinates startingPosition, boolean horizontal) {
        this.question = question;
        this.answer = answer;
        this.startingPosition = startingPosition;
        this.isHorizontal = horizontal;
    }

    public Question(String answer, String question, Coordinates startingPosition) {
        this.question = question;
        this.answer = answer;
        this.startingPosition = startingPosition;
    }

    public Question(String a) {
        answer = a;
        startingPosition = findWord(a);
    }

    public Question(String answer, Coordinates startingPosition) {
        this.answer = answer;
        this.startingPosition = startingPosition;
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
    //getter und Setter:

    public boolean getIsHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Coordinates getStartingPosition() {
        return startingPosition;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", startingPosition=" + startingPosition +
                '}';
    }
}
