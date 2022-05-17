package com.example.crossword.playground;

import com.example.crossword.Coordinates;
import com.example.crossword.Crossword;

import java.io.IOException;

public class PrintCrosswordPlaygroung {
    public static void main(String[] args) throws IOException {
        Crossword cw = new Crossword();
        cw.exampleSetup();
        //getAnwserRaster();
        //PrintCrosswordPlaygroung printCrossword = new PrintCrosswordPlaygroung();
        //printCrossword.producePdfCrossword(cw, "");
        printFieldWithLimits(new Coordinates(0,0),new Coordinates(60,20),cw);
        System.out.println("Spalte dreißig is leer: "+columnIsEmpty(cw.getRaster(),30));
        System.out.println("Spalte elf is leer: "+columnIsEmpty(cw.getRaster(),11));
        System.out.println("Start Char Y: "+findStartY(cw));
        System.out.println("Start Char X: "+findStartX(cw));
        System.out.println("Stop Char Y: "+findStopX(cw));
        System.out.println("Stop Char X: "+findStopX(cw));
        printFieldWithLimits(new Coordinates(findStartX(cw),findStartY(cw))
                ,new Coordinates(findStopX(cw),findStopY(cw)),cw);
    }


    public static int findStartY(Crossword crossword){
        char table[][]=crossword.getRaster();
        for (int i = 0; i < crossword.getHeight(); i++) {
            if(rowIsEmpty(table,i)==false){
                return i;
            }
        }
        return crossword.getHeight();
    }

    public static int findStartX(Crossword crossword){
        char table[][]=crossword.getRaster();
        for (int i = 0; i < crossword.getWidth(); i++) {
            if(columnIsEmpty(table,i)==false){
                return i;
            }
        }
        return crossword.getWidth();
    }

    public static int findStopY(Crossword crossword){
        int temporyColumIndex=0;
        char table[][]=crossword.getRaster();
        for (int i = 0; i < crossword.getHeight(); i++) {
            if(rowIsEmpty(table,i)==false){
                temporyColumIndex=i;
            }
        }
        return temporyColumIndex;
    }

    public static int findStopX(Crossword crossword){
        int temporyRowIndex=0;
        char table[][]=crossword.getRaster();
        for (int i = 0; i < crossword.getWidth(); i++) {
            if(columnIsEmpty(table,i)==false){
               temporyRowIndex=i;
            }
        }
        return temporyRowIndex;
    }

    public static int firstChar(Crossword crossword) {
        Coordinates start;
        char table[][]=crossword.getRaster();
        for (int e = 0; e <crossword.getHeight() ; e++) {


        for (int i = 0; i < crossword.getWidth(); i++) {
            //       SPALTE, ZEILE
            if (table[i][e] != ' ') {

            }
        }}
        return table[0].length;
    }

    public static int lastCharInRow(Crossword crossword, int index) {
        char table[][]=crossword.getRaster();
        for (int i = 0; i < table[0].length; i++) {
            //       SPALTE, ZEILE
            if (table[i][index] == ' ') {
                return i;
            }
        }
        return table[0].length;
    }

    //finde end und start koordinaten mit methoden



    public static void printFieldWithLimits(Coordinates start,Coordinates stop,Crossword crossword) {
// coordinaten zwangsweise an die Größe des Felds anpassen.
        System.out.print("  ");
        for (int x = start.getHorizontal(); x < stop.getHorizontal(); x++) {
            System.out.print("|" + x);

        }
        System.out.println();
        for (int y = start.getVertikal(); y < stop.getVertikal(); y++) {
            if (y<10){
                System.out.print(' ');
            }
            System.out.print(y);

            for (int x = start.getHorizontal(); x < stop.getHorizontal(); x++) {
                System.out.print("|" + crossword.getRaster()[x][y]);
                if (x>=10){
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }


    public static boolean rowIsEmpty(char table[][], int rowIndex) {
        for (int columnIndex = 0; columnIndex < table.length; columnIndex++) {
            //       SPALTE, ZEILE
            if (table[columnIndex][rowIndex] != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean columnIsEmpty(char table[][], int columnIndex) {
        for (int rowIndex = 0; rowIndex < table[0].length; rowIndex++) {
            if (table[columnIndex][rowIndex] != ' ') {
                return false;
            }
        }
        return true;
    }

}
