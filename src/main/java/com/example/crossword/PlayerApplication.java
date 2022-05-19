package com.example.crossword;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerApplication {
    static char[][] answerRaster = new char[Crossword.width][Crossword.height];
    static Question[] questionList;
    static ArrayList<Question> questionArrayList = new ArrayList<>();
    //es finden sowohl eine Array list als auch ein Array verwendung, um die Vorteile von
    // beiden nutzen zu können in zukunft wäre anzudenken bei jeder änderung der
    // ArrayList den Array automatisch anzupassen und somit zu einer Bloßen hilfsmethode des Arrays zu degradieren

    public static void main(String[] args) throws IOException {
        Crossword.exampleSetup();
        for (int i = 0; i < questionArrayList.toArray().length; i++) {
            System.out.println(questionArrayList.toArray()[i].toString());
        }
        PrintCrossword.producePdfCrossword("");

    }

    public static Question[] getQuestionList() {
        return questionList;
    }

    private static Question[] createQuestionList() {
        questionList = new Question[questionArrayList.toArray().length];
        for (int i = 0; i < questionList.length; i++) {
            questionList[i] = new Question(questionArrayList.get(i).getAnswer(), questionArrayList.get(i).getQuestion(), questionArrayList.get(i).getStartingPosition(), questionArrayList.get(i).getIsHorizontal());
        }
        return questionList;
    }

    static void addQuestion(Question q) {
        questionArrayList.add(q);
        questionList = createQuestionList();
    }
}
