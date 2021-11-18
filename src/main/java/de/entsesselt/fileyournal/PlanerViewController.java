package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Page;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class PlanerViewController extends AbstractController{


    @FXML
    Button nextPageButton;

    @FXML
    Button prevPageButton;

    @FXML
    Button modifyClick;

    @FXML
    Button button1;

    @FXML
    Button halfButton1;

    @FXML
    Button halfButton2;

    @FXML
    Button halfButton3;

    @FXML
    Button quadButton1;

    @FXML
    Button quadButton2;

    @FXML
    Button quadButton3;

    @FXML
    Button quadButton4;

    @FXML
    Button halfQuadButton1;

    @FXML
    Button halfQuadButton2;

    @FXML
    Button halfQuadButton3;

    @FXML
    Button quadHalfButton1;

    @FXML
    Button quadHalfButton2;

    @FXML
    Button quadHalfButton3;

    @FXML
    Button previousButton;

    @FXML
    Label label;

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
        label.setVisible(false);
        takeButton.setDisable(false);

    }

    @FXML
    public void setHalfVisible(){
        halfPane.setVisible(true);
        label.setVisible(false);
        takeButton.setDisable(false);
    }

    @FXML
    public void setQuadVisible(){
        quadPane.setVisible(true);
        label.setVisible(false);
        takeButton.setDisable(false);
    }

    @FXML
    public void setQuadHalfVisible(){
        quadHalfPane.setVisible(true);
        label.setVisible(false);
        takeButton.setDisable(false);
    }

    @FXML
    public void setHalfQuadVisible(){
        halfQuadPane.setVisible(true);
        label.setVisible(false);
        takeButton.setDisable(false);
    }

    protected void showFullTemplates() throws Exception {
        mainApp.changeRightView("FullContentView.fxml");
    }

    protected void showHalfTemplates() throws Exception {
        mainApp.changeRightView("HalfContentView.fxml");
    }

    // three buttons under PageOverview

    @FXML
    public void previousPage() throws Exception { mainApp.goBack();}

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
    public void loadPage(String template, String  content1, String content2, String content3, String content4) throws Exception {
        this.content1 = (content1);
        this.content2 = (content2);
        this.content3 = (content3);
        this.content4 = (content4);
        System.out.println("aus Controller load Page: " + content1 + content2 + content3 + content4);

        if (template.equals("fullpage")){
            mainApp.showPageTemplate("fullpage");
            String imagePath = FULLPATH + content1;
            System.out.println(imagePath);

            ImageView imageView1 = new ImageView(imagePath);
            button1.setGraphic(imageView1);
            System.out.println("fullpage erkannt");
            imageView1.setFitHeight(button1.getHeight());
            imageView1.setPreserveRatio(true);
            button1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else if (mainApp.getCurrentTemplate().equals("half")){
            mainApp.showPageTemplate("half");
            System.out.println("half erkannt"+ halfButton1.getText());
            ImageView imageView1 = new ImageView(HALFPATH + content1);
            ImageView imageView2 = new ImageView(HALFPATH + content2);
            // sets the content in the top half
            halfButton1.setGraphic(imageView1);
            imageView1.setFitHeight(halfButton1.getHeight());
            imageView1.setPreserveRatio(true);
            halfButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the bottom half
            halfButton2.setGraphic(imageView2);
            imageView2.setFitHeight(halfButton2.getHeight());
            imageView2.setPreserveRatio(true);
            halfButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else if (mainApp.getCurrentTemplate().equals("quad")){
            mainApp.showPageTemplate("quad");
            System.out.println("quad erkannt" + quadButton1.getText());
            ImageView imageView1 = new ImageView(FULLPATH + content1);
            ImageView imageView2 = new ImageView(FULLPATH + content2);
            ImageView imageView3 = new ImageView(FULLPATH + content3);
            ImageView imageView4 = new ImageView(FULLPATH + content4);
            // sets the content in the left top quarter
            quadButton1.setGraphic(imageView1);
            imageView1.setFitHeight(quadButton1.getHeight());
            imageView1.setPreserveRatio(true);
            quadButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right top quarter
            quadButton2.setGraphic(imageView2);
            imageView2.setFitHeight(quadButton2.getHeight());
            imageView2.setPreserveRatio(true);
            quadButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the left bottom quarter
            quadButton3.setGraphic(imageView2);
            imageView3.setFitHeight(quadButton3.getHeight());
            imageView3.setPreserveRatio(true);
            quadButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right bottom quarter
            quadButton4.setGraphic(imageView4);
            imageView4.setFitHeight(quadButton4.getHeight());
            imageView4.setPreserveRatio(true);
            quadButton4.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else if (mainApp.getCurrentTemplate().equals("halfquad")){
            mainApp.showPageTemplate("halfQuad");
            ImageView imageView1 = new ImageView(HALFPATH + content1);
            ImageView imageView2 = new ImageView(FULLPATH + content2);
            ImageView imageView3 = new ImageView(FULLPATH + content3);
            // sets the content in the top half
            halfQuadButton1.setGraphic(imageView1);
            imageView1.setFitHeight(halfQuadButton1.getHeight());
            imageView1.setPreserveRatio(true);
            halfQuadButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the left bottom quarter
            halfQuadButton2.setGraphic(imageView2);
            imageView2.setFitHeight(halfQuadButton2.getHeight());
            imageView2.setPreserveRatio(true);
            halfQuadButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right bottom quarter
            halfQuadButton3.setGraphic(imageView3);
            imageView3.setFitHeight(halfQuadButton3.getHeight());
            imageView3.setPreserveRatio(true);
            halfQuadButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else {
            mainApp.showPageTemplate("quadHalf");
            System.out.println("quadhalf erkannt");
            ImageView imageView1 = new ImageView(FULLPATH + content1);
            ImageView imageView2 = new ImageView(FULLPATH + content2);
            ImageView imageView3 = new ImageView(HALFPATH + content3);
            // sets the content in the left top quarter
            quadHalfButton1.setGraphic(imageView1);
            imageView1.setFitHeight(quadHalfButton1.getHeight());
            imageView1.setPreserveRatio(true);
            quadHalfButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the right top quarter
            quadHalfButton2.setGraphic(imageView2);
            imageView2.setFitHeight(quadHalfButton2.getHeight());
            imageView2.setPreserveRatio(true);
            quadHalfButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            // sets the content in the bottom half
            quadHalfButton3.setGraphic(imageView3);
            imageView3.setFitHeight(quadHalfButton3.getHeight());
            imageView3.setPreserveRatio(true);
            quadHalfButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
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



