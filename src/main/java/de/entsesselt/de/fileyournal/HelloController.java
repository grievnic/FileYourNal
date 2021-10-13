package de.entsesselt.de.fileyournal;

import de.entsesselt.de.fileyournal.controller.Generators;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        Generators.foToPdf();
    }
}