package de.entsesselt.fileyournal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller of the ContentViews: (because of the same code requirement)
 * FullContentView
 * HalfContentView
 * User can select content elements to fill page areas.
 *
 * @author Nicole Grieve (nicole.grieve@stud.th-luebeck.de)
 * @version 1.0
 *
 */

public class ContentViewController extends AbstractController{

    String contentName;

    /**
     * gets the active content-button and reads the user data with the filename
     * initializes that the chosen content will be shown
     * @param event selected content click
     */
    @FXML
    public void selectContent(ActionEvent event) {
        contentName = ((Button) event.getSource()).getUserData().toString();
        loadContent(contentName);
    }

    /**
     * initializes that the content will be shown in the page
     * @param contentName name of content
     */
    @FXML
    public void loadContent(String contentName) {
        EditViewController control = mainApp.getEditViewController();
        control.addContent(contentName);
    }

    /**
     * Shows the TemplateView
     */
    public void backToTemplate() {
        mainApp.showRightView();
    }
}
