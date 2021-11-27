package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Page;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

import static javafx.scene.text.TextAlignment.CENTER;


public class PlanerViewController extends AbstractController{


    @FXML
    Button nextPageButton;

    @FXML
    Button prevPageButton;

    @FXML
    Label pageLabel;

    @FXML
    Label goTo;

    @FXML
    Button goToButton;

    @FXML
    TextField goToPage;

    @FXML
    Label noPage;

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

    @FXML
    ImageView half1;

    @FXML
    ImageView half2;

    @FXML
    ImageView halfQuad1;

    @FXML
    ImageView halfQuad2;

    @FXML
    ImageView halfQuad3;

    @FXML
    ImageView quadHalf1;

    @FXML
    ImageView quadHalf2;

    @FXML
    ImageView quadHalf3;

    @FXML
    ImageView quad1;

    @FXML
    ImageView quad2;

    @FXML
    ImageView quad3;

    @FXML
    ImageView quad4;

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

    private int pageIndex;

    private final static String FULLPATH = "assets/Content/ContentElements/fullPageContent/";
    private final static String HALFPATH = "assets/Content/ContentElements/halfPageContent/";


    // Reference to the main application.
    /* private HelloApplication mainApp;*/


    @FXML
    public void modifyPage() throws Exception {
        /* mainApp.openChangeManager();*/
        //
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Änderungsmanager");
        a.setContentText("Was möchtest Du bearbeiten?");
        ButtonType buttonTypeOne = new ButtonType("Die aktuelle Seite ändern.");
        ButtonType buttonTypeTwo = new ButtonType("Vor dieser Seite eine neue Seite einfügen.");
        ButtonType buttonTypeThree = new ButtonType("Nach dieser Seite eine neue Seite einfügen.");
        ButtonType buttonTypeFour = new ButtonType("Am Ende weitere Seiten hinzufügen");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        a.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeCancel);
        Optional<ButtonType> result = a.showAndWait();

        if (result.get() == buttonTypeOne) { // modify current page
            mainApp.showEditView();//
            mainApp.goToFoPage(pageIndex);
        } else if (result.get() == buttonTypeTwo) { // inserts before current page
            mainApp.showEditView();//
            mainApp.newPage();
            mainApp.changePageInsertBeforeButton();
        } else if (result.get() == buttonTypeThree) { // insert after current page
            mainApp.showEditView();//
            mainApp.newPage();
            mainApp.changePageInsertAfterButton();
            mainApp.setPageIndex(pageIndex + 1);
        } else if (result.get() == buttonTypeFour) { // add at the end of document
            mainApp.showEditView();//
            mainApp.newPage();
            mainApp.setAddNewPageButtonVisible();
            mainApp.changePageViewSaveButtons();
            mainApp.setPageIndex(mainApp.getMaxIndex() + 1);
        }/*mainApp.setPageIndex(pageIndex);*/
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
    public void setFullVisible(Boolean bool){
        fullPane.setVisible(bool);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/

    }

    @FXML
    public void setHalfVisible(Boolean bool){
        halfPane.setVisible(bool);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/
    }

    @FXML
    public void setQuadVisible(Boolean bool){
        quadPane.setVisible(bool);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/
    }

    @FXML
    public void setQuadHalfVisible(Boolean bool){
        quadHalfPane.setVisible(bool);
        /*label.setVisible(false);
        takeButton.setDisable(false);*/
    }

    @FXML
    public void setHalfQuadVisible(Boolean bool){
        halfQuadPane.setVisible(bool);
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
    public void previousPage() throws Exception {
        /*mainApp.prevPage();*/
        mainApp.goToPageIndex(mainApp.getPageIndex() - 1);
    }

    @FXML
    public void nextPage()throws Exception{
        /*mainApp.nextPage();*/
        mainApp.goToPageIndex(mainApp.getPageIndex() + 1);
    }

    @FXML
    private void goToPageNumber() throws Exception{
        String pagenumber = goToPage.getText();// pagenumber from user
        if (pagenumber.isEmpty() | pagenumber.equals("0")){
            noPage.setText("Du hast keine Seitenzahl angegeben!");
            noPage.setVisible(true);
        } else { // only int allowed
            if (!pagenumber.matches("^[0-9]*$")) {
                System.out.println("keine Buchstaben erwünscht!");
               pagenumber = pagenumber.replaceAll("[^0-9]", "");
                System.out.println("ohne Buchstaben: " + pagenumber);
                goToPage.setText(pagenumber);
            }
            System.out.println(pagenumber);
            int index = Integer.parseInt(pagenumber) - 1; // conversion to index
            System.out.println("Index ist: " + index);
            if (index <= mainApp.getMaxIndex()) {//check if index occurs
                mainApp.goToPageIndex(index);
                goToPage.setText("");
                noPage.setVisible(false);
            } else {
                noPage.setVisible(true);
            }
        }
    }

    @FXML
    public void savePage() throws Exception {
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
            mainApp.modifyInOrganizer(fullPage.pageCreator());
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
        pageLabel.setTextAlignment(CENTER);
        this.pageIndex = pageindex;

        System.out.println("aus PlanerController loadPage(): " + content1 + content2 + content3 + content4);
        if (template.equals("fullpage")){
            mainApp.showPageTemplate("planerViewController", "fullpage");
            String imagePath = FULLPATH + content1;
            System.out.println(imagePath);
            Image image1 = new Image(imagePath);
            full1.setImage(image1);

            System.out.println("fullPage wird abgebildet");
        } else if (mainApp.getCurrentTemplate().equals("half")){
            mainApp.showPageTemplate("planerViewController","half");
            String imagePath1 = HALFPATH + content1;
            System.out.println(imagePath1);
            Image image1 = new Image(imagePath1);
            half1.setImage(image1);

            String imagePath2 = HALFPATH + content2;
            System.out.println(imagePath2);
            Image image2 = new Image(imagePath2);
            half2.setImage(image2);

        } else if (mainApp.getCurrentTemplate().equals("quad")){
            mainApp.showPageTemplate("planerViewController", "quad");
            String imagePath1 = FULLPATH + content1;
            System.out.println(imagePath1);
            Image image1 = new Image(imagePath1);
            quad1.setImage(image1);

            String imagePath2 = FULLPATH + content2;
            System.out.println(imagePath2);
            Image image2 = new Image(imagePath2);
            quad2.setImage(image2);

            String imagePath3 = FULLPATH + content3;
            System.out.println(imagePath3);
            Image image3 = new Image(imagePath3);
            quad3.setImage(image3);

            String imagePath4 = FULLPATH + content4;
            System.out.println(imagePath4);
            Image image4 = new Image(imagePath4);
            quad4.setImage(image4);

        } else if (mainApp.getCurrentTemplate().equals("halfquad")){
            mainApp.showPageTemplate("planerViewController", "halfQuad");
            String imagePath1 = HALFPATH + content1;
            System.out.println(imagePath1);
            Image image1 = new Image(imagePath1);
            halfQuad1.setImage(image1);

            String imagePath2 = FULLPATH + content2;
            System.out.println(imagePath2);
            Image image2 = new Image(imagePath2);
            halfQuad2.setImage(image2);

            String imagePath3 = FULLPATH + content3;
            System.out.println(imagePath3);
            Image image3 = new Image(imagePath3);
            halfQuad3.setImage(image3);

        } else {
            mainApp.showPageTemplate("planerViewController", "quadHalf");
            String imagePath1 = FULLPATH + content1;
            System.out.println(imagePath1);
            Image image1 = new Image(imagePath1);
            quadHalf1.setImage(image1);

            String imagePath2 = FULLPATH + content2;
            System.out.println(imagePath2);
            Image image2 = new Image(imagePath2);
            quadHalf2.setImage(image2);

            String imagePath3 = HALFPATH + content3;
            System.out.println(imagePath3);
            Image image3 = new Image(imagePath3);
            quadHalf3.setImage(image3);
        }
    }

   /* @FXML
    public void addContent(String contentName) throws Exception{
        this.contentName = contentName;
        ImageView imageView;
        activeButton = mainApp.getActiveButton();
        if (activeButton.getText().equals("halbe Seite")) { //identifies format of the page area : landscape
            imagePath = HALFPATH + contentName + ".png";
            *//*imageView = new ImageView(HALFPATH + contentName + ".png"); // Path to halfpage-Content*//*
        } else {
            imagePath = FULLPATH + contentName + ".png"; //format of the page area : portrait
            *//*imageView = new ImageView(FULLPATH + contentName + ".png"); // Path to fullpage-Content*//*
        }
        imageView = new ImageView(imagePath);
        activeButton.setGraphic(imageView);
        imageView.setFitHeight(activeButton.getHeight());
        imageView.setPreserveRatio(true);
        activeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        sendContent();
    }*/

    // Assignment of the content linked to the buttons
    /*public void sendContent(){
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



