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


    public static boolean rowIsEmpty(char table[][], int rowIndex) {
        for (int columnIndex = 0; columnIndex < table.length; columnIndex++) {
            //       SPALTE, ZEILE
            if (table[i][index] != ' ') {
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
