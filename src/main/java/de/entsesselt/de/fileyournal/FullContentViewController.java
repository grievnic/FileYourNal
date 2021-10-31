package de.entsesselt.de.fileyournal;

import javafx.fxml.FXML;

public class FullContentViewController {

    // Reference to the main application.
    private HelloApplication mainApp;

    //shows the QuadQuad-Page-View in the Center of the GUI and takes the template-Selection away
    @FXML
    protected void loadContent() throws Exception {

        // TODO lade das QuadQuad-XML-Seiten-Template
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
