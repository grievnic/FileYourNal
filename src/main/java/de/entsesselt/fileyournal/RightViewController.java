package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RightViewController extends AbstractController {

    @FXML
    AnchorPane pageAreaTemplatePane;

    @FXML
    Label pdfPathLabel;

    @FXML
    Text pdfFeedbackText;


    String template = "";
    // Reference to the main application.
    /*private HelloApplication mainApp;*/

    /*@FXML*/
    /*public void showTemplateView(){
        templateViewPane.setVisible();
    }*/

    //shows the QuadQuad-Page-View in the Center of the GUI and takes the template-Selection away
    @FXML
    protected void startQuadQuadTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "quad");
        template = "quad";
        sendTemplate();
        emptyRightView();
    }

    //shows the Full-Page-View in the Center of the GUI and takes the template-Selection away
    @FXML
    protected void startFullTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "fullpage");
        template = "full";
        sendTemplate();
        emptyRightView();
    }

    //shows the Half-Page-View in the Center of the GUI and takes the template-Selection away
    @FXML
    protected void startHalfHalfTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "half");
        template = "half";
        sendTemplate();
        emptyRightView();
    }

    @FXML
    protected void startQuadHalfTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "quadHalf");
        template = "quadHalf";
        sendTemplate();
        emptyRightView();
    }

    @FXML
    protected void startHalfQuadTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "halfQuad");
        template = "halfQuad";
        sendTemplate();
        emptyRightView();
    }

    public void hidePageAreaTemplatePane(){
        mainApp.showRightView();
        pageAreaTemplatePane.setVisible(false);
    }

    @FXML
    public void emptyIt(){
        pageAreaTemplatePane.setVisible(false);
    }

    /*public void pdfFeedback(String path){
        *//*pdfFeedbackText.setVisible(true);
        pdfPathLabel.setText(path);*//*
    }*/

    private void sendTemplate(){
        mainApp.setCurrentTemplate(template);
    }

    // takes the template-Selection away
    public void emptyRightView() throws Exception{
        mainApp.changeRightView("EmptyRightView.fxml");
    }

    public void showTemplateView(){
        mainApp.showRightView();
        emptyIt();
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
