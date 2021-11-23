package de.entsesselt.fileyournal;

import javafx.fxml.FXML;

public class LeftViewController extends AbstractController {


    @FXML
    protected void exitEditor() throws Exception {
        mainApp.showPlanerView();//
        mainApp.goToFirstPage();
        System.out.println("exit passiert");
    }

    private void exportToPdf()throws Exception{

    }





}
