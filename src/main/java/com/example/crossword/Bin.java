package com.example.crossword;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.util.Arrays;

public class Bin {

    public static boolean canAddWordHere(Coordinates cor) {
        boolean a = Crossword.raster[cor.horizontal][cor.vertikal - 1] == ' ';
        boolean b = Crossword.raster[cor.horizontal][cor.vertikal + 1] == ' ';
        boolean c = Crossword.raster[cor.horizontal - 1][cor.vertikal] == ' ';
        boolean d = Crossword.raster[cor.horizontal + 1][cor.vertikal] == ' ';
        if (cor != null) {
            return (!a && !b)
                    || (!c && !d);
            //funktion hinzufügen die überprüft, ob die ganze, strecke auf die hinzugefügt werden kann frei ist
        } else {
            return false;
        }

    }

    public static char findConnection(String s1, String s2) {
        char connectingChar = '-';
        char[] word1 = s1.toCharArray();
        char[] word2 = s2.toCharArray();
        for (char c1 : word1) {
            for (char c2 : word2) {
                if (c1 == c2) {
                    connectingChar = c2;
                    break;
                }
            }
        }

        return connectingChar;
    }

    public static char[][] trimTable(char[][] table) {
        int rowIndex = table.length;

        for (int row = 0; row < rowIndex; row++) {
            if (PrintCrossword.rowIsEmpty(table, row)) {
                rowIndex = rowIndex - 1;
                table = deleteOneRow(table, row);
            }
        }
        for (int i = 0; i < table[0].length; i++) {
            if (PrintCrossword.columnIsEmpty(table, i)) {

            }
        }
        return table;
    }

    public static char[][] deleteOneRow(char[][] table, int toDeleteIndex) {
        char[][] shortened = new char[table.length - 1][table[0].length];
        int index = 0;
        for (int i = 0; i < table.length - 1; i++) {
            if (i == toDeleteIndex) {
                index++;
            }
            shortened[i] = table[index];
            index++;
        }
        return shortened;
    }

    public static char[][] deleteOneColumn(char[][] table, int toDeleteIndex) {
        char[][] shortened = new char[table.length][table[0].length - 1];
        int index = 0;
        for (int i = 0; i < table[0].length; i++) {
            if (i == toDeleteIndex) {
                index++;
            }
            shortened[i] = table[index];
            index++;
        }
        return shortened;
    }

    static void fillTable(Document document, boolean printLetters) {
        float[] pointColumnWidths = new float[Crossword.width];
        Table table = new Table(pointColumnWidths);
        for (int y = 0; y < Crossword.height; y++) {

            for (int x = 0; x < Crossword.width; x++) {
                //table.addCell(String.valueOf(menu)+String.valueOf(i+y));
                if (Crossword.raster[x][y] == ' ') {
                    Cell cellMenu = new Cell();   // Creating a cell
                    String paraMenu = " ";
                    Paragraph paragraph10 = new Paragraph(paraMenu);
                    cellMenu.add(paragraph10);// Adding content to the cell
                    cellMenu.setWidth(20F);
                    cellMenu.setHeight(20F);
                    cellMenu.setBackgroundColor(ColorConstants.GRAY);
                    table.addCell(cellMenu);
                } else {
                    if (printLetters == true) {
                        table.addCell(String.valueOf(Crossword.raster[x][y]));
                    } else {
                        table.addCell(" ");
                    }
                }


            }
        }
        document.add(table);
    }
}
