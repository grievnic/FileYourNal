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
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom2.xpath.XPathFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        currentTemplate = "fullpage";
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("FullPageView.fxml"));
            AnchorPane fullView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            pageViewController = loader.getController();
            pageViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(fullView);
            changeRightView("EmptyRightView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTest() {
        currentTemplate = "quad";
        try {
            // Load QuadQuad Template-View
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("QuadPageView.fxml"));
            AnchorPane quadView = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void showQuadQuadPageView() { // shows the page-view that equals the quad-template
        currentTemplate = "quad";
        try {
            // Load QuadQuad Template-View
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("QuadPageView.fxml"));
            AnchorPane quadView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            pageViewController = loader.getController();
            pageViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(quadView);
            changeRightView("EmptyRightView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHalfHalfPageView() { // shows the page-view that equals the half-template
        currentTemplate = "half";
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("HalfHalfPageView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            pageViewController = loader.getController();
            pageViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
            changeRightView("EmptyRightView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showQuadHalfPageView() { // shows the page-view that equals the quadHalf-template
        currentTemplate = "quadHalf";
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("QuadHalfPageView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            pageViewController = loader.getController();
            pageViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
            changeRightView("EmptyRightView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHalfQuadPageView() { // shows the page-view that equals the halfQuad-template
        currentTemplate = "halfQuad";
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("HalfQuadPageView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            pageViewController = loader.getController();
            pageViewController.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
            changeRightView("EmptyRightView.fxml");
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

    public void goBack() throws Exception{ // if user wants to scroll back to older pages

        //myElement is the <Element> element in your example
//List implementation:
        Element parent = (Element) currentPage.getParent();
        List children = parent.getChildren();
        int myIndex = children.indexOf(currentPage);
        if (currentPage == children.get(children.size() - 1)){
            currentTemplate = currentPage.getAttributeValue("id").replaceFirst(".$", "");
            getFoData();
        } else if(myIndex > 0 && myIndex < children.size()) { //get prevSibling
            Element prevElement = (Element)children.get(myIndex - 1);
            System.out.println("Current page is: " + currentPage.getAttributeValue("id"));
            System.out.println("PrevElement page is: " + prevElement.getAttributeValue("id"));
            currentPage = prevElement;
            System.out.println("neue Current page is: " + currentPage.getAttributeValue("id"));
            currentTemplate = currentPage.getAttributeValue("id").replaceFirst(".$", "");
            System.out.println(currentTemplate);
            getFoData();
        }

        /*String pageTextPath = "root/page-sequence/flow/block";*/
       /* Element stellvertreter;
        List<Object> liste;
        System.out.println("aus goBack: Die ID lautet: " + currentPage.getAttributeValue("id"));
        String previousOne = "preceding-sibling::*[1]";
        XPathExpression<Object> expr;
        XPathFactory xPathFactory = XPathFactory.instance();
        *//*XPathExpression<Object> xpathPage =  xPathFactory.compile(pageTextPath + "[id = ‘" + pageID + "‘]");*//*
        expr = xPathFactory.compile("/*");
        *//*stellvertreter= (Element) expr.evaluateFirst(currentPage);*//*
        liste = expr.evaluate(org.getCurrentOrganizer());
        System.out.println("Liste: " + liste.size());
        System.out.println(previousOne);*/
       /* if (stellvertreter != null) {
            currentPage = stellvertreter;
            currentTemplate = currentPage.getAttributeValue("id").replaceFirst(".$", "");
            System.out.println("das currentTemplate lautet: " + currentTemplate);
            getFoData();
        } else { System.out.println("schade");}*/
/*
        String currentContent1 = currentPage.getChild("block-container").getChild("block").getChild("external-graphic").getAttributeValue("src");
*/
        /*List<Element>containerChildren = currentPage.getChildren();*/

    }

      public void nextPage()throws Exception{//User klickt auf naechste Seite
        XPathFactory xPathFactory = XPathFactory.instance();
        String nextOne = "following-sibling::*";
        currentPage = (Element) xPathFactory.compile(nextOne);
        currentTemplate = currentPage.getAttributeValue("id").replaceFirst(".$","");
        getFoData();
      }


      private void getFoData() throws Exception { // filters all graphic paths from children/descendants and assigns to its content variable

         /* Element parent = (Element) currentPage.getParent();
          List children = parent.getChildren();*/

          Iterator<Element> graphics= currentPage.getDescendants(new ElementFilter("external-graphic"));// search for the element <fo:external-graphic>
          ArrayList<String> paths = new ArrayList<>();
          graphics.forEachRemaining((content) -> paths.add(content.getAttributeValue("src")));


              /*content.getAttributeValue("src");
              paths.add(content.getAttributeValue("src",fo)); *///writes the source-paths into an array-list

              if (currentTemplate.equals("fullpage")){
                  content1 = paths.get(0).replaceFirst("^.*/", "");
              } else if (currentTemplate.equals("half")){
                  content1 = paths.get(0).replaceFirst("^.*/", "");
                  content2 = paths.get(1).replaceFirst("^.*/", "");
              } else if (currentTemplate.equals("quad")) {
                  content1 = paths.get(0).replaceFirst("^.*/", "");
                  content2 = paths.get(1).replaceFirst("^.*/", "");
                  content3 = paths.get(2).replaceFirst("^.*/", "");
                  content4 = paths.get(3).replaceFirst("^.*/", "");
              } else {
                  content1 = paths.get(0).replaceFirst("^.*/", "");
                  content2 = paths.get(1).replaceFirst("^.*/", "");
                  content3 = paths.get(2).replaceFirst("^.*/", "");
              }
              System.out.println("Der Content ist: " + content1 + ", " + content2 + ", " +content3 + ", " + content4);
              System.out.println("Current Template ist: " + currentTemplate);
              pageViewController.loadPage(currentTemplate, content1, content2, content3, content4);
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

    public FullPageViewController getPageViewController() {
        return pageViewController;
    }

    public Organizer getOrg() {
        return org;
    }



    public static void main(String[] args) {
        launch();
    }

}