package de.entsesselt.fileyournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.Optional;

/**
 * Controller of the LeftView:
 * contains some buttons like pdf export and the modification-manager
 * @author Nicole Grieve (nicole.grieve@stud.th-luebeck.de)
 * @version 1.0
 *
 */

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


    /**
     * opens a directory dialogue, to get the place,where to store the pdf file
     * the given filename and path is pre-filled
     * @param event click on button "Export als PDF"
     * @throws Exception if error occurs
     */
    @FXML
    private void exportToPdf(ActionEvent event) throws Exception{
        //gets the stage from the node that triggered the event
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        /*if (mainApp.getFilePath() == null) { // if no user given file directory
            fileChooser.setInitialDirectory(new File(mainApp.getFilePath()));// user given path from the app directory
        } else {*///  goto user given file directory
            String directoryPath = mainApp.getFilePath().substring(0,mainApp.getFilePath().lastIndexOf("/")); // only path without filename
            fileChooser.setInitialDirectory(new File(directoryPath) );
            fileChooser.setInitialFileName(mainApp.getFileName());
       /* }*/
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".pdf-Files", "*.pdf"));
        File filename = fileChooser.showSaveDialog(theStage);
        mainApp.foToPdf(filename.getPath());
        pdfFeedback();
        mainApp.showStartView();
    }

    /**
     * gives feedback when the export is complete
     */
    public void pdfFeedback(){
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("Deine PDF wurde exportiert!");
        a.show();
    }

    /**
     *shows the start view, if Button clicked "zur??ck zum Programmstart"
     */
    @FXML
    protected void backToStart() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Zur Startseite zur??ckkehren");
        a.setContentText("Bist Du sicher, dass Du diese Ansicht verlassen m??chtest?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) mainApp.showStartView();
    }

    // button-delegation

    /**
     * service to control the button from outside the class
     * button and imageView are separated, to control the placement of the image
     */
    @FXML
    public void showPdfExportButton(){
        if(mainApp.getFilePath().equals("")){
            pdfButtonView.setDisable(true); // icon
            pdfButton.setDisable(true); // button
        } else
        pdfButton.setDisable(false);
        pdfButtonView.setDisable(false);
    }

    /**
     * Button "zur??ck zum Programmstart" should only be visible when you are no longer at the start
     * service to handle it from mainApp
     */
    @FXML
    public void showBackToStartButton(){
        exitEditor.setVisible(true);
        backToStartButtonView.setVisible(true);
    }


    // modification manager

    /**
     * shows the "??nderungsmanager", if there is anything to modify at the flipping through mode
     * @param bool true - visible
     */
    @FXML
    public void setModifyPaneVisible(Boolean bool){
        modifyPane.setVisible(bool);
    }

    /**
     * click on "aktuelle Seite ??ndern"
     * change to editView, loads current page in editmode, shows the appropriate save button
     */
    @FXML
    private void modifyCurrentPage() {
        mainApp.showEditView();
        mainApp.goToFoPage(mainApp.getPageIndex());
        mainApp.setNamePane();
        mainApp.changePageViewSaveButtons(); // shows the appropriate save button with another onAction-method
        mainApp.changeRightView("InfoRightView.fxml");
    }

    /**
     * click on "Seite einf??gen (vor dieser)"
     * change to editView, loads current page in editmode, shows the appropriate save button
     */
    @FXML
    private void insertBeforeCurrent() {
        mainApp.showEditView();//
        mainApp.newPage();
        mainApp.changePageInsertBeforeButton(); // shows the appropriate save button with another onAction-method
        mainApp.showRightView();
    }

    /**
     * click on "Seite einf??gen (nach dieser)"
     * change to editView, loads current page in editmode, shows the appropriate save button
     */
    @FXML
    private void insertAfterCurrent() {
        mainApp.showEditView();
        mainApp.newPage();
        mainApp.changePageInsertAfterButton(); // shows the appropriate save button with another onAction-method
        mainApp.showRightView();
    }

    /**
     * click on "hinten Seite(n) hinzuf??gen"
     * change to editView, loads current page in editmode, shows the appropriate save button
     */
    @FXML
    private void addAtEnd() {
        mainApp.showEditView();
        mainApp.newPage();
        mainApp.setAddNewPageButtonVisible(); // shows the appropriate save button with another onAction-method
        mainApp.showRightView();
    }

    /**
     * click on "aktuelle Seite l??schen"
     * deletes current page
     */
    @FXML
    private void deleteCurrentPage() {
    mainApp.deleteFromOrganizer(); // deletes the current page
    }
}
