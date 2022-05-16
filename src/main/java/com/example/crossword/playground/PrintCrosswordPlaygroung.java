package com.example.crossword.playground;

import com.example.crossword.Coordinates;
import com.example.crossword.Crossword;
import com.example.crossword.PrintCrossword;
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

public class PrintCrosswordPlaygroung {
    public static void main(String[] args) throws IOException {
        Crossword cw = new Crossword();
        cw.exampleSetup();
        //getAnwserRaster();
        //PrintCrosswordPlaygroung printCrossword = new PrintCrosswordPlaygroung();
        //printCrossword.producePdfCrossword(cw, "");
        printFieldWithLimits(new Coordinates(2,2),new Coordinates(2,2),cw);
    }

    public static int findStartY(){}

    //finde end und start koordinaten mit methoden



    public static void printFieldWithLimits(Coordinates start,Coordinates stop,Crossword crossword) {
// coordinaten zwangsweise an die Größe des Felds anpassen.
        for (int y = start.getVertikal(); y < stop.getVertikal(); y++) {
            for (int x = start.getHorizontal(); x < stop.getHorizontal(); x++) {
                System.out.print("|" + crossword.getRaster()[x][y]);
            }
            System.out.println();
        }
    }

    public static void producePdfCrossword(Crossword cw, String dest) throws IOException, FileNotFoundException {

        cw.setRaster(trimTable(cw.getRaster()));
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
        float[] pointColumnWidths = new float[cw.getWidth()];
        for (float cellLength : pointColumnWidths) {
            cellLength = 40F;
        }
        Table table = new Table(pointColumnWidths);

        fillTable(cw, table, false);

        document.add(table);
        document.close();


        Desktop desktop = Desktop.getDesktop();
        File preview = new File(dest);
        desktop.open(preview);

        System.out.println("crossword.Crossword created");
    }

    private static void fillTable(Crossword cw, Table table, boolean printLetters) {
        for (int y = 0; y < cw.getHeight(); y++) {

            for (int x = 0; x < cw.getWidth(); x++) {
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
                    if (printLetters == true) {
                        table.addCell(String.valueOf(cw.getRaster()[x][y]));
                    } else {
                        table.addCell(" ");
                    }
                }


            }
        }
    }

    public static char[][] trimTable(char table[][]) {
        int rowIndex = table.length;

        for (int row = 0; row < rowIndex; row++) {
            if (rowIsEmpty(table, row)) {
                rowIndex = rowIndex - 1;
                table = deleteOneRow(table, row);
            }
        }
        for (int i = 0; i < table[0].length; i++) {
            if (colummIsEmpty(table, i)) {

            }
        }
        return table;
    }


    public static boolean rowIsEmpty(char table[][], int index) {
        for (int i = 0; i < table[0].length; i++) {
            //       SPALTE, ZEILE
            if (table[i][index] != ' ') {
                return false;
            }
        }
        return true;
    }

    public static char[][] deleteOneRow(char table[][], int toDeleteIndex) {
        char[][] shortend = new char[table.length - 1][table[0].length];
        int index = 0;
        for (int i = 0; i < table.length - 1; i++) {
            if (i == toDeleteIndex) {
                index++;
            }
            shortend[i] = table[index];
            index++;
        }
        return shortend;
    }

    public static boolean colummIsEmpty(char table[][], int index) {
        for (int i = 0; i < table.length; i++) {
            if (table[index][i] != ' ') {
                return false;
            }
        }
        return true;
    }

    public static char[][] deleteOneColumm(char table[][], int toDeleteIndex) {
        char[][] shortend = new char[table.length][table[0].length - 1];
        int index = 0;
        for (int i = 0; i < table[0].length; i++) {
            if (i == toDeleteIndex) {
                index++;
            }
            shortend[i] = table[index];
            index++;
        }
        return shortend;
    }
}
