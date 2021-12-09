package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
     * @throws Exception if error occurs
     */
    @FXML
    protected void startQuadQuadTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "quad");
        template = "quad";
        sendTemplate();
        emptyRightView();
    }

    /**
     * shows the Full-Page-View in the Center of the GUI and takes the template-Selection away
     * @throws Exception if error occurs
     */
    @FXML
    protected void startFullTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "fullpage");
        template = "full";
        sendTemplate();
        emptyRightView();
    }

    /**
     * shows the Half-Page-View in the Center of the GUI and takes the template-Selection away
     * @throws Exception if error occurs
     */
    @FXML
    protected void startHalfHalfTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "half");
        template = "half";
        sendTemplate();
        emptyRightView();
    }


    /**
     * shows the QuadHalf-Page-View in the Center of the GUI and takes the template-Selection away
     * @throws Exception if error occurs
     */
    @FXML
    protected void startQuadHalfTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "quadHalf");
        template = "quadHalf";
        sendTemplate();
        emptyRightView();
    }

    /**
     * shows the HalfQuad-Page-View in the Center of the GUI and takes the template-Selection away
     * @throws Exception if error occurs
     */
    @FXML
    protected void startHalfQuadTemplate() throws Exception {
        mainApp.showPageTemplate("pageViewController", "halfQuad");
        template = "halfQuad";
        sendTemplate();
        emptyRightView();
    }

    /**
     * sets the info pane unvisible for an empty right view
     */
    @FXML
    public void emptyIt(){
        pageAreaTemplatePane.setVisible(false);
    }

    /**
     * sets the selected template at the mainApp
     */
    private void sendTemplate(){
        mainApp.setCurrentTemplate(template);
    }

    /**
     * takes the template-Selection away and shows info
     * @throws Exception if error occurs
     */
    public void emptyRightView() throws Exception{
        mainApp.changeRightView("EmptyRightView.fxml");
    }

    /**
     * shows the template overview
     */
    public void showTemplateView(){
        mainApp.showRightView();
        emptyIt();
    }
}