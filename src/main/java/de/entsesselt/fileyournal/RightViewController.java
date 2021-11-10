package de.entsesselt.fileyournal;

import javafx.fxml.FXML;

public class RightViewController extends AbstractController {

    // Reference to the main application.
    /*private HelloApplication mainApp;*/

    //shows the QuadQuad-Page-View in the Center of the GUI and takes the template-Selection away
    @FXML
    protected void startQuadQuadTemplate() throws Exception {
        mainApp.showQuadQuadPageView();
        mainApp.setCurrentTemplate("quadquad");
        emptyRightView();
        // TODO lade das QuadQuad-XML-Seiten-Template
    }

    //shows the Full-Page-View in the Center of the GUI and takes the template-Selection away
    @FXML
    protected void startFullTemplate() throws Exception {
        mainApp.showFullPageView();
        mainApp.setCurrentTemplate("full");
        emptyRightView();
        // TODO lade das FullPage-XML-Seiten-Template
    }

    //shows the Half-Page-View in the Center of the GUI and takes the template-Selection away
    @FXML
    protected void startHalfHalfTemplate() throws Exception {
        mainApp.showHalfHalfPageView();
        mainApp.setCurrentTemplate("halfhalf");
        emptyRightView();
        // TODO lade das FullPage-XML-Seiten-Template
    }

    @FXML
    protected void startQuadHalfTemplate() throws Exception {
        mainApp.showQuadHalfPageView();
        mainApp.setCurrentTemplate("quadhalf");
        emptyRightView();
        // TODO lade das FullPage-XML-Seiten-Template
    }

    @FXML
    protected void startHalfQuadTemplate() throws Exception {
        mainApp.showHalfQuadPageView();
        mainApp.setCurrentTemplate("halfquad");
        emptyRightView();
        // TODO lade das FullPage-XML-Seiten-Template
    }

    // takes the template-Selection away
    public void emptyRightView(){
        mainApp.changeRightView("EmptyRightView.fxml");
    }

    public void showTemplateView(){
        mainApp.showRightView();
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    /*public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }*/

}
