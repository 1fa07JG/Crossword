package com.example.crossword;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class GuiController {
    @FXML
    private Label PrintStatusText;

    @FXML
    protected void onPrintButtonClick() throws IOException {
        PrintStatusText.setText("Crossword has been printed to Pdf");
        Crossword.exampleSetup();
        PrintCrossword.producePdfCrossword("", false);
    }
}