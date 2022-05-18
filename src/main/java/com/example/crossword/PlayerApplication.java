package com.example.crossword;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerApplication {
    static char[][] answerRaster = new char[Crossword.width][Crossword.height];
    static Question[] questionList;//=new Question[Crossword.getHeight()*Crossword.getWidth()];
    static ArrayList<Question> questionArrayList = new ArrayList<>();
    //alternativ kann auch einen Array list verwendet werden die bessere variante muss noch gefunden werden.


    public static void main(String[] args) throws IOException {
        Crossword.exampleSetup();
        getAnwserRaster();
        for (int i = 0; i < questionArrayList.toArray().length; i++) {
            System.out.println(questionArrayList.toArray()[i].toString());
        }
        PrintCrossword.producePdfCrossword("");


    }

    public static ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public static Question[] getQuestionList() {
        questionList = new Question[questionArrayList.toArray().length];
        for (int i = 0; i < questionList.length; i++) {
            questionList[i] = new Question(questionArrayList.get(i).getAnwser(), questionArrayList.get(i).getQuestion(), questionArrayList.get(i).getStartingPosition());
        }
        return questionList;
    }


    public static boolean setWordBeginings() {

        return false;
    }

    public static void getAnwserRaster() {
        for (int y = 0; y < Crossword.height; y++) {
            for (int x = 0; x < Crossword.width; x++) {
                if (Crossword.raster[x][y] != ' ') {
                    answerRaster[x][y] = '_';
                }
            }

        }
        setWordBeginings();
    }
}
