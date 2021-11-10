package de.entsesselt.fileyournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;

public class FullPageViewController extends AbstractController{

    @FXML
    Button Button1;

    @FXML
    Button halfButton1;

    @FXML
    Button halfButton2;

    @FXML
    Button quadButton1;

    @FXML
    Button quadButton2;

    @FXML
    Button quadButton3;



    @FXML
    Button nextPageButton;

    String template = "full";

    String content1;

    /*Button activeButton;*/

    String contentName;

    @FXML
    ImageView fullPage;

    String imagePath;

    Button activeButton;

    String buttonId;

    private final static String FULLPATH = "assets/Content/ContentElements/fullPageContent/";
    private final static String HALFPATH = "assets/Content/ContentElements/halfPageContent/";
    private final static String FILENAME = "resources/assets/Content/ContentElements/fullPageContent/Dots.png";


    // Reference to the main application.
   /* private HelloApplication mainApp;*/


   @FXML
    public void selectArea(ActionEvent event) throws Exception {
        Button activeButton = (Button) event.getSource();
        mainApp.setActiveButton(activeButton);
        if (activeButton.getText().equals("halbe Seite")){
            showHalfTemplates();
       } else {
        showFullTemplates();
        }
   }

   // three buttons under the PageOverview
   @FXML
   public void savePage () throws Exception {
       System.out.println("Das Foto ist: " + content1);
       /*Page fullPage = new Page(template,content1);
       mainApp.addToOrganizer(fullPage.pageCreator());
       nextPageButton.setDisable(false);*/
   }

   @FXML
   public void nextPage(){
    mainApp.nextPage();
   }

    protected void showFullTemplates() throws Exception {
        mainApp.changeRightView("FullContentView.fxml");
    }

    protected void showHalfTemplates() throws Exception {
        mainApp.changeRightView("HalfContentView.fxml");
    }

    @FXML
    public void addContent(String contentName) throws Exception{
        this.contentName = contentName;
        ImageView imageView;
        activeButton = mainApp.getActiveButton();
        if (activeButton.getText().equals("halbe Seite")) {
            imagePath = HALFPATH + contentName + ".png";
            /*imageView = new ImageView(HALFPATH + contentName + ".png"); // Path to halfpage-Content*/
        } else {
            imagePath = FULLPATH + contentName + ".png";
            /*imageView = new ImageView(FULLPATH + contentName + ".png"); // Path to fullpage-Content*/
        }

            imageView = new ImageView(imagePath);
            activeButton.setGraphic(imageView);
            imageView.setFitHeight(activeButton.getHeight());
            imageView.setPreserveRatio(true);
            activeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            sendContent();
            System.out.println("Content 1 in FullPage ist: " + imagePath);

    }

    public void sendContent(){
       buttonId = activeButton.getId();
       System.out.println("Die ButtonID lautet" + buttonId);
       if (buttonId.matches("^.*.1")){
           System.out.println("YEAH");
           mainApp.setContent1(imagePath);
        }else if (buttonId.matches("^.*.2")){
            mainApp.setContent2(imagePath);
        }else if (buttonId.matches("^.*.3")){
           mainApp.setContent3(imagePath);
        }else {
           mainApp.setContent4(imagePath);
        }
       System.out.println("In sendButton:" + imagePath);
        System.out.println("Content 1 in Main ist: " + mainApp.getContent1());
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
