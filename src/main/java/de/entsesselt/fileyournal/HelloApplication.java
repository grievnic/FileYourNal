package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Organizer;
import de.entsesselt.fileyournal.model.Page;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The mainApp is the central control unit
 */
public class HelloApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Organizer org;
    private Button activeButton;
    private String content1 = "";
    private String content2 = "";
    private String content3 = "";
    private String content4 = "";
    private String currentTemplate = "";
    private EditViewController editViewController;
    private PlanerViewController planerViewController;
    private LeftViewController leftViewController;
    private String fileName;
    private String filePath = "";
    private int maxIndex = 0;
    private int pageIndex = 0;
    private int maxIdNumber = 0;
    private Element currentPage;

    /**
     * The start method
     * @param primaryStage first stage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FileYOURnal");

        // initiales Laden der GUI
        initRootLayout();
        showLeftView();
        showStartView();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("GuiView.fxml"));
            rootLayout = loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the first view center of the root
     */
    public void showStartView() { // shows the page-view with the organizer-picture
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("StartView.fxml"));
            AnchorPane startView = loader.load();
            // Give the controller access to the main app.
            AbstractController controller;
            controller = loader.getController();
            controller.setMainApp(this);
            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
            changeRightView("EmptyRightView.fxml");
            leftViewController.setModifyPaneVisible(false);
            leftViewController.showPdfExportButton();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the edit view at center of the root
     */
    @FXML
    public void showEditView() { // shows the empty page-view
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("EditView.fxml"));
            AnchorPane pagePane = loader.load();
            // Give the controller access to the main app.
            editViewController = loader.getController();
            editViewController.setMainApp(this);
            // Set person overview into the center of root layout.
            rootLayout.setCenter(pagePane);
            leftViewController.showBackToStartButton();
            leftViewController.setModifyPaneVisible(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the flipping through view to show saved pages
     */
    @FXML
    public void showPlanerView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PlanerView.fxml"));
            AnchorPane fullPane = loader.load();
            // Give the controller access to the main app.
            planerViewController = loader.getController();
            planerViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(fullPane);
            leftViewController.setModifyPaneVisible(true);
            leftViewController.showPdfExportButton();
            leftViewController.showBackToStartButton();
            changeRightView("EmptyRightView.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates a new organizer document with user given name und path
     * @param fileName user given at start of the new organizer
     * @param foFilePath user given at start of the new organizer
     */
    public void startNewOrganizer(String fileName, String foFilePath) {
        if (this.org != null){
            this.org = null;
            Page.setCurrentPageNumber(0);
            deleteContent();
            maxIndex = 0;
            pageIndex = 0;
            maxIdNumber = 0;
            currentPage = null;
        }
        this.fileName = fileName;
        this.filePath = foFilePath;
        this.org = (Organizer.getInstance());
        org.readFO(foFilePath);
    }

    /**
     * depending on calling instance the chosen template page view will be shown
     * in edit or flick through view
     * @param controller calling instance submitted by itself
     * @param template the user selected template
     */
    @FXML
    public void showPageTemplate(String controller, String template) {
        currentTemplate = template;
        changeRightView("InfoRightView.fxml");
        if (controller.equals("editViewController")) {
            // to prevent the current view from being overlaid by other panes
            editViewController.setFullVisible(false);
            editViewController.setHalfVisible(false);
            editViewController.setQuadVisible(false);
            editViewController.setHalfQuadVisible(false);
            editViewController.setQuadHalfVisible(false);

            switch (currentTemplate) {
                case "fullpage" -> editViewController.setFullVisible(true);
                case "half" -> editViewController.setHalfVisible(true);
                case "quad" -> editViewController.setQuadVisible(true);
                case "quadHalf" -> editViewController.setQuadHalfVisible(true);
                default -> editViewController.setHalfQuadVisible(true);
            }
        } else {
            //to prevent the current view from being overlaid by other panes
            planerViewController.setFullVisible(false);
            planerViewController.setHalfVisible(false);
            planerViewController.setQuadVisible(false);
            planerViewController.setHalfQuadVisible(false);
            planerViewController.setQuadHalfVisible(false);

            switch (currentTemplate) {
                case "fullpage" -> planerViewController.setFullVisible(true);
                case "half" -> planerViewController.setHalfVisible(true);
                case "quad" -> planerViewController.setQuadVisible(true);
                case "quadHalf" -> planerViewController.setQuadHalfVisible(true);
                default -> planerViewController.setHalfQuadVisible(true);
            }
        }
    }

    /**
     * shows the view on the left side from gui
     */
    public void showLeftView() { // to show the left side of the gui
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("leftView.fxml"));
            AnchorPane pageView = loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setLeft(pageView);
            if (loader.getController() instanceof AbstractController) {
                leftViewController = loader.getController();
                leftViewController.setMainApp(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the view on the right side from gui
     */
    public void showRightView() { // to show the template-overview on the right side
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("rightView.fxml"));
            AnchorPane templatesView = loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setRight(templatesView);
            // Give the controller access to the main app.
            if (loader.getController() instanceof AbstractController) {
                RightViewController rightViewController = loader.getController();
                rightViewController.setMainApp(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * changes the template overview on the right side of the gui
     * according to the submitted template
      * @param templateFxml is the template according content overview as fxml
     */
    public void changeRightView(String templateFxml) { // changes the view according to the page template
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(templateFxml));
            Node node = loader.load();
            rootLayout.setRight(node);
            if (loader.getController() instanceof AbstractController) {
                AbstractController controller = loader.getController();
                controller.setMainApp(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deleting old content information
     */
    private void deleteContent(){
        //empty all content
        content1 = "";
        content2 = "";
        content3 = "";
        content4 = "";
        currentTemplate = "";
    }

    /**
     * cleans the side view as preparation for the new template and content selection
     */
    public void newPage() { //to get a new empty pageview
        showRightView(); // template overview
        showEditView();
        editViewController.namePaneUnvisible();
        if (currentPage == null) editViewController.goToOrganizerDisable(true);
        deleteContent();
    }

    /**
     * hands over the modified page to organizer to save it
     * @param modifiedPage the <fo:block> page element from modified page
     *
     */
    public void modifyInOrganizer(Element modifiedPage) {
        org.addModifiedContent(currentPage.getParent().indexOf(currentPage), modifiedPage);
        org.writeFO(filePath);
    }

    /**
     * hands over the new page to organizer to insert it into the document (before current page)
     * @param newPage the <fo:block> page element from new page
     */
    public void insertBeforeInOrganizer(Element newPage) {
        org.insertContent(currentPage.getParent().indexOf(currentPage), newPage);
        org.writeFO(filePath);
    }

    /**
     * test method to handle multi-page content
     * @return a new page
     */
    public void calComposer(String preName) {
        switch (preName) {
            case "Quart" -> {
                for (int i = 0; i < 4; i++) {
                    String content1 = preName + (i + 1) + ".png";
                    System.out.println(content1);
                    // new page will be created an added to the XSL-FO-document
                    Page fullPage = new Page("fullpage", content1, "", "", ""); //new page
                    addToOrganizer(fullPage.pageCreator());
                    // shows an empty page and shows the template-overview on the right side
                    setPdfButtonVisible(); // after the first storage of a page, it will be able to export it as pdf
                }
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Deine Quartalsplanung wurde erstellt!");
                a.show();
                newPage();
                editViewController.showPlanerViewFirstPage();
            }
            default -> {
                for (int j = 0; j < 12; j++) {
                    String content1 = preName + j + 1 + ".png";
                    System.out.println(content1);
                    // new page will be created an added to the XSL-FO-document
                    Page fullPage = new Page("fullpage", content1, "", "", ""); //new page
                    addToOrganizer(fullPage.pageCreator());
                    newPage(); // shows an empty page and shows the template-overview on the right side
                    setPdfButtonVisible(); // after the first storage of a page, it will be able to export it as pdf
                }
            }
        }
    }

    /**
     * hands over the new page to organizer to insert it into the document (after current page)
     * @param newPage the <fo:block> page element from new page
     */
    public void insertAfterInOrganizer(Element newPage) {
        org.insertContent(currentPage.getParent().indexOf(currentPage) + 1, newPage);
        org.writeFO(filePath);
    }

    /**
     * hands over the new page to organizer to save it
     * @param newPage the <fo:block> page element from new page
     */
    public void addToOrganizer(Element newPage) {
        Organizer.addPage(newPage);
        currentPage = newPage;
        org.writeFO(filePath); // writes the XST-FO-Document in Organizer Class
    }

    /**
     * service method to get the List of <fo: flow> children from current organizer
     * @return the list of children representing all page elements
     */
    private List <Element> getChildren(){
        return Organizer.fetchPageParent().getChildren();
    }

    /**
     * delegates the delete-instruction to organizer
     */
    public void deleteFromOrganizer() {
        int index = currentPage.getParent().indexOf(currentPage);
    org.deletePage(index);
    goToPageIndex(0);
        org.writeFO(filePath); // writes the XST-FO-Document in Organizer Class
    }

    /**
     * enables to go to user given page by searching the page in document
     * and sending the read-out page data to the flick through view
     * @param indexnumber to jump to desired page <fo:block> element
     */
    public void goToPageIndex(int indexnumber) { // if user wants to scroll back to older pages
        /*List children = Organizer.fetchPageParent().getChildren();*/
        pageIndex = indexnumber;
        currentPage = getChildren().get(pageIndex);
        currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", ""); // delete pagecounter to get only template-name
        maxIndex = getChildren().size() - 1;
        getFoData(currentTemplate);
        checkButtons(pageIndex, maxIndex);
        planerViewController.loadPage(currentTemplate, content1, content2, content3, content4, pageIndex);
    }

    /**
     * Getting the first Page and the information, to show it in flick through view
     */
    public void loadedOrganizer() {
        /*List children = Organizer.fetchPageParent().getChildren();*/
        currentPage = getChildren().get(0);
        // filters the template type out of the id
        currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", "");
        maxIndex = getChildren().size() - 1;// important value for controlling the buttons "Seite zurück" and "Seite vor"
        pageIndex = getChildren().indexOf(currentPage); // index from currently shown page
        getFoData(currentTemplate); // fetches the content for the current page
        checkButtons(pageIndex, maxIndex); // (dis)ables the buttons "Seite zurück" and "Seite vor" depending on the indexes
        // sending all page information to the gui
        planerViewController.loadPage(currentTemplate, content1, content2, content3, content4, pageIndex);
        // sets the max number from ids - important to ensure the explicitness of the ID, when modifying the organizer
        Page.setCurrentPageNumber(maxIdNumber);
    }

    /**
     * getting the user requested page and shows in flick through view
     * @param pageIndex to jump to desired page <fo:block> element
     */
    public void goToFoPage(int pageIndex) {
        /*List children = Organizer.fetchPageParent().getChildren();*/
        currentPage = getChildren().get(pageIndex);
        this.pageIndex = pageIndex;
        currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", "");
        maxIndex = getChildren().size() - 1;
        getFoData(currentTemplate);
        checkButtons(this.pageIndex, maxIndex);
        editViewController.loadPage(currentTemplate, content1, content2, content3, content4);
    }

    /**
     * reads out the page data from XSL-FO document and assigns it to the variables
     * @param currentTemplate to find out how much template data has to be read
     */
    private void getFoData(String currentTemplate) { // filters all graphic paths from children/descendants and assigns to its content variable
        Iterator<Element> graphics = currentPage.getDescendants(new ElementFilter("external-graphic"));// search for the element <fo:external-graphic>
        ArrayList<String> paths = new ArrayList<>();
        graphics.forEachRemaining((content) -> paths.add(content.getAttributeValue("src"))); //writes the source-paths into an array-list
        switch (currentTemplate) {
            case "fullpage" -> content1 = paths.get(0).replaceFirst("^.*/", "");
            case "half" -> {
                content1 = paths.get(0).replaceFirst("^.*/", "");
                content2 = paths.get(1).replaceFirst("^.*/", "");
            }
            case "quad" -> {
                content1 = paths.get(0).replaceFirst("^.*/", "");
                content2 = paths.get(1).replaceFirst("^.*/", "");
                content3 = paths.get(2).replaceFirst("^.*/", "");
                content4 = paths.get(3).replaceFirst("^.*/", "");
            }
            default -> {  // template is halfQuad or quadHalf
                content1 = paths.get(0).replaceFirst("^.*/", "");
                content2 = paths.get(1).replaceFirst("^.*/", "");
                content3 = paths.get(2).replaceFirst("^.*/", "");
            }
        }
        // filters all block elements to get their id
        Iterator<Element> blockIterator = currentPage.getParent().getDescendants(new ElementFilter("block"));
        ArrayList<String> fullIds = new ArrayList<>();
        ArrayList<Integer> indexNumbers =new ArrayList<>();
        blockIterator.forEachRemaining((iD)-> fullIds.add(iD.getAttributeValue("id")));
        for( String element : fullIds ){
            if (element!= null) {
                indexNumbers.add(Integer.parseInt(element.replaceAll("[^0-9]", ""))); //keeps only numeric part
            }
        }
        //getting the highest number of the ids is important to create unique ids when modifying
        maxIdNumber = Collections.max(indexNumbers);
    }

    /**
     * checks whether the button display is logical, is there a previous or next page
     * @param pageIndex index from current page
     * @param maxIndex the last page
     */
    private void checkButtons(int pageIndex, int maxIndex){
        planerViewController.setNextButton(pageIndex == maxIndex);
        planerViewController.setPrevButton(pageIndex == 0);
    }

    /**
     * service method to set the PDF-Export-Button visible at the left view, if there is anything to export
     */
    public void setPdfButtonVisible(){
        leftViewController.showPdfExportButton();
     }

    /**
     * service method to hide the namePane at the EditView
     */
    public void setNamePane(){
        editViewController.namePaneUnvisible();
    }

    /**
     * service method to change the save button according to edit- or modify mode at the EditView
     * modify page
     */
    public void changePageViewSaveButtons(){
        editViewController.takeButton.setVisible(false);
        editViewController.takeChangeButton.setVisible(true);
    }

    /**
     * service method to change the save button according to edit- or modify mode at the EditView
     * insert before
     */
    public void changePageInsertBeforeButton(){
        editViewController.takeButton.setVisible(false);
        editViewController.insertBeforeButton.setVisible(true);
    }

    /**
     * service method to change the save button according to edit- or modify mode at the EditView
     * insert after
     */
    public void changePageInsertAfterButton(){
        editViewController.takeButton.setVisible(false);
        editViewController.insertAfterButton.setVisible(true);
    }

    /**
     * service method to start the pdf export
     */
    public void foToPdf(String targetPath) throws Exception {
        org.foToPdf(filePath, targetPath);
    }

    /**
     * sets the save-button in edit mode according to modify type add pages
     */
    public void setAddNewPageButtonVisible(){
        editViewController.takeButton.setVisible(false);
        editViewController.addNewPageButton.setVisible(true);
    }

      // Getter & Setter

    public Button getActiveButton() {
        return activeButton;
    }

    public void setActiveButton(Button activeButton) {
        this.activeButton = activeButton;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getContent4() {
        return content4;
    }

    public void setContent4(String content4) {
        this.content4 = content4;
    }

    public String getCurrentTemplate() {
        return currentTemplate;
    }

    public void setCurrentTemplate(String template) {
        this.currentTemplate = template;
    }

    public EditViewController getEditViewController() {
        return editViewController;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getMaxIdNumber() {
        return maxIdNumber;
    }

    public Element getCurrentPage() {
        return currentPage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public static void main(String[] args) {
        launch();
    }
}