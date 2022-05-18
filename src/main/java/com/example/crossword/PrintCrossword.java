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
        Paragraph questionParagraph = new Paragraph(PrintCrossword.giveQuestionList());

        document.add(questionParagraph);

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
        float[] pointColumnWidths = new float[Helper.findStopX() - Helper.findStartX() + 1];

        Table table = new Table(pointColumnWidths);

        for (int y = Helper.findStartY(); y <= Helper.findStopY(); y++) {

            for (int x = Helper.findStartX(); x <= Helper.findStopX(); x++) {
                if (Crossword.getRaster()[x][y] == ' ') {
                    table.addCell(createEmptyCell());
                } else if (printLetters) {
                    table.addCell(String.valueOf(Crossword.getRaster()[x][y]));
                } else {
                    table.addCell(createCustomerCell(y, x));
                }


            }

        }
        document.add(table);
    }

    private static Paragraph createCustomerCell(int y, int x) {
        String content = "";
        for (int i = 0; i < PlayerApplication.getQuestionList().length; i++) {
            if ((PlayerApplication.getQuestionList()[i].getStartingPosition().getHorizontal() == x && PlayerApplication.getQuestionList()[i].getStartingPosition().getVertikal() == y)) {
                content = content + (i + 1);
                if (PlayerApplication.getQuestionList()[i].isHorizontal == true) {
                    content = content + "> ";
                } else {
                    content = content + "v ";
                }
            }
        }
        Paragraph wordFieldParagraph = new Paragraph(content);
        wordFieldParagraph.setFontSize(5);
        return wordFieldParagraph;
    }

    private static Cell createEmptyCell() {
        Cell charCell = new Cell();   // Creating a cell
        String paraMenu = " ";
        Paragraph paragraph10 = new Paragraph(paraMenu);
        charCell.add(paragraph10);// Adding content to the cell
        charCell.setWidth(20F);
        charCell.setHeight(20F);
        charCell.setBackgroundColor(ColorConstants.GRAY);
        return charCell;
    }

    public static String giveQuestionList() {
        String questionList = "\n";
        Question[] questionArray = PlayerApplication.getQuestionList();
        for (int i = 0; i < questionArray.length; i++) {
            questionList = questionList + (i + 1) + ". " + questionArray[i].getQuestion() + " ?\n";
        }
        return questionList;
    }
}
