package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class PageFullViewController extends AbstractController{


    @FXML
    Rectangle fullGrey;

    @FXML
    ImageView fullPage;



    private final static String PATH = "@assets/Content/ContentElements/fullPageContent";


    // Reference to the main application.
    /*private HelloApplication mainApp;*/



   /* @FXML
    protected void showFullTemplates() throws Exception {
        fullGrey.setStrokeWidth(5);
        mainApp.changeRightView("FullContentView.fxml");
        //EventHandler als anonyme innere Klasse registrieren
        fullGrey.getOnMouseClicked(event -> selectCell(event));
    }*/


   /* public void addContent(String contentName){

        File contentFile = new File(PATH + contentName);
        Image contentImage = new Image(contentFile.toURI().toString());
        fullPage.setImage(contentImage);
    }*/

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    /*public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }*/
}
