package de.entsesselt.de.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class StartViewController {
    @FXML
    Button newPlaner;

    @FXML
    ImageView planer;

    @FXML
    ImageView page;

    // Reference to the main application.
    private HelloApplication mainApp;

    @FXML
    protected void startNewPlanerClick() throws Exception {
        mainApp.showRightView();
        newPlaner.setVisible(false);
        planer.setVisible(false);
        mainApp.showPageView();
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