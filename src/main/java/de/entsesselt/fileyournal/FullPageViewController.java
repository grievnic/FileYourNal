package de.entsesselt.fileyournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;

public class FullPageViewController extends AbstractController{

    @FXML
    Button fullButton;

    /*Button activeButton;*/

    String contentName;

    @FXML
    ImageView fullPage;

    private final static String PATH = "assets/Content/ContentElements/fullPageContent/";
    private final static String FILENAME = "resources/assets/Content/ContentElements/fullPageContent/Dots.png";

    // Reference to the main application.
   /* private HelloApplication mainApp;*/



    public void selectArea(ActionEvent event) throws Exception {
        Button activeButton = (Button) event.getSource();
        mainApp.setActiveButton(activeButton);
        showFullTemplates();
    }

    protected void showFullTemplates() throws Exception {
        mainApp.changeRightView("FullContentView.fxml");
    }

    @FXML
    public void addContent(String contentName) throws Exception{
        this.contentName = contentName;
        System.out.println("in addContent " + contentName);
        System.out.println(PATH + contentName + ".png");
        /*File contentFile = new File(PATH + contentName + ".png");*/


        /*Image contentImage = new Image(FILENAME);*/
        /*ImageView contentImageView = new ImageView(contentImage);*/


        Button activeButton = mainApp.getActiveButton();
        System.out.println("activeButton is " + activeButton);
        /*System.out.println("contentImageView is " + contentImageView.toString());*/


        /*System.out.println(contentFile);*/


        /*activeButton.setText("");*/

        /*ImageView imageView = new ImageView(getClass().getResource(PATH + "Dots.png").toExternalForm());*/
        /*activeButton.setGraphic(imageView);*/
        /*Image contentImage = new Image(PATH + contentName + ".png");*/
        ImageView imageView = new ImageView(PATH + contentName + ".png");


        /*imageView.fitHeightProperty().bind(activeButton.heightProperty());
        imageView.fitWidthProperty().bind(activeButton.widthProperty());*/


        activeButton.setGraphic(imageView);
        imageView.setFitHeight(activeButton.getHeight());
        imageView.setPreserveRatio(true);
        activeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        /*activeButton.setGraphic(new Button("TEST"));*/
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
