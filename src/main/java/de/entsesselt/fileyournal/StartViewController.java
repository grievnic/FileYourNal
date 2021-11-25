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
    Button newPlaner;

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
        mainApp.nameIt();
            newPlaner.setVisible(false);
            planer.setVisible(false);

    }



    public void loadFoFromFile(ActionEvent event) throws Exception{
        //aus dem Knoten (also der Button), der das Event ausgelöst, die Stage ermitteln
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        if (mainApp.getFilePath() == null) { // no user given file directory
            fileChooser.setInitialDirectory(new File(INITIAL_DIRECTORY)); // path from the app directory
        } else {//  goto user given file directory
            String directoryPath = mainApp.getFilePath().substring(0,mainApp.getFilePath().lastIndexOf("/")); // only path without filename
            fileChooser.setInitialDirectory(new File(directoryPath));
        }
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".fo-Files", "*.fo"));
        selectedFile = fileChooser.showOpenDialog(theStage);
        //ausgewähltes Pic in GUI anzeigen
        if (selectedFile != null) {
            mainApp.startNewOrganizer(selectedFile.getName(), selectedFile.getAbsolutePath());
            mainApp.setFilePath(selectedFile.getPath());
            System.out.println(mainApp.getFilePath());
            mainApp.showPlanerView();
            mainApp.loadedOrganizer();
            System.out.println("Die gewählte Datei heißt: " + selectedFile.getName() + "und ist: " + selectedFile.getAbsolutePath() );
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