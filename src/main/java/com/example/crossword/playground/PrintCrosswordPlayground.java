package com.example.crossword.playground;

import com.example.crossword.*;
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
import java.io.IOException;

public class PrintCrosswordPlayground {
    public static void main(String[] args) throws IOException {
        Crossword.exampleSetup();
        printFieldWithLimits(new Coordinates(0, 0), new Coordinates(60, 20));
        System.out.println("Spalte dreißig is leer: " + columnIsEmpty(Crossword.getRaster(), 30));
        System.out.println("Spalte elf is leer: " + columnIsEmpty(Crossword.getRaster(), 11));
        System.out.println("Start Char Y: " + Helper.findStartY());
        System.out.println("Start Char X: " + Helper.findStartX());
        System.out.println("Stop Char Y: " + Helper.findStopY());
        System.out.println("Stop Char X: " + Helper.findStopX());
        printFieldWithLimits(Helper.getStartCoordinates(), Helper.getStopCoordinates());
        producePdfCrossword("");
        System.out.println(Question.findWord("YEAR"));
    }


    //finde end und start koordinaten mit methoden

    public static void printFieldWithLimits(Coordinates start, Coordinates stop) {
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
                System.out.print("|" + Crossword.getRaster()[x][y]);
                if (x >= 10) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    private static void fillTable(Document document, boolean printLetters) {
        float[] pointColumnWidths = new float[Helper.findStopX() - Helper.findStartX()];

        Table table = new Table(pointColumnWidths);

        for (int y = Helper.findStartY(); y < Helper.findStopY(); y++) {

            for (int x = Helper.findStartX(); x < Helper.findStopX(); x++) {
                //table.addCell(String.valueOf(menu)+String.valueOf(i+y));
                if (Crossword.getRaster()[x][y] == ' ') {
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
                        table.addCell(String.valueOf(Crossword.getRaster()[x][y]));
                    } else {
                        table.addCell(" ");
                    }
                }


            }
        }
        document.add(table);
    }

    public static String giveQuestionList() {
        String questionList = "\n";
        Question[] questionArray = PlayerApplication.getQuestionList();
        for (int i = 0; i < questionArray.length; i++) {
            questionList = questionList + (i + 1) + ". " + questionArray[i].getQuestion() + " ?\n";
        }
        return questionList;
    }


    public static boolean rowIsEmpty(char[][] table, int rowIndex) {
        for (char[] chars : table) {
            //       SPALTE, ZEILE
            if (chars[rowIndex] != ' ') {
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

    public static void producePdfCrossword(String dest) throws IOException {

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


        fillTable(document, true);

        Paragraph questionParagraph = new Paragraph(giveQuestionList());

        document.add(questionParagraph);

        document.close();


        Desktop desktop = Desktop.getDesktop();
        File preview = new File(dest);
        desktop.open(preview);

        System.out.println("crossword.Crossword created");
    }


}
