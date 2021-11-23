package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Page;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class PlanerViewController extends AbstractController{


    @FXML
    Button nextPageButton;

    @FXML
    Button prevPageButton;

    @FXML
    Label pageLabel;

    @FXML
    Button modifyClick;

    @FXML
    Button planerButton1;

    @FXML
    Button planerHalfButton1;

    @FXML
    Button planerHalfButton2;



    @FXML
    Button planerQuadButton1;

    @FXML
    Button planerQuadButton2;

    @FXML
    Button planerQuadButton3;

    @FXML
    Button planerQuadButton4;

    @FXML
    Button planerHalfQuadButton1;

    @FXML
    Button planerHalfQuadButton2;

    @FXML
    Button planerHalfQuadButton3;

    @FXML
    Button planerQuadHalfButton1;

    @FXML
    Button planerQuadHalfButton2;

    @FXML
    Button planerQuadHalfButton3;

    @FXML
    Button previousButton;

    @FXML
    ImageView full1;

  /*  @FXML
    Label label;
*/
    @FXML
    Button takeButton;

    String template;

    String content1;
    String content2;
    String content3;
    String content4;

    String contentName;

    String imagePath;

    Button activeButton;

    String buttonId;

    @FXML
    private AnchorPane fullPane;

    @FXML
    private AnchorPane halfPane;

    @FXML
    private AnchorPane quadPane;

    @FXML
    private AnchorPane halfQuadPane;

    @FXML
    private AnchorPane quadHalfPane;

    private final static String FULLPATH = "assets/Content/ContentElements/fullPageContent/";
    private final static String HALFPATH = "assets/Content/ContentElements/halfPageContent/";


    // Reference to the main application.
    /* private HelloApplication mainApp;*/


    @FXML
    public void modifyPage(){
        System.out.println("Hier würde irgendwann eine Änderung passieren");
    }

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

    @FXML
    public void setFullVisible(){
        fullPane.setVisible(true);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/

    }

    @FXML
    public void setHalfVisible(){
        halfPane.setVisible(true);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/
    }

    @FXML
    public void setQuadVisible(){
        quadPane.setVisible(true);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/
    }

    @FXML
    public void setQuadHalfVisible(){
        quadHalfPane.setVisible(true);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/
    }

    @FXML
    public void setHalfQuadVisible(){
        halfQuadPane.setVisible(true);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/
    }

    protected void showFullTemplates() throws Exception {
        mainApp.changeRightView("FullContentView.fxml");
    }

    protected void showHalfTemplates() throws Exception {
        mainApp.changeRightView("HalfContentView.fxml");
    }

    // three buttons under PageOverview

    @FXML
    public void previousPage() throws Exception { mainApp.prevPage();}

    @FXML
    public void nextPage()throws Exception{
        mainApp.nextPage();
    }
    @FXML
    public void savePage () throws Exception {
        content1 = mainApp.getContent1();
        content2 = mainApp.getContent2();
        content3 = mainApp.getContent3();
        content4 = mainApp.getContent4();
        template = mainApp.getCurrentTemplate();
        // alert
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Es müssen alle Seitenelemente befüllt sein!");
        //Are all Page-Elements filled with content?
        if (template.equals("fullpage") & content1.isEmpty()){
            a.show();
        }else if (template.equals("half") && content1.isEmpty() | content2.isEmpty()){
            a.show();
        }else if (template.equals("halfQuad") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty() ){
            a.show();
        }else if (template.equals("quadHalf") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty()){
            a.show();
        }else if (template.equals("quad") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty() | content4.isEmpty()){
            a.show();
        }else { // creates a new JDOM-Page-Element and writes it into the XSL-FO tree
            Page fullPage = new Page(template, content1, content2, content3, content4);
            mainApp.addToOrganizer(fullPage.pageCreator());
            mainApp.newPage(); // shows a empty page and shows the template-overview at the right side
        }

    }

    @FXML
    public void setPrevButton(Boolean bool){
        prevPageButton.setDisable(bool);
    }

    @FXML
    public void setNextButton(Boolean bool){
        nextPageButton.setDisable(bool);
    }

    @FXML
    public void loadPage(String template, String  content1, String content2, String content3, String content4, int pageindex) throws Exception {
        this.content1 = (content1);
        this.content2 = (content2);
        this.content3 = (content3);
        this.content4 = (content4);
        pageLabel.setText(String.valueOf(pageindex + 1));
        System.out.println("aus PlanerController loadPage(): " + content1 + content2 + content3 + content4);
        if (template.equals("fullpage")){
            mainApp.showPageTemplate("planerViewController", "fullpage");
            String imagePath = FULLPATH + content1;
            System.out.println(imagePath);
            Image image1 = new Image(imagePath);
            full1.setImage(image1);
            /*planerButton1.setVisible(false);*/
            /*ImageView imageView1 = new ImageView(imagePath);
            *//*planerButton1.setGraphic(imageView1);*//*
            imageView1.setFitHeight(planerButton1.getHeight());
            System.out.println("PlanerButton1: "+ planerButton1.getHeight());
            imageView1.setPreserveRatio(true);
            planerButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);*/
            System.out.println("fullPage wird abgebildet");
        } else if (mainApp.getCurrentTemplate().equals("half")){
            mainApp.showPageTemplate("planerViewController","half");
            System.out.println("half erkannt"+ planerHalfButton1.getText());
            ImageView imageView1 = new ImageView(HALFPATH + content1);
            ImageView imageView2 = new ImageView(HALFPATH + content2);
            // sets the content in the top half
            planerHalfButton1.setGraphic(imageView1);
            imageView1.setFitHeight(planerHalfButton1.getHeight());
            imageView1.setPreserveRatio(true);
            planerHalfButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the bottom half
            planerHalfButton2.setGraphic(imageView2);
            imageView2.setFitHeight(planerHalfButton2.getHeight());
            imageView2.setPreserveRatio(true);
            planerHalfButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else if (mainApp.getCurrentTemplate().equals("quad")){
            mainApp.showPageTemplate("planerViewController", "quad");
            System.out.println("quad erkannt" + planerQuadButton1.getText());
            ImageView imageView1 = new ImageView(FULLPATH + content1);
            ImageView imageView2 = new ImageView(FULLPATH + content2);
            ImageView imageView3 = new ImageView(FULLPATH + content3);
            ImageView imageView4 = new ImageView(FULLPATH + content4);
            // sets the content in the left top quarter
            planerQuadButton1.setGraphic(imageView1);
            imageView1.setFitHeight(planerQuadButton1.getHeight());
            imageView1.setPreserveRatio(true);
            planerQuadButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right top quarter
            planerQuadButton2.setGraphic(imageView2);
            imageView2.setFitHeight(planerQuadButton2.getHeight());
            imageView2.setPreserveRatio(true);
            planerQuadButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the left bottom quarter
            planerQuadButton3.setGraphic(imageView2);
            imageView3.setFitHeight(planerQuadButton3.getHeight());
            imageView3.setPreserveRatio(true);
            planerQuadButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right bottom quarter
            planerQuadButton4.setGraphic(imageView4);
            imageView4.setFitHeight(planerQuadButton4.getHeight());
            imageView4.setPreserveRatio(true);
            planerQuadButton4.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else if (mainApp.getCurrentTemplate().equals("halfquad")){
            mainApp.showPageTemplate("planerViewController", "halfQuad");
            ImageView imageView1 = new ImageView(HALFPATH + content1);
            ImageView imageView2 = new ImageView(FULLPATH + content2);
            ImageView imageView3 = new ImageView(FULLPATH + content3);
            // sets the content in the top half
            planerHalfQuadButton1.setGraphic(imageView1);
            imageView1.setFitHeight(planerHalfQuadButton1.getHeight());
            imageView1.setPreserveRatio(true);
            planerHalfQuadButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the left bottom quarter
            planerHalfQuadButton2.setGraphic(imageView2);
            imageView2.setFitHeight(planerHalfQuadButton2.getHeight());
            imageView2.setPreserveRatio(true);
            planerHalfQuadButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right bottom quarter
            planerHalfQuadButton3.setGraphic(imageView3);
            imageView3.setFitHeight(planerHalfQuadButton3.getHeight());
            imageView3.setPreserveRatio(true);
            planerHalfQuadButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else {
            mainApp.showPageTemplate("planerViewController", "quadHalf");
            System.out.println("quadhalf erkannt");
            ImageView imageView1 = new ImageView(FULLPATH + content1);
            ImageView imageView2 = new ImageView(FULLPATH + content2);
            ImageView imageView3 = new ImageView(HALFPATH + content3);
            // sets the content in the left top quarter
            planerQuadHalfButton1.setGraphic(imageView1);
            imageView1.setFitHeight(planerQuadHalfButton1.getHeight());
            imageView1.setPreserveRatio(true);
            planerQuadHalfButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right top quarter
            planerQuadHalfButton2.setGraphic(imageView2);
            imageView2.setFitHeight(planerQuadHalfButton2.getHeight());
            imageView2.setPreserveRatio(true);
            planerQuadHalfButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the bottom half
            planerQuadHalfButton3.setGraphic(imageView3);
            imageView3.setFitHeight(planerQuadHalfButton3.getHeight());
            imageView3.setPreserveRatio(true);
            planerQuadHalfButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    @FXML
    public void addContent(String contentName) throws Exception{
        this.contentName = contentName;
        ImageView imageView;
        activeButton = mainApp.getActiveButton();
        if (activeButton.getText().equals("halbe Seite")) { //identifies format of the page area : landscape
            imagePath = HALFPATH + contentName + ".png";
            /*imageView = new ImageView(HALFPATH + contentName + ".png"); // Path to halfpage-Content*/
        } else {
            imagePath = FULLPATH + contentName + ".png"; //format of the page area : portrait
            /*imageView = new ImageView(FULLPATH + contentName + ".png"); // Path to fullpage-Content*/
        }
        imageView = new ImageView(imagePath);
        activeButton.setGraphic(imageView);
        imageView.setFitHeight(activeButton.getHeight());
        imageView.setPreserveRatio(true);
        activeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        sendContent();
    }

    // Assignment of the content linked to the buttons
    public void sendContent(){
        buttonId = activeButton.getId();
        if (buttonId.matches("^.*.1")){ // if button has identifier 1
            mainApp.setContent1(contentName); // ... its content is Content1
        }else if (buttonId.matches("^.*.2")){
            mainApp.setContent2(contentName);
        }else if (buttonId.matches("^.*.3")){
            mainApp.setContent3(contentName);
        }else {
            mainApp.setContent4(contentName);
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



