package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Page;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    String template;

    String content1;
    String content2;
    String content3;
    String content4;

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
    }

    public void sendContent(){
       buttonId = activeButton.getId();
       if (buttonId.matches("^.*.1")){
           mainApp.setContent1(contentName);
        }else if (buttonId.matches("^.*.2")){
            mainApp.setContent2(contentName);
        }else if (buttonId.matches("^.*.3")){
           mainApp.setContent3(contentName);
        }else {
           mainApp.setContent4(contentName);
        }
    }

    @FXML
    public void savePage () throws Exception {
       content1 = mainApp.getContent1();
       content2 = mainApp.getContent2();
       content3 = mainApp.getContent3();
       content4 = mainApp.getContent4();
       template = mainApp.getCurrentTemplate();
        // create a alert
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Es müssen alle Seitenelemente befüllt sein!");
        //Are all Page-Elements filled with content?
        if (template.equals("full") & content1.isEmpty()){
            a.show();
        }else if (template.equals("half") && content1.isEmpty() | content2.isEmpty()){
            a.show();
        }else if (template.equals("halfQuad") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty() ){
            a.show();
        }else if (template.equals("quadHalf") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty()){
            a.show();
        }else if (template.equals("quad") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty() | content4.isEmpty()){
            a.show();
        }else {
            Page fullPage = new Page(template, content1, content2, content3, content4);
            mainApp.addToOrganizer(fullPage.pageCreator());
            mainApp.showPageView();
            mainApp.showRightView();
        }
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
