package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class LeftViewController extends AbstractController {

    @FXML
    private AnchorPane ControlPane;


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




    private void exportToPdf()throws Exception{

    }





}
