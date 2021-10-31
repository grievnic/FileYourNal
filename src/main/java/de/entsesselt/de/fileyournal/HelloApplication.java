package de.entsesselt.de.fileyournal;

import de.entsesselt.de.fileyournal.model.Organizer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Organizer org;

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
        org.readFO();
        /*createOrganizer(org);*/
        /*org.addPageTemplate("test");*/
        System.out.println(org.addPageTemplate("QuadQuad"));

        org.foToPdf(); // Test zum Wandeln der Fo zu PDF aus oben erstellter Datei

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

    public void showStartView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("StartView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            StartViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPageView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PageView.fxml"));
            AnchorPane pageView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            PageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(pageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFullPageView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PageFullView.fxml"));
            AnchorPane fullView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            PageFullViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(fullView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showQuadQuadPageView() {
        try {
            // Load QuadQuad Template-View
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PageQuadQuadView.fxml"));
            AnchorPane quadView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            /*PageQuadQuadViewController controller = loader.getController();
            controller.setMainApp(this);*/

            // Set person overview into the center of root layout.
            rootLayout.setCenter(quadView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHalfHalfPageView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("HalfHalfPageView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();
            // Give the controller access to the main app.
            PageViewController controller = loader.getController();
            controller.setMainApp(this);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLeftView() {
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

    public void showRightView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("rightView.fxml"));
            AnchorPane templatesView = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setRight(templatesView);
            // Give the controller access to the main app.
            RightViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeRightView(String pageTemplate) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource(pageTemplate));
            AnchorPane templatesView = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setRight(templatesView);
            // Give the controller access to the main app.
            RightViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
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


    public static void main(String[] args) {
        launch();
    }

}