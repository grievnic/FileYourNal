package de.entsesselt.fileyournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.Date;
import java.util.Optional;

public class LeftViewController extends AbstractController {

    @FXML
    private AnchorPane modifyPane;

    @FXML
    private Button exitEditor;

    @FXML
    private ImageView backToStartButtonView;

    @FXML
    private Button pdfButton;

    @FXML
    private ImageView pdfButtonView;


    // Menu Buttons

    @FXML
    private void exportToPdf(ActionEvent event) throws Exception{
        //aus dem Knoten (also der Button), der das Event ausgelöst, die Stage ermitteln
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        if (mainApp.getFilePath() == null) { // no user given file directory
            System.out.println("Initial Filename: " + mainApp.getFileName());
            fileChooser.setInitialDirectory(new File(mainApp.getFilePath()));// path from the app directory
        } else {//  goto user given file directory
            String directoryPath = mainApp.getFilePath().substring(0,mainApp.getFilePath().lastIndexOf("/")); // only path without filename
            fileChooser.setInitialDirectory(new File(directoryPath) );
            fileChooser.setInitialFileName(mainApp.getFileName());
        }
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".pdf-Files", "*.pdf"));
        File filename = fileChooser.showSaveDialog(theStage);
        System.out.println("Der Filename lautet: " + filename);
        mainApp.foToPdf(filename.getPath());
        pdfFeedback();
        delay(2000);
        mainApp.showStartView();
    }

    private static void delay(int t){
        long ende = (new Date()).getTime() + t;
        while( (new Date()).getTime() < ende ){
        }
    }
    public void pdfFeedback(){
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("Deine PDF wurde exportiert!");
        a.show();
    }


    @FXML
    protected void backToStart() throws Exception{
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Zur Startseite zurückkehren");
        a.setContentText("Bist Du sicher, dass Du diese Ansicht verlassen möchtest?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            mainApp.showStartView();
        }
    }

    // button-delegation

    @FXML
    public void showPdfExportButton(){
        if(mainApp.getFilePath().equals("")){
            pdfButtonView.setDisable(true);
            pdfButton.setDisable(true);
        } else
        pdfButton.setDisable(false);
        pdfButtonView.setDisable(false);
    }

    @FXML
    public void showBackToStartButton(){
        exitEditor.setVisible(true);
        backToStartButtonView.setVisible(true);
    }


    // modification manager

    @FXML
    public void setModifyPaneVisible(Boolean bool){
        modifyPane.setVisible(bool);
    }

    @FXML
    private void modifyCurrentPage() throws Exception {
        mainApp.showEditView();//
        mainApp.goToFoPage(mainApp.getPageIndex());
        mainApp.setNamePane();
        mainApp.changePageViewSaveButtons();
        mainApp.changeRightView("InfoRightView.fxml");
    }

    @FXML
    private void insertBeforeCurrent() throws Exception{
        mainApp.showEditView();//
        mainApp.newPage();
        mainApp.changePageInsertBeforeButton();
        mainApp.showRightView();
    }

    @FXML
    private void insertAfterCurrent() throws Exception{
        mainApp.showEditView();//
        mainApp.newPage();
        mainApp.changePageInsertAfterButton();
        mainApp.showRightView();
    }

    @FXML
    private void addAtEnd() throws Exception{
        mainApp.showEditView();//
        mainApp.newPage();
        mainApp.setAddNewPageButtonVisible();
        mainApp.showRightView();
    }

    @FXML
    private void deleteCurrentPage() throws Exception{
    mainApp.deleteFromOrganizer();
    }
}
