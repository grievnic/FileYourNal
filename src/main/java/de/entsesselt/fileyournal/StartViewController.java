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

/**
 * Controller of the StartView
 * Here the user can decide whether he wants to create a new planner or load a planner that has already been created.
 * @author Nicole Grieve (nicole.grieve@stud.th-luebeck.de)
 * @version 1.0
 *
 */

public class StartViewController extends AbstractController {
    @FXML
    Button newOrganizerButton;

    @FXML
    ImageView planer;


    private static final String INITIAL_DIRECTORY = Paths.get(".").toAbsolutePath().normalize().toString();

    /**
     * button click on "Organizer erstellen" to start a new organizer
     */
    @FXML
    protected void startNewPlanerClick() {
        mainApp.showEditView();
    }

    /**
     * Method loads an organizer .fo-File from directory, to represent it in the
     * @param event clickEvent: Button "Organizer laden"
     */
    @FXML
    public void loadFoFromFile(ActionEvent event) {
        //gets the stage from the node that triggered the event
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(INITIAL_DIRECTORY)); // path from the app directory
        fileChooser.getExtensionFilters().addAll( // filter to make only .fo files selectable
                new FileChooser.ExtensionFilter(".fo-Files", "*.fo"));
        File selectedFile = fileChooser.showOpenDialog(theStage);
        //starts a new organizer-instance, gets file information and loads page content to show in flick through view
        if (selectedFile != null) {
            mainApp.startNewOrganizer(selectedFile.getName(), selectedFile.getAbsolutePath());
            mainApp.showPlanerView();
            mainApp.loadedOrganizer();
        }
    }
}