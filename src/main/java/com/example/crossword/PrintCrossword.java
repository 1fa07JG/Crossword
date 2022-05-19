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
import java.util.ArrayList;

public class PrintCrossword {

    public static void producePdfCrossword(String dest, boolean solution) throws IOException {

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


        fillTable(document, solution);
        Paragraph questionParagraph = new Paragraph(PrintCrossword.giveQuestionList());

        document.add(questionParagraph);

        document.close();


        Desktop desktop = Desktop.getDesktop();
        File preview = new File(dest);
        desktop.open(preview);

        System.out.println("crossword.Crossword created");
    }

    //Helper für producePdfCrossword

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

    public static String giveQuestionList() {
        StringBuilder questionList = new StringBuilder("\n");

        ArrayList<Question> questionArrayList = PlayerApplication.questionArrayList;
        for (int i = 0; i < questionArrayList.size(); i++) {
            questionList.append(i + 1).append(". ").append(questionArrayList.get(i).getQuestion()).append(" ?\n");
        }
        return questionList.toString();
    }


    //Helper für fillTable:
    private static Paragraph createCustomerCell(int y, int x) {
        ArrayList<Question> questionArrayList = PlayerApplication.questionArrayList;
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < questionArrayList.size(); i++) {

            Question question = PlayerApplication.questionArrayList.get(i);
            Coordinates startingPosition = question.getStartingPosition();
            if ((startingPosition.getHorizontal() == x && startingPosition.getVertikal() == y)) {
                content.append(i + 1);
                if (question.isHorizontal) {
                    content.append("> ");
                } else {
                    content.append("v ");
                }
            }
        }
        Paragraph wordFieldParagraph = new Paragraph(content.toString());
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


}
