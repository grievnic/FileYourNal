package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class LeftViewController extends AbstractController {

    @FXML
    private AnchorPane ControlPane;

    @FXML
    private AnchorPane modifyPane;

    @FXML
    private Button modifyCurrentButton;

    @FXML
    private Button deleteCurrentButton;

    @FXML
    private Button insertBeforeButton;

    @FXML
    private Button insertAfterButton;

    @FXML
    private Button addPagesButton;


   /* @FXML
    void setControlPaneVisible(Boolean bool){
        ControlPane.setVisible(bool);
    }*/



    @FXML
    protected void backToStart() throws Exception{
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Möglicher Datenverlust!");
        a.setContentText("Dein letzter Seitenentwurf ist noch nicht gespeichert, trotzdem zum Programmstart gehen?");

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            mainApp.showStartView();
        }
    }


    // ChangeManager

    @FXML
    public void setModifyPaneVisible(){
        modifyPane.setVisible(true);
    }

    @FXML
    private void modifyCurrentPage() throws Exception {
        mainApp.showEditView();//
        mainApp.goToFoPage(mainApp.getPageIndex());
        mainApp.SetNamePane();
        mainApp.changePageViewSaveButtons();
    }

    @FXML
    private void insertBeforeCurrent() throws Exception{

        mainApp.showEditView();//

        mainApp.newPage();
        mainApp.changePageInsertBeforeButton();
    }

    @FXML
    private void insertAfterCurrent() throws Exception{

        mainApp.showEditView();//

        mainApp.newPage();
        mainApp.changePageInsertAfterButton();
    }

    @FXML
    private void addAtEnd() throws Exception{

        mainApp.showEditView();//
        mainApp.newPage();
        mainApp.setAddNewPageButtonVisible();

    }

    @FXML
    private void deleteCurrentPage() throws Exception{
    mainApp.deleteFromOrganizer();
    }

    @FXML
    private void exportToPdf()throws Exception{

    }





}
