package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Page;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

/**
 * Controller of the EditView
 * This view enables the user to create pages. The selected templates can be filled with content elements.
 *
 * @author Nicole Grieve (nicole.grieve@stud.th-luebeck.de)
 * @version 1.0
 *
 */
public class EditViewController extends AbstractController{

    /**
     * buttons as areas depending on selected template
     */
    @FXML
    Button button1;

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


    /**
     * save buttons depending on selected modification mode  - combined with different onAction methods
     */
    @FXML
    Button takeButton;

    @FXML
    Button takeChangeButton;

    @FXML
    Button insertBeforeButton;

    @FXML
    Button insertAfterButton;

    @FXML
    Button addNewPageButton;

    /**
     *  other
     */
    @FXML
    Label label;

    @FXML
    Button templateViewShow;

    @FXML
    Button goToOrganizer;

    /**
     * variables for building pages
     */
    String template;
    String content1;
    String content2;
    String content3;
    String content4;

    /**
     * variables to get information
     */
    String contentName;
    String imagePath;
    Button activeButton;
    String buttonId;

    /**
     * several page template view as anchorpanes
     */
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

    /**
     * first view elements to set document name
     */

    @FXML
    Label nameLabel;

    @FXML
    Button saveName;

    @FXML
    TextField nameField;

    @FXML
    private Label nameWarn;

    @FXML
    private Label pathWarn;

    @FXML
    private AnchorPane namePane;

    private final static String FULLPATH = "assets/Content/ContentElements/fullPageContent/";
    private final static String HALFPATH = "assets/Content/ContentElements/halfPageContent/";
    private final static String FO_TEMPLATE = "/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/PageTemplateDinA4.fo";

    /**
     * first method when starting the editor to get all file information (name and dir) from user
     * click on Button "speichern & Speicherort festlegen"
     */
   @FXML
   public void nameYourOrganizer() {
       File file;
       String fileName;
       String filePath = null;
       // to get the files name
       if (nameField.getText().isEmpty()) { // if no name is entered
           nameWarn.setVisible(true); // warning message appears

       } else {
           fileName = nameField.getText();
           mainApp.setFileName(fileName);
        // to open a window to set a directory
           DirectoryChooser dc = new DirectoryChooser();
           Stage stage = (Stage) namePane.getScene().getWindow(); // gets the current window
           file = dc.showDialog(stage);
           if (file != null) {
               file = fileRenameCheck(file, fileName); // checks and change name, if path is already taken
               filePath = file.getPath(); // selected filepath
           }
       }

       if (filePath != null) {
           namePane.setVisible(false);
           mainApp.startNewOrganizer(mainApp.getFileName(), FO_TEMPLATE); // starts a new organizer
           mainApp.setFilePath(filePath);
           mainApp.newPage();
       } else {
           pathWarn.setVisible(true);
       }
   }

    /**
     * realizes the active button
     * gets the template identifier
     * initializes showing the corresponding content overview
     * @param event selected Page area
     */
    @FXML
    public void selectArea(ActionEvent event) {
        Button activeButton = (Button) event.getSource();
        mainApp.setActiveButton(activeButton);
        if (activeButton.getText().equals("halbe Seite")){
            showLandscapeTemplates();
        } else {
            showPortraitTemplates();
        }
    }

    /**
     * checks, if file already exist
     * @param file to check
     * @return true, if file already exists
     */
   private Boolean fileCheck(File file){
       return file.exists() && !file.isDirectory();
   }

    /**
     * This recursive method checks, if the path already exists, if true, the number "1" will be concatenated
     * @param file File with origin path
     * @param fileName tested filename
     * @return a unique file
     */
   private File fileRenameCheck(File file, String fileName) {
       File newFile = new File(file.getAbsolutePath() + "/" + fileName + ".fo");
       if(!fileCheck(newFile)){
           return newFile;
       } else {
           String newFileName = fileName + "1";
           mainApp.setFileName(newFileName);
           return fileRenameCheck(file, newFileName);
       }
   }

    /**
     * sets namePane unvisible after the start process getting file information
     */
   public void namePaneUnvisible(){
       namePane.setVisible(false);
   }

    /**
     * sets the fullpageview on-air / off-air
     * @param bool true or false for on / off-air
     */
   @FXML
   public void setFullVisible(Boolean bool){
       fullPane.setVisible(bool);
       label.setVisible(false);
       takeButton.setDisable(false);
       if (mainApp.getCurrentPage() == null) goToOrganizer.setDisable(true); // if there is no first page, there ist no possibility to flipping through
   }

    /**
     * sets the halfpageview on-air / off-air
     * @param bool true or false for on / off-air
     */
    @FXML
    public void setHalfVisible(Boolean bool){
        halfPane.setVisible(bool);
        label.setVisible(false);
        takeButton.setDisable(false);
        if (mainApp.getCurrentPage() == null) goToOrganizer.setDisable(true); // if there is no first page, there ist no possibility to flipping through
    }

    /**
     * sets the quadpageview on-air / off-air
     * @param bool true or false for on / off-air
     */
    @FXML
    public void setQuadVisible(Boolean bool){
        quadPane.setVisible(bool);
        label.setVisible(false);
        takeButton.setDisable(false);
        if (mainApp.getCurrentPage() == null) goToOrganizer.setDisable(true); // if there is no first page, there ist no possibility to flipping through
    }

    /**
     * sets the quadhalfpageview on-air / off-air
     * @param bool true or false for on / off-air
     */
    @FXML
    public void setQuadHalfVisible(Boolean bool){
        quadHalfPane.setVisible(bool);
        label.setVisible(false);
        takeButton.setDisable(false);
        if (mainApp.getCurrentPage() == null) goToOrganizer.setDisable(true); // if there is no first page, there ist no possibility to flipping through
    }

    /**
     * sets the halfquadpageview on-air / off-air
     * @param bool true or false for on / off-air
     */
    @FXML
    public void setHalfQuadVisible(Boolean bool){
        halfQuadPane.setVisible(bool);
        label.setVisible(false);
        takeButton.setDisable(false);
        if (mainApp.getCurrentPage() == null) goToOrganizer.setDisable(true); // if there is no first page, there ist no possibility to flipping through
    }

    /**
     * controls the content overview in the rightView - shows portrait content
     */
    protected void showPortraitTemplates() {
        mainApp.changeRightView("FullContentView.fxml");
    }

    /**
     * controls the content overview in the rightView - shows landscape content
     */
    protected void showLandscapeTemplates() {
        mainApp.changeRightView("HalfContentView.fxml");
    }

    /**
     * method fetches deposited content from mainApp as service
     */
    private void pageDataService(){
        content1 = mainApp.getContent1();
        content2 = mainApp.getContent2();
        content3 = mainApp.getContent3();
        content4 = mainApp.getContent4();
        template = mainApp.getCurrentTemplate();
    }

    /**
     * method checks if all areas are filled with content
     * if not: shows an alert
     * if it's ok...
     * @return a new page
     */
    private Page pageContentComposer() {
       Page fullPage = null;
        // alert
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Es müssen alle Seitenelemente befüllt sein!");
        //Checks if all pageAreas are filled with content?
        if (!allAreasFull()){
            a.show();
        }else fullPage = new Page(template, content1, content2, content3, content4); //new page if all areas are filled
        return fullPage;
    }

    /**
     * button-click initializes the check, if all areas are filled,
     * the page will be created with JDOM and saved in the FO-document
     * an empty page will be shown and
     * after the first page it will be possible, to click the PDF export button
     */
    @FXML
    public void savePage () {
        pageDataService();
        Page page = pageContentComposer(); // if all areas got content, a new page object will be created
        if (page!=null) {
            // new page will be created an added to the XSL-FO-document
            mainApp.addToOrganizer(page.pageCreator()); // page object will be converted to a JDOM element
            mainApp.newPage(); // shows an empty page and shows the template-overview on the right side
            mainApp.setPdfButtonVisible(); // after the first storage of a page, it will be able to export it as pdf
        }
    }

    /**
     * button-click initializes the check, if all areas are filled,
     * the page will be modified with JDOM and saved in the FO-document
     * an empty page will be shown and
     * after the first page it will be possible, to click the PDF export button
     */
    public void saveChanges() {
        pageDataService();
        Page page = pageContentComposer();
        if (page!=null) {
            mainApp.modifyInOrganizer(page.pageCreator());
            mainApp.newPage(); // shows an empty page and shows the template-overview on the right side
            mainApp.showPlanerView();
            mainApp.goToPageIndex(mainApp.getPageIndex());
        }
    }

    /**
     * button-click initializes the check, if all areas are filled,
     * the page will be inserted before the current one with JDOM and saved in the FO-document
     * an empty page will be shown and
     * after the first page it will be possible, to click the PDF export button
     */
    public void insertNewBefore() {
        pageDataService();
        Page page = pageContentComposer();
        if (page!=null) {
            Page.setCurrentPageNumber(mainApp.getMaxIdNumber()); // important to get a unique id
            mainApp.insertBeforeInOrganizer(page.pageCreator());
            mainApp.newPage(); // shows an empty page and shows the template-overview on the right side
            mainApp.showPlanerView();
            mainApp.goToPageIndex(mainApp.getPageIndex());
        }
    }


    /**
     * button-click initializes the check, if all areas are filled,
     * the page will be inserted after the current one with JDOM and saved in the FO-document
     * an empty page will be shown and
     * after the first page it will be possible, to click the PDF export button
     */
    public void insertNewAfter() {
        pageDataService();
        Page fullPage = pageContentComposer();
        if (fullPage!=null) {
            Page.setCurrentPageNumber(mainApp.getMaxIdNumber()); // important to get a unique id
            mainApp.insertAfterInOrganizer(fullPage.pageCreator());
            mainApp.newPage(); // shows an empty page and shows the template-overview on the right side
            mainApp.showPlanerView();
            mainApp.goToPageIndex(mainApp.getPageIndex());
        }
    }

    /**
     * leaves the editor mode and makes a superficial check, if unsaved content may be set
     * and gives a possibility to save from dialogue - depending on edit- or modification mode
     */
    @FXML
    public void exitEditor() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Möglicher Datenverlust");
        ButtonType buttonTypeOne = new ButtonType("Speichern & weiter");
        ButtonType buttonTypeTwo = new ButtonType("Editor verlassen!");
        ButtonType buttonTypeCancel = new ButtonType("abbrechen");
        pageDataService();
        if (!allAreasFull() | template.equals("")){
            a.setContentText("Bist Du sicher, dass Du den Editor verlassen möchtest?");
            a.getButtonTypes().setAll(buttonTypeTwo, buttonTypeCancel);
        } else {
            a.setContentText("Dein aktueller Seitenentwurf ist nicht gespeichert?");
            a.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        }
            Optional<ButtonType> result = a.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOne) { // checks if result != null and which kind of button in pressed
                if (takeChangeButton.isVisible()) saveChanges();
                else if (insertBeforeButton.isVisible()) insertNewBefore();
                else if (insertAfterButton.isVisible()) insertNewAfter();
                else if (addNewPageButton.isVisible()) savePage();
                else savePage();
                showPlanerViewFirstPage();
            } else if (result.isPresent() && result.get()== buttonTypeTwo) { // checks if result != null and which kind of button in pressed
                showPlanerViewFirstPage();
            }
    }

    /**
     * depending on the template the method checks, if all page areas are filled with content
     * @return true, if all areas are filled
     */
    private Boolean allAreasFull() {
        if (template.equals("full") & content1.isEmpty()) {
            return false;
        } else if (template.equals("half") && content1.isEmpty() | content2.isEmpty()) {
            return false;
        } else if (template.equals("halfQuad") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty()) {
            return false;
        } else if (template.equals("quadHalf") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty()) {
            return false;
        } else if (template.equals("quad") && content1.isEmpty() | content2.isEmpty() | content3.isEmpty() | content4.isEmpty()) {
            return false;
        } else return true;
    }

    /**
     * service to get to page 1 at the flipping through mode
     */
    public void showPlanerViewFirstPage() {
        mainApp.showPlanerView();//
        mainApp.loadedOrganizer();
        mainApp.goToPageIndex(0);
    }

    /**
     * loads site view with content depending on the data and template
     * if an area is portrait, the path to portrait content files will be addet
     * if an area is landcape, the path to landscape content files will be addet
     * @param template template type
     * @param content1 for fullpage, half1, quad1, halfquad1, quadhalf1
     * @param content2 for half2, quad2, halfquad2, quadhalf2
     * @param content3 for quad3, halfquad3, quadhalf3
     * @param content4 for quad4
     */
    @FXML
    public void loadPage(String template, String  content1, String content2, String content3, String content4) {
        this.content1 = (content1);
        this.content2 = (content2);
        this.content3 = (content3);
        this.content4 = (content4);
        this.template = template;
        takeButton.setVisible(false);
        takeChangeButton.setVisible(true);

       if (template.equals("fullpage")){
           ImageView imageView1;
           mainApp.showPageTemplate("pageViewController","fullpage");
           String imagePath = FULLPATH + content1;
           imageView1 = new ImageView(imagePath);
           button1.setGraphic(imageView1);
           imageView1.fitWidthProperty().bind(button1.widthProperty());
           imageView1.setPreserveRatio(true);
           button1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
       } else if (mainApp.getCurrentTemplate().equals("half")){
           mainApp.showPageTemplate("pageViewController", "half");
           ImageView imageView1 = new ImageView(HALFPATH + content1);
           ImageView imageView2 = new ImageView(HALFPATH + content2);
           // sets the content in the top half
           halfButton1.setGraphic(imageView1);
           imageView1.fitWidthProperty().bind(halfButton1.widthProperty());
           imageView1.setPreserveRatio(true);
           halfButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the bottom half
           halfButton2.setGraphic(imageView2);
           imageView2.fitWidthProperty().bind(halfButton2.widthProperty());
           imageView2.setPreserveRatio(true);
           halfButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
       } else if (mainApp.getCurrentTemplate().equals("quad")){
           mainApp.showPageTemplate("pageViewController", "quad");
           ImageView imageView1 = new ImageView(FULLPATH + content1);
           ImageView imageView2 = new ImageView(FULLPATH + content2);
           ImageView imageView3 = new ImageView(FULLPATH + content3);
           ImageView imageView4 = new ImageView(FULLPATH + content4);
           // sets the content in the left top quarter
           quadButton1.setGraphic(imageView1);
           imageView1.fitWidthProperty().bind(quadButton1.widthProperty());
           imageView1.setPreserveRatio(true);
           quadButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the right top quarter
           quadButton2.setGraphic(imageView2);
           imageView2.fitWidthProperty().bind(quadButton2.widthProperty());
           imageView2.setPreserveRatio(true);
           quadButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the left bottom quarter
           quadButton3.setGraphic(imageView3);
           imageView3.fitWidthProperty().bind(quadButton3.widthProperty());
           imageView3.setPreserveRatio(true);
           quadButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the right bottom quarter
           quadButton4.setGraphic(imageView4);
           imageView4.fitWidthProperty().bind(quadButton4.widthProperty());
           imageView4.setPreserveRatio(true);
           quadButton4.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
       } else if (mainApp.getCurrentTemplate().equals("halfquad")){
           mainApp.showPageTemplate("pageViewController", "halfQuad");
           ImageView imageView1 = new ImageView(HALFPATH + content1);
           ImageView imageView2 = new ImageView(FULLPATH + content2);
           ImageView imageView3 = new ImageView(FULLPATH + content3);
           // sets the content in the top half
           halfQuadButton1.setGraphic(imageView1);
           imageView1.fitWidthProperty().bind(halfQuadButton1.widthProperty());
           imageView1.setPreserveRatio(true);
           halfQuadButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the left bottom quarter
           halfQuadButton2.setGraphic(imageView2);
           imageView2.fitWidthProperty().bind(halfQuadButton2.widthProperty());
           imageView2.setPreserveRatio(true);
           halfQuadButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the right bottom quarter
           halfQuadButton3.setGraphic(imageView3);
           imageView3.fitWidthProperty().bind(halfQuadButton3.widthProperty());
           imageView3.setPreserveRatio(true);
           halfQuadButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
       } else {
           mainApp.showPageTemplate("pageViewController", "quadHalf");
           ImageView imageView1 = new ImageView(FULLPATH + content1);
           ImageView imageView2 = new ImageView(FULLPATH + content2);
           ImageView imageView3 = new ImageView(HALFPATH + content3);
           // sets the content in the left top quarter
           quadHalfButton1.setGraphic(imageView1);
           imageView1.fitWidthProperty().bind(quadHalfButton1.widthProperty());
           imageView1.setPreserveRatio(true);
           quadHalfButton1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the right top quarter
           quadHalfButton2.setGraphic(imageView2);
           imageView2.fitWidthProperty().bind(quadHalfButton2.widthProperty());
           imageView2.setPreserveRatio(true);
           quadHalfButton2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
           // sets the content in the bottom half
           quadHalfButton3.setGraphic(imageView3);
           imageView3.fitWidthProperty().bind(quadHalfButton3.widthProperty());
           imageView3.setPreserveRatio(true);
           quadHalfButton3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
       }
    }

    /**
     * method displays the chosen content in the active button (handled in mainApp)
     * @param contentName - in the right view chosen content
     */
    @FXML
    public void addContent(String contentName) {
        this.contentName = contentName;
        ImageView imageView;
        activeButton = mainApp.getActiveButton(); // identifies the active page area button
        if (activeButton.getText().equals("halbe Seite")) { //identifies format of the page area "landscape"
            imagePath = HALFPATH + contentName; // format of the page area "landscape"
        } else {
            imagePath = FULLPATH + contentName; // format of the page area "portrait"
        }
            // shows the selected content in the GUI
            imageView = new ImageView(imagePath);
            activeButton.setGraphic(imageView);
            imageView.setFitHeight(activeButton.getHeight());
            imageView.setPreserveRatio(true);
            activeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            sendContent(); // ... initiates the assignment of the content linked to the buttons
    }

    /**
     * service to show the template overview at the right view
     */
    @FXML
    private void templateView() {
        mainApp.showRightView();
    }

    /**
     * Assignment of the content linked to the buttons
     */
    public void sendContent() {
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
     * service to enable/disable the button "Organizer durchblättern"
     * @param bool true = disable, false = enable
     */
    public void goToOrganizerDisable(Boolean bool){
        goToOrganizer.setDisable(bool);
    }
}
