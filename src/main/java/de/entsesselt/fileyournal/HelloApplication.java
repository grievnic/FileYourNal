package de.entsesselt.fileyournal;

import de.entsesselt.fileyournal.model.Organizer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom2.xpath.XPathFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    private Document doc;
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

        //Organizer-Objekt erstellen und per JDOM eine XSL-FO erstellen
        Organizer org = new Organizer();
        this.org = org;

        org.readFO();
        /*createOrganizer(org);*/
        /*org.addPageTemplate("test");*/
        System.out.println(org.addPageTemplate("QuadQuad"));

       /* org.foToPdf(); // Test zum Wandeln der Fo zu PDF aus oben erstellter Datei*/

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

    public void showPageView() { // shows the empty page-view
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PageView.fxml"));
            AnchorPane pageView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            FullPageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(pageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFullPageView() { // shows the page-view that equals the fullpage-template
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("FullPageView.fxml"));
            AnchorPane fullView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            FullPageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(fullView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showQuadQuadPageView() { // shows the page-view that equals the quad-template
        try {
            // Load QuadQuad Template-View
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("QuadPageView.fxml"));
            AnchorPane quadView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            FullPageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(quadView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHalfHalfPageView() { // shows the page-view that equals the half-template
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("HalfHalfPageView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            FullPageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showQuadHalfPageView() { // shows the page-view that equals the quadHalf-template
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("QuadHalfPageView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            FullPageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHalfQuadPageView() { // shows the page-view that equals the halfQuad-template
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("HalfQuadPageView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            FullPageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
        } catch (IOException e) {
            e.printStackTrace();
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
            AbstractController controller;
            controller = loader.getController();
            controller.setMainApp(this);

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

    public void newPage(){ //to get a new empty pageview
        showRightView(); // template overview
        showPageView(); // empty page
        //empty all content
        content1 = "";
        content2 = "";
        content3 = "";
        content4 = "";
    }

    public void addToOrganizer (Element newPage) throws Exception {
        org.addPage(newPage);
        currentPage = newPage;
        org.writeFO(); // writes the XST-FO-Document in Organizer Class
        org.foToPdf(); // creates the PDF-Document in Organizer Class
    }

    public void goBack(){ // if user wants to scroll back to older pages
        /*String pageTextPath = "root/page-sequence/flow/block";*/

        String previousOne = "preceding-sibling::*";
        /*XPathExpression<Object> expr;*/
        XPathFactory xPathFactory = XPathFactory.instance();
        /*XPathExpression<Object> xpathPage =  xPathFactory.compile(pageTextPath + "[id = ‘" + pageID + "‘]");*/

        /*currentPage.evaluateFirst(org.getCurrentOrganizer());*/
        currentPage = (Element) xPathFactory.compile(previousOne);
        currentTemplate = currentPage.getAttributeValue("id").replaceFirst(".$","");
        System.out.println("das currentTemplate lautet: " + currentTemplate);
        getFoData();
/*
        String currentContent1 = currentPage.getChild("block-container").getChild("block").getChild("external-graphic").getAttributeValue("src");
*/
        /*List<Element>containerChildren = currentPage.getChildren();*/

    }

      public void nextPage(){//User klickt auf naechste Seite
        XPathFactory xPathFactory = XPathFactory.instance();
        String nextOne = "following-sibling::*";
        currentPage = (Element) xPathFactory.compile(nextOne);
        currentTemplate = currentPage.getAttributeValue("id").replaceFirst(".$","");
        getFoData();
      }


      private void getFoData() { // filters all graphic paths from children/descendants and assigns to its content variable
          List<Element> graphics = (List<Element>) currentPage.getDescendants(new ElementFilter("external-graphic")); // search for the element <fo:external-graphic>
          List<String> paths = new ArrayList<String>();
          for (Element content : graphics) {
              /*content.getAttributeValue("src");*/
              paths.add(content.getAttributeValue("src",fo)); //writes the source-paths into an array-list
              content1 = paths.get(0);
              if (!currentTemplate.equals("fullpage")){
                  content2 = paths.get(1);
              } else if (!currentTemplate.equals("half")){
                  content3 = paths.get(2);
              } else if (currentTemplate.equals("quad")) {
                  content4 = paths.get(3);
              }
      }



   /* @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GuiView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1200);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }*/

    /*public void writeFO(Document doc) {
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        try (FileOutputStream fos = new FileOutputStream(new File(FILENAME))) {
            XMLOutputter op = new XMLOutputter(format);
            op.output(doc, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public Button getActiveButton() {
        return activeButton;
    }

    public void setActiveButton(Button activeButton) {
        this.activeButton = activeButton;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String inhalt1) {

        this.content1 = inhalt1;
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

    public Organizer getOrg() {
        return org;
    }

    public static void main(String[] args) {
        launch();
    }

}