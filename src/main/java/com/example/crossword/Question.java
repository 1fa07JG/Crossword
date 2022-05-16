package com.example.crossword;

public class Question {
    public String question;
    public String anwser;
    public Coordinates startingPosition;

    Question(String q, String a, int x, int y) {
        question = q;
        anwser = a;
        startingPosition = new Coordinates(x, y);
    }
}
