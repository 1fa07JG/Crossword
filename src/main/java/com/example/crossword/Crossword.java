package com.example.crossword;

import java.util.ArrayList;

public class Crossword {

    static int width = 60;
    static int height = 20;
    //                                SPALTEN ZEILEN
    static char[][] raster = new char[width][height];  // x (horizontal) / y (vertikal); 0/0 ist links oben

    public static void main(String[] args) {

        exampleSetup();


        printField();


    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static char[][] getRaster() {
        return raster;
    }

    //The Word setting Methods:
    public static void setWordToWord(String answer, String question) {
        //buchstabe finden herausfinden, wo das einzugebende wort beginnen soll.
        //es wäre möglich anstelle der Vorschleife mit einer math. Random funktion zu arbeiten

        answer = answer.toUpperCase();
        Coordinates[] connectionCoordinates = findConnectionInRaster(answer);

        boolean success = false;
        for (int i = 0; i < connectionCoordinates.length && !success; i++) {
            Coordinates cor = connectionCoordinates[i];


            int letterIndex = indexOfLetterInWord(answer, raster[cor.horizontal][cor.vertikal]);
            if (wordIsHorizontal(cor)) {
                success = setWordVertically(new Question(answer, question, new Coordinates(cor.horizontal, cor.vertikal - letterIndex)));
            } else {
                success = setWordHorizontally(new Question(answer, question, new Coordinates(cor.horizontal - letterIndex, cor.vertikal)));
            }

        }
    }


    public static boolean setWordHorizontally(Question question) {

        Coordinates cor = question.getStartingPosition();
        question.setAnswer(question.getAnswer().toUpperCase());
        String s = question.getAnswer();
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
        question.setHorizontal(true);
        addQuestion(question);
        return true;
    }

    public static boolean setWordVertically(Question question) {
        question.setAnswer(question.getAnswer().toUpperCase());
        String s = question.getAnswer();
        Coordinates cor = question.getStartingPosition();
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
        question.setHorizontal(false);
        addQuestion(question);
        return true;

    }

    //Helper Methodes for Main
    private static void initializeField() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                raster[x][y] = ' ';
            }

        }

    }

    public static void exampleSetup() {
        initializeField();

        setWordHorizontally(new Question("Sherlock", "The Worlds most Famous fictional Detective (Firstname)", new Coordinates(30, 12)));
        setWordToWord("ship", "Corvette, Tug, Frigate");
        setWordToWord("Watson", "The Surname of a Lead character played by Martin Freeman in a 2010 crime drama ");

        setWordToWord("water", "primordial element according to Thales of Miletus");
        setWordToWord("London", "City near the County of Essex");
        setWordToWord("Zabbix", "Infrastructure monitoring software first Released in 2001");
        setWordToWord("Year", "A unit to measure Time defined by Pope Gregor the XIII");
        setWordToWord("Waal", " municipality in the district of Ostallgäu in Bavaria");
        setWordToWord("hex", "short for Hexadecimal");
        //setWordVertically(new Question("First", "Translation of the latin number I. into words", new Coordinates(1, 1)));
        //setWordHorizontally(new Question("end", "ende in englisch", new Coordinates(57, 19)));


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

    private static void addQuestion(Question q) {
        PlayerApplication.questionArrayList.add(q);
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
