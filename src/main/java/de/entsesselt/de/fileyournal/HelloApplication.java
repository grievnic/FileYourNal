package de.entsesselt.de.fileyournal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FileYOURnal");

        initRootLayout();
        showPageView();
        showLeftView();
        showRightView();
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

    public void showPageView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("PageView.fxml"));
            AnchorPane pageView = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(pageView);
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
            ScrollPane pageView = (ScrollPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setRight(pageView);
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