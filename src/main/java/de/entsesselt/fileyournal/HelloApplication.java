package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Organizer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*import org.jdom.Document;*/


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
    private FullPageViewController pageViewController;
    private PlanerViewController planerViewController;
    private LeftViewController leftViewController;
    private String fileName;
    private String filePath;
    private int maxIndex = 0;
    private int pageIndex = 0;
    private int maxIdNumber = 0;


    @FXML
    private AnchorPane pagePane;


    private Element currentPage;
    private final static String FILENAME = "/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/OrganizerTEST.fo";
    private final static File FILE = new File(FILENAME);
    private final static String NAMESPACE = "http://www.w3.org/1999/XSL/Format";
    Namespace fo = Namespace.getNamespace("fo", NAMESPACE);

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FileYOURnal");

        // initiales Laden der GUI
        initRootLayout();
        showStartView();
        showLeftView();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("GuiView.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStartView() { // shows the page-view with the organizer-picture
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("StartView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            AbstractController controller;
            controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showEditView() { // shows the empty page-view
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("NewPageView.fxml"));
            pagePane = (AnchorPane) loader.load();
            /*AnchorPane fullPane = (AnchorPane) loader.load();*/
            // Give the controller access to the main app.
            pageViewController = loader.getController();
            pageViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(pagePane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showPlanerView() { //
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PlanerView.fxml"));
            AnchorPane fullPane = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            planerViewController = loader.getController();
            planerViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(fullPane);
            leftViewController.setModifyPaneVisible();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startNewOrganizer(String fileName, String foFilePath) {
        //Organizer-Objekt erstellen und per JDOM eine XSL-FO erstellen
        Organizer org = new Organizer(fileName);
        this.org = org;
        org.readFO(foFilePath);
        /*pageIndex = 0;
        maxIndex = 0;*/
    }


    @FXML
    public void showPageTemplate(String controller, String template) {
        currentTemplate = template;

        System.out.println("CurrentTemplate ist : " + currentTemplate);
        if (controller.equals("pageViewController")) {
            System.out.println("Bin im pageView-Zweig");
            if (currentTemplate.equals("fullpage")) {
                pageViewController.setFullVisible();
            } else if (currentTemplate.equals("half")) {
                pageViewController.setHalfVisible();
            } else if (currentTemplate.equals("quad")) {
                pageViewController.setQuadVisible();
            } else if (currentTemplate.equals("quadHalf")) {
                pageViewController.setQuadHalfVisible();
            } else {
                pageViewController.setHalfQuadVisible();
            }
        } else {

            planerViewController.setFullVisible(false);
            planerViewController.setHalfVisible(false);
            planerViewController.setQuadVisible(false);
            planerViewController.setHalfQuadVisible(false);
            planerViewController.setQuadHalfVisible(false);

            if (currentTemplate.equals("fullpage")) {
                System.out.println("Bin im PlanerView-Zweig!");
                planerViewController.setFullVisible(true);
            } else if (currentTemplate.equals("half")) {
                planerViewController.setHalfVisible(true);
            } else if (currentTemplate.equals("quad")) {
                planerViewController.setQuadVisible(true);
            } else if (currentTemplate.equals("quadHalf")) {
                planerViewController.setQuadHalfVisible(true);
            } else {
                planerViewController.setHalfQuadVisible(true);
            }
            changeRightView("EmptyRightView.fxml");
        }
    }

    public void showLeftView() { // to show the left side of the gui
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("leftView.fxml"));
            AnchorPane pageView = (AnchorPane) loader.load();

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

    public void showRightView() { // to show the template-overview at the right side
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("rightView.fxml"));
            AnchorPane templatesView = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setRight(templatesView);
            // Give the controller access to the main app.
            if (loader.getController() instanceof AbstractController) {
                AbstractController controller = loader.getController();
                controller.setMainApp(this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeRightView(String pageTemplate) { // changes the according to the page template
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(pageTemplate));
            Node node = loader.load();
           /* FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource(pageTemplate));
            AnchorPane templatesView = (AnchorPane) loader.load();*/

            rootLayout.setRight(node);

            if (loader.getController() instanceof AbstractController) {
                AbstractController controller = loader.getController();
                controller.setMainApp(this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nameIt() {
        showEditView();
    }

   /* public void showEditControl(Boolean bool){
        leftViewController.setControlPaneVisible(bool);
    }*/

    public void newPage() { //to get a new empty pageview
        showRightView(); // template overview
        showEditView();
        pageViewController.namePaneUnvisible();
        //empty all content
        content1 = "";
        content2 = "";
        content3 = "";
        content4 = "";
    }

    /*public void newModifiedPage() { //to get a new empty pageview
        showRightView(); // template overview
        showEditView();
        pageViewController.namePaneUnvisible();
        //empty all content
        content1 = "";
        content2 = "";
        content3 = "";
        content4 = "";
    }*/

   /* public void deletePage (Element pageElement){
        currentPage.removeContent();
    }*/

    public void openChangeManager() throws Exception{
        leftViewController.setModifyPaneVisible();
        /*planerViewController.s*/
    }

    public void modifyInOrganizer(Element modifiedPage) throws Exception {
        org.addModifiedContent(currentPage.getParent().indexOf(currentPage), modifiedPage);
        org.writeFO(filePath);
        org.foToPdf(filePath);
    }

    public void insertBeforeInOrganizer(Element newPage) throws Exception {

        /*org.insertContent(currentPage.getParent().indexOf(currentPage), newPage);*/
        org.insertContent(currentPage.getParent().indexOf(currentPage) - 1, newPage);
        org.writeFO(filePath);
        org.foToPdf(filePath);
    }

    public void insertAfterInOrganizer(Element newPage) throws Exception {

        /*org.insertContent(currentPage.getParent().indexOf(currentPage) + 1, newPage);*/
        org.insertContent(currentPage.getParent().indexOf(currentPage) + 1, newPage);
        org.writeFO(filePath);
        org.foToPdf(filePath);
    }

    public void addToOrganizer(Element newPage) throws Exception {
        org.addPage(newPage);
        currentPage = newPage;
        org.writeFO(filePath); // writes the XST-FO-Document in Organizer Class
        org.foToPdf(filePath); // creates the PDF-Document in Organizer Class
    }

    public void deleteFromOrganizer() throws Exception{
        int index = currentPage.getParent().indexOf(currentPage);
    org.deletePage(index);
    goToPageIndex(0);
    }

    public void goToPageIndex(int indexnumber) throws Exception { // if user wants to scroll back to older pages
        /*if (currentPage == null) return;*/
        //myElement is the <Element> element in your example
//List implementation:
        /*Element parent = (Element) currentPage.getParent();*/
        Element parent = org.fetchPageParent();
        List children = parent.getChildren();
        /*int myIndex = children.indexOf(currentPage);*/
        /*if (currentPage == children.get(children.size() - 1)) {
            currentTemplate = currentPage.getAttributeValue("id").replaceFirst(".$", "");
            getFoData();
        } else*//* if (myIndex > 0 && myIndex < children.size())*/
        { //get prevSibling
            pageIndex = indexnumber;
            currentPage = (Element) children.get(pageIndex);
            currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", ""); // delete pagecounter to get only template-name
            System.out.println(currentTemplate);
            maxIndex = children.size() - 1;
            /*pageIndex = children.indexOf(currentPage);*/
            System.out.println("aktueller Index: " + pageIndex + " und maxIndex lautet: " + maxIndex);
            System.out.println("aktuelle Seite " + pageIndex + 1);
            getFoData(currentTemplate, pageIndex, maxIndex);
            checkButtons(pageIndex, maxIndex);
            planerViewController.loadPage(currentTemplate, content1, content2, content3, content4, pageIndex);
        }
    }

    public void loadedOrganizer() throws Exception {
        List children = org.fetchPageParent().getChildren();
        Element firstElement = (Element) children.get(0);
        currentPage = firstElement;
        currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", "");
        System.out.println(currentTemplate);
        maxIndex = children.size() - 1;
        pageIndex = children.indexOf(currentPage);
        System.out.println("Seite " + pageIndex + 1);
        getFoData(currentTemplate, pageIndex, maxIndex);
        checkButtons(pageIndex, maxIndex);
        planerViewController.loadPage(currentTemplate, content1, content2, content3, content4, pageIndex);
    }

    public void nextPage() throws Exception { //User klickt auf nächste Seite
        Element parent = (Element) currentPage.getParent();
        List children = parent.getChildren();
        pageIndex = children.indexOf(currentPage);
        System.out.println("pageIndex ist " + pageIndex);
        maxIndex = children.size() - 1;
        System.out.println("Maximaler Index ist " + maxIndex);
        Element nextElement = (Element) children.get(pageIndex + 1);
        currentPage = nextElement;
        pageIndex = children.indexOf(currentPage);
        currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", "");
        getFoData(currentTemplate, pageIndex, maxIndex);
        checkButtons(pageIndex, maxIndex);
        planerViewController.loadPage(currentTemplate, content1, content2, content3, content4, pageIndex);
    }

    public void prevPage() throws Exception {//User klickt auf vorherige Seite
        Element parent = (Element) currentPage.getParent();
        List children = parent.getChildren();
        maxIndex = children.size() - 1;
        pageIndex = children.indexOf(currentPage);
        Element nextElement = (Element) children.get(pageIndex - 1);
        currentPage = nextElement;
        pageIndex = children.indexOf(currentPage);
        currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", "");
        getFoData(currentTemplate, pageIndex, maxIndex);
        checkButtons(pageIndex, maxIndex);
        planerViewController.loadPage(currentTemplate, content1, content2, content3, content4, pageIndex);
    }

    public void goToFoPage(int pageIndex) throws Exception {
        List children = org.fetchPageParent().getChildren();
        Element pageElement = (Element) children.get(pageIndex);
        currentPage = pageElement;
        this.pageIndex = pageIndex;
        currentTemplate = currentPage.getAttributeValue("id").replaceAll("[0-9]", "");
        System.out.println(currentTemplate);
        maxIndex = children.size() - 1;
        /* int pageindex = children.indexOf(currentPage);*/
        System.out.println("Seite " + pageIndex + 1);
        getFoData(currentTemplate, this.pageIndex, maxIndex);
        checkButtons(this.pageIndex, maxIndex);
        pageViewController.loadPage(currentTemplate, content1, content2, content3, content4);
    }

    /*public void changeSaveButton(){
        pageViewController.changeTakeButton();
    }*/

    private void getFoData(String currentTemplate, int pageindex, int maxIndex) throws Exception { // filters all graphic paths from children/descendants and assigns to its content variable
        Iterator<Element> graphics = currentPage.getDescendants(new ElementFilter("external-graphic"));// search for the element <fo:external-graphic>
        ArrayList<String> paths = new ArrayList<>();
        graphics.forEachRemaining((content) -> paths.add(content.getAttributeValue("src"))); //writes the source-paths into an array-list
        if (currentTemplate.equals("fullpage")) {
            content1 = paths.get(0).replaceFirst("^.*/", "");
        } else if (currentTemplate.equals("half")) {
            content1 = paths.get(0).replaceFirst("^.*/", "");
            content2 = paths.get(1).replaceFirst("^.*/", "");
        } else if (currentTemplate.equals("quad")) {
            content1 = paths.get(0).replaceFirst("^.*/", "");
            content2 = paths.get(1).replaceFirst("^.*/", "");
            content3 = paths.get(2).replaceFirst("^.*/", "");
            content4 = paths.get(3).replaceFirst("^.*/", "");
        } else { // template is halfQuad or quadHalf
            content1 = paths.get(0).replaceFirst("^.*/", "");
            content2 = paths.get(1).replaceFirst("^.*/", "");
            content3 = paths.get(2).replaceFirst("^.*/", "");
        }
        System.out.println("Get FO-Data: Der Content ist: " + content1 + ", " + content2 + ", " + content3 + ", " + content4);
        System.out.println("Get FO-Data: Current Template ist: " + currentTemplate);
        Iterator<Element> blockIterator = currentPage.getParent().getDescendants(new ElementFilter("block"));
        ArrayList<String> fullIds = new ArrayList<>();
        ArrayList<Integer> indexNumbers =new ArrayList<>();
        blockIterator.forEachRemaining((iD)-> fullIds.add(iD.getAttributeValue("id")));
        for( String element : fullIds ){
            if (element!= null) {
                indexNumbers.add(Integer.parseInt(element.replaceAll("[^0-9]", "")));
            }
        }
        maxIdNumber = Collections.max(indexNumbers); // Um größte Zahl aller IDs zu erhalten
        System.out.println("Die höchste Nummer ist: " + maxIdNumber); // Testausgabe
    }

      private void checkButtons(int pageIndex, int maxIndex){
          if (pageIndex == maxIndex ){
              planerViewController.setNextButton(true);
          } else {
              planerViewController.setNextButton(false);
          }  if (pageIndex == 0){
              planerViewController.setPrevButton(true);
          } else {
              planerViewController.setPrevButton(false);
          }
      }

      public void SetNamePane(){
        pageViewController.namePaneUnvisible();
      }

      public void changePageViewSaveButtons(){
          pageViewController.takeButton.setVisible(false);
          pageViewController.takeChangeButton.setVisible(true);
          System.out.println("InsertSaveGesetzt");
      }

    public void changePageInsertBeforeButton(){
        pageViewController.takeButton.setVisible(false);
        pageViewController.insertBeforeButton.setVisible(true);
        System.out.println("InsertBeforeGesetzt");
    }

    public void changePageInsertAfterButton(){
        pageViewController.takeButton.setVisible(false);
        pageViewController.insertAfterButton.setVisible(true);
        System.out.println("InsertAfterGesetzt");
    }

      public void FoToPdf(){
      }

      // Getter & Setter

    public Button getActiveButton() {
        return activeButton;
    }

    public void setActiveButton(Button activeButton) {
        this.activeButton = activeButton;
    }

    public void setAddNewPageButtonVisible(){
        pageViewController.takeButton.setVisible(false);
        pageViewController.addNewPageButton.setVisible(true);
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {

        this.content1 = content1;
        System.out.println("Aus der Set-Methode: " + content1);
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
        System.out.println("current Template is: " + currentTemplate);
    }

    public void setCurrentPage(Element currentPage) {
        this.currentPage = currentPage;
    }

    public Element getCurrentPage() {
        return currentPage;
    }

    public FullPageViewController getPageViewController() {
        return pageViewController;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getMaxIdNumber() {
        return maxIdNumber;
    }

    public Organizer getOrg() {
        return org;
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