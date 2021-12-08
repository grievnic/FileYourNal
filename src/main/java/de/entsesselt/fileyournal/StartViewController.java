package de.entsesselt.fileyournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Paths;

public class StartViewController extends AbstractController {
    @FXML
    Button newOrganizerButton;

    @FXML
    ImageView planer;

    @FXML
    ImageView page;

    private File selectedFile = null;
    private static final String INITIAL_DIRECTORY = Paths.get(".").toAbsolutePath().normalize().toString();

    // Reference to the main application.
    /*private HelloApplication mainApp;*/

    @FXML
    protected void startNewPlanerClick() throws Exception {
        mainApp.showEditView();
    }

    /**
     * Method loads an organizer .fo-File from directory, to represent it in the
     * @param event clickEvent: Button "Organizer laden"
     * @throws Exception if error occurs
     */
    @FXML
    public void loadFoFromFile(ActionEvent event) throws Exception{
        //gets the stage from the node that triggered the event
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(INITIAL_DIRECTORY)); // path from the app directory
        fileChooser.getExtensionFilters().addAll( // filter to make only .fo files selectable
                new FileChooser.ExtensionFilter(".fo-Files", "*.fo"));
        selectedFile = fileChooser.showOpenDialog(theStage);
        //starts a new organizer-instance, gets file information and loads page content to show in flick through view
        if (selectedFile != null) {
            mainApp.startNewOrganizer(selectedFile.getName(), selectedFile.getAbsolutePath());
            mainApp.setFilePath(selectedFile.getPath());
            mainApp.setFileName(selectedFile.getName());
            System.out.println(mainApp.getFilePath());
            mainApp.showPlanerView();
            mainApp.loadedOrganizer();
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