package com.example.crossword.playground;

import com.example.crossword.Coordinates;
import com.example.crossword.Crossword;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PrintCrosswordPlayground {
    public static void main(String[] args) throws IOException {
        Crossword cw = new Crossword();
        cw.exampleSetup();
        //getAnswerRaster();
        //PrintCrosswordPlayground printCrossword = new PrintCrosswordPlayground();
        //printCrossword.producePdfCrossword(cw, "");
        printFieldWithLimits(new Coordinates(0, 0), new Coordinates(60, 20), cw);
        System.out.println("Spalte dreißig is leer: " + columnIsEmpty(cw.getRaster(), 30));
        System.out.println("Spalte elf is leer: " + columnIsEmpty(cw.getRaster(), 11));
        System.out.println("Start Char Y: " + findStartY(cw));
        System.out.println("Start Char X: " + findStartX(cw));
        System.out.println("Stop Char Y: " + findStopY(cw));
        System.out.println("Stop Char X: " + findStopX(cw));
        printFieldWithLimits(getStartCoordinates(cw), getStopCoordinates(cw), cw);
        producePdfCrossword(cw, "");
    }

    public static Coordinates getStartCoordinates(Crossword cw) {
        return new Coordinates(findStartX(cw), findStartY(cw));
    }

    public static Coordinates getStopCoordinates(Crossword cw) {
        return new Coordinates(findStopX(cw), findStopY(cw));
    }


    public static int findStartY(Crossword crossword) {
        char[][] table = crossword.getRaster();
        for (int i = 0; i < crossword.getHeight(); i++) {
            if (rowIsEmpty(table, i)) {
                return i;
            }
        }
        return crossword.getHeight();
    }

    public static int findStartX(Crossword crossword) {
        char[][] table = crossword.getRaster();
        for (int i = 0; i < crossword.getWidth(); i++) {
            if (!columnIsEmpty(table, i)) {
                return i;
            }
        }
        return crossword.getWidth();
    }

    public static int findStopY(Crossword crossword) {
        int temporaryColumnIndex = 0;
        char[][] table = crossword.getRaster();
        for (int i = 0; i < crossword.getHeight(); i++) {
            if (rowIsEmpty(table, i)) {
                temporaryColumnIndex = i;
            }
        }
        return temporaryColumnIndex;
    }

    public static int findStopX(Crossword crossword) {
        int temporaryRowIndex = 0;
        char[][] table = crossword.getRaster();
        for (int i = 0; i < crossword.getWidth(); i++) {
            if (!columnIsEmpty(table, i)) {
                temporaryRowIndex = i;
            }
        }
        return temporaryRowIndex;
    }

    //finde end und start koordinaten mit methoden

    private static void fillTable(Crossword cw, Table table, boolean printLetters) {

        for (int y = findStartY(cw); y < findStopY(cw); y++) {

            for (int x = findStartX(cw); x < findStopX(cw); x++) {
                //table.addCell(String.valueOf(menu)+String.valueOf(i+y));
                if (cw.getRaster()[x][y] == ' ') {
                    Cell cellMenu = new Cell();   // Creating a cell
                    String paraMenu = " ";
                    Paragraph paragraph10 = new Paragraph(paraMenu);
                    cellMenu.add(paragraph10);// Adding content to the cell
                    cellMenu.setWidth(20F);
                    cellMenu.setHeight(20F);
                    cellMenu.setBackgroundColor(ColorConstants.GRAY);
                    table.addCell(cellMenu);
                } else {
                    if (printLetters) {
                        table.addCell(String.valueOf(cw.getRaster()[x][y]));
                    } else {
                        table.addCell(" ");
                    }
                }


            }
        }
    }

    public static void printFieldWithLimits(Coordinates start, Coordinates stop, Crossword crossword) {
// Koordinaten zwangsweise an die Größe des Felds anpassen.
        System.out.print("  ");
        for (int x = start.getHorizontal(); x < stop.getHorizontal(); x++) {
            System.out.print("|" + x);

        }
        System.out.println();
        for (int y = start.getVertikal(); y < stop.getVertikal(); y++) {
            if (y < 10) {
                System.out.print(' ');
            }
            System.out.print(y);

            for (int x = start.getHorizontal(); x < stop.getHorizontal(); x++) {
                System.out.print("|" + crossword.getRaster()[x][y]);
                if (x >= 10) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }


    public static boolean rowIsEmpty(char[][] table, int rowIndex) {
        for (int columnIndex = 0; columnIndex < table.length; columnIndex++) {
            //       SPALTE, ZEILE
            if (table[columnIndex][rowIndex] != ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean columnIsEmpty(char[][] table, int columnIndex) {
        for (int rowIndex = 0; rowIndex < table[0].length; rowIndex++) {
            if (table[columnIndex][rowIndex] != ' ') {
                return false;
            }
        }
        return true;
    }

    public static void producePdfCrossword(Crossword cw, String dest) throws IOException {

        //cw.raster = trimTable(cw.raster);
        Crossword.printField();//wird behalten um fehler finden zu können
        if (dest.equals("")) {
            dest = "./crossword.Crossword.pdf";
        }

        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A3.rotate());

        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);


        float[] pointColumnWidths = new float[findStopX(cw) - findStartX(cw)];

        Table table = new Table(pointColumnWidths);

        fillTable(cw, table, true);

        document.add(table);
        document.close();


        Desktop desktop = Desktop.getDesktop();
        File preview = new File(dest);
        desktop.open(preview);

        System.out.println("crossword.Crossword created");
    }


}
