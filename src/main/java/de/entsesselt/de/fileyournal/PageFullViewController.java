package de.entsesselt.de.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;


public class PageFullViewController {


    @FXML
    Rectangle fullGrey;


    // Reference to the main application.
    private HelloApplication mainApp;

    @FXML
    protected void showFullTemplates() throws Exception {
        fullGrey.setStrokeWidth(5);
        mainApp.changeRightView("FullContentView.fxml");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }
}
