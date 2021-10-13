package de.entsesselt.de.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception {
        welcomeText.setText("Welcome to JavaFX Application!");
        try {
            Generators.foToPdf();
        } catch (IOException e) {
            System.out.println("IO-Problem");
        }
    }
}