package com.example.crossword;

import java.util.ArrayList;

public class Crossword {

    static int width = 60;
    static int height = 20;
    //                                SPALTEN ZEILEN
    static char[][] raster = new char[width][height];  // x (horizontal) / y (vertikal); 0/0 ist links oben


    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static char[][] getRaster() {
        return raster;
    }

    public static void main(String[] args) {

        exampleSetup();


        printField();


    }

    public static void exampleSetup() {
        initializeField();

        setWordHorizontally("Sherlock", new Coordinates(30, 12));
        setWordToWord("ship");
        setWordToWord("Watson");

        setWordToWord("water");
        setWordToWord("London");
        setWordToWord("Zabbix");
        setWordToWord("Year");
        setWordToWord("Waal");
    }

    private static void initializeField() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                raster[x][y] = ' ';
            }

        }

    }


    public static void setWordToWord(String s) {
        //buchstabe finden herausfinden, wo das einzugebende wort beginnen soll.
        //es wäre möglich anstelle der Vorschleife mit einer math. Random funktion zu arbeiten

        s = s.toUpperCase();
        Coordinates[] connectionCoordinates = findConnectionInRaster(s);

        boolean success = false;
        for (int i = 0; i < connectionCoordinates.length && !success; i++) {
            Coordinates cor = connectionCoordinates[i];


            int letterIndex = indexOfLetterInWord(s, raster[cor.horizontal][cor.vertikal]);
            if (wordIsHorizontal(cor)) {
                success = setWordVertically(s, new Coordinates(cor.horizontal, cor.vertikal - letterIndex));
            } else {
                success = setWordHorizontally(s, new Coordinates(cor.horizontal - letterIndex, cor.vertikal));
            }

        }
    }


    public static boolean setWordHorizontally(String s, Coordinates cor) {
        s = s.toUpperCase();
        int x = cor.horizontal;
        int y = cor.vertikal;

        if (x + s.length() > width) {
            System.out.println("Word is too long!");
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = raster[x + i][y];
            if (c != ' ' && c != s.charAt(i)) {
                return false;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            raster[x + i][y] = s.charAt(i);
        }

        addQuestion(s);
        return true;
    }

    public static boolean setWordVertically(String s, Coordinates cor) {
        s = s.toUpperCase();
        int x = cor.horizontal;
        int y = cor.vertikal;

        if (y + s.length() > height) {
            System.out.println("Word is too long!");
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = raster[x][y + i];
            if (c != ' ' && c != s.charAt(i)) {
                return false;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            raster[x][y + i] = s.charAt(i);
        }

        addQuestion(s);
        return true;

    }

    public static void printField() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print("|" + raster[x][y]);
            }
            System.out.println();
        }
    }

    //WordToWord Support Methoden
    public static boolean wordIsHorizontal(Coordinates cor) {

        return raster[cor.horizontal][cor.vertikal - 1] == ' ' || raster[cor.horizontal][cor.vertikal + 1] == ' ';
    }


    public static Coordinates findLetter(char letter) {
        // eventuell mit ausgangs Koordinaten versehen
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (raster[x][y] == letter) {
                    return new Coordinates(x, y);
                }
            }
        }
        return null;
    }

    public static int indexOfLetterInWord(String s, char c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    private static void addQuestion(String anwser){
        PlayerApplication.questionArrayList.add(new Question(anwser));
    }

    private static Coordinates[] findConnectionInRaster(String s) {
        ArrayList<Coordinates> letterCoordinates = new ArrayList<>();
        //eventual mit Ausgangs Koordinaten versehen und als fehler negative Koordinaten zurückgeben
        for (char c : s.toCharArray()) {
            Coordinates cord = findLetter(c);
            if (cord != null) {
                letterCoordinates.add(cord);
            }
        }
        Coordinates[] answerCoordinates = new Coordinates[]{};
        answerCoordinates = letterCoordinates.toArray(answerCoordinates);
        return answerCoordinates;
    }


}
