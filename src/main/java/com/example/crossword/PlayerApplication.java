package com.example.crossword;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerApplication {
    static char[][] answerRaster = new char[Crossword.width][Crossword.height];
    static ArrayList<Question> questionArrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Crossword.exampleSetup();
        for (Question question : questionArrayList) {
            System.out.println(question.toString());
        }
        
        PrintCrossword.producePdfCrossword("", true);
        for (int i = 0; i < 50; i++) {
            Crossword.exampleSetup();
            PrintCrossword.producePdfCrossword("", true);
        }

    }



    static void addQuestion(Question q) {
        questionArrayList.add(q);
    }
}
