package de.entsesselt.fileyournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FullContentViewController extends AbstractController{

    // Reference to the main application.
    /*private HelloApplication mainApp;*/
    @FXML
    Button selectedTemplate;
    String contentName;


    @FXML
    public void selectContent(ActionEvent event) throws Exception {
        /*selectedTemplate = (Button) event.getSource();*/
        contentName = ((Button) event.getSource()).getText();
        System.out.println("in selectContent " + contentName);
        loadContent(contentName);

    }
    //shows the Full-Page-View in the Center of the GUI
    @FXML
    public void loadContent(String contentName) throws Exception {
        FullPageViewController control = mainApp.getPageViewController();

        /*control.setMainApp(mainApp);*/
        System.out.println("in loadContent " + contentName);
        control.addContent(contentName);
        System.out.println("in loadContent nach addContent-Aufruf  " + contentName);
    }


    public void backToTemplate(){
        mainApp.showRightView();
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
   /* public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }*/
}
