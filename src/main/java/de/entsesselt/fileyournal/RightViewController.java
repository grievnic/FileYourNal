package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Controller of the RightView(s):
 * rightView
 * EmptyRightView
 * InfoRightView
 * guides the user through the template and content overview
 * @author Nicole Grieve (nicole.grieve@stud.th-luebeck.de)
 * @version 1.0
 *
 */

public class RightViewController extends AbstractController {

    @FXML
    AnchorPane pageAreaTemplatePane;

    @FXML
    Label pdfPathLabel;

    @FXML
    Text pdfFeedbackText;

    String template = "";


    /**
     * shows the Quad-Page-View in the Center of the GUI and takes the template-Selection away
     */
    @FXML
    protected void startQuadQuadTemplate() {
        mainApp.showPageTemplate("editViewController", "quad");
        template = "quad";
        sendTemplate();
    }

    /**
     * shows the Full-Page-View in the Center of the GUI and takes the template-Selection away
     */
    @FXML
    protected void startFullTemplate() {
        mainApp.showPageTemplate("editViewController", "fullpage");
        template = "full";
        sendTemplate();
    }

    /**
     * shows the Half-Page-View in the Center of the GUI and takes the template-Selection away
     */
    @FXML
    protected void startHalfHalfTemplate() {
        mainApp.showPageTemplate("editViewController", "half");
        template = "half";
        sendTemplate();
    }


    /**
     * shows the QuadHalf-Page-View in the Center of the GUI and takes the template-Selection away
     */
    @FXML
    protected void startQuadHalfTemplate() {
        mainApp.showPageTemplate("editViewController", "quadHalf");
        template = "quadHalf";
        sendTemplate();
    }

    /**
     * shows the HalfQuad-Page-View in the Center of the GUI and takes the template-Selection away
     */
    @FXML
    protected void startHalfQuadTemplate() {
        mainApp.showPageTemplate("editViewController", "halfQuad");
        template = "halfQuad";
        sendTemplate();
    }

    /**
     * sets the info pane unvisible for an empty right view
     */
    @FXML
    public void emptyIt() {
        pageAreaTemplatePane.setVisible(false);
    }

    /**
     * sets the selected template at the mainApp
     */
    private void sendTemplate() {
        mainApp.setCurrentTemplate(template);
    }

    /**
     * takes the template-Selection away and shows info
     */
    public void emptyRightView() {
        mainApp.changeRightView("EmptyRightView.fxml");
    }

    /**
     * shows the template overview
     */
    public void showTemplateView() {
        mainApp.showRightView();
        emptyIt();
    }

    private void createMonthCal(){

    }

    /**
     * test to handle multi-page content
     */
    @FXML
    private void createQuartCal(){
        startFullTemplate();
        mainApp.calComposer("Quart");
    }
}

