package com.example.crossword;

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
import java.util.Objects;

public class PrintCrossword {
    public static void producePdfCrossword(String dest) throws IOException {

        //cw.raster = trimTable(cw.raster);
        Crossword.printField();//wird behalten um fehler finden zu können
        if (dest.equals("")) {
            dest = "./crossword.Crossword.pdf";
        }

        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());

        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);


        fillTable(document, false);


        document.close();


        Desktop desktop = Desktop.getDesktop();
        File preview = new File(dest);
        desktop.open(preview);

        System.out.println("crossword.Crossword created");
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

    public static void fillTable(Document document, boolean printLetters) {
        float[] pointColumnWidths = new float[Helper.findStopX() - Helper.findStartX()];

        Table table = new Table(pointColumnWidths);

        for (int y = Helper.findStartY(); y < Helper.findStopY(); y++) {

            for (int x = Helper.findStartX(); x < Helper.findStopX(); x++) {
                //table.addCell(String.valueOf(menu)+String.valueOf(i+y));
                if (Crossword.getRaster()[x][y] == ' ') {
                    Cell charCell = new Cell();   // Creating a cell
                    String paraMenu = " ";
                    Paragraph paragraph10 = new Paragraph(paraMenu);
                    charCell.add(paragraph10);// Adding content to the cell
                    charCell.setWidth(20F);
                    charCell.setHeight(20F);
                    charCell.setBackgroundColor(ColorConstants.GRAY);
                    table.addCell(charCell);
                } else {
                    if (printLetters) {
                        table.addCell(String.valueOf(Crossword.getRaster()[x][y]));
                    } else {
                        String content = "";
                        for (int i = 0; i < PlayerApplication.getQuestionList().length; i++) {
                            if ((PlayerApplication.getQuestionList()[i].getStartingPosition().getHorizontal() == x && PlayerApplication.getQuestionList()[i].getStartingPosition().getVertikal() == y)) {
                                content = content + (i + 1);
                            }
                        }
                        table.addCell(content);
                    }
                }


            }

        }
        document.add(table);
    }
}
