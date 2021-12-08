package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

import static javafx.scene.text.TextAlignment.CENTER;


public class PlanerViewController extends AbstractController{

    @FXML
    Button nextPageButton;

    @FXML
    Button prevPageButton;

    @FXML
    Label pageLabel;

    @FXML
    Button goToButton;

    @FXML
    TextField goToPage;

    @FXML
    Label noPage;

    @FXML
    ImageView full1;

    @FXML
    ImageView half1;

    @FXML
    ImageView half2;

    @FXML
    ImageView halfQuad1;

    @FXML
    ImageView halfQuad2;

    @FXML
    ImageView halfQuad3;

    @FXML
    ImageView quadHalf1;

    @FXML
    ImageView quadHalf2;

    @FXML
    ImageView quadHalf3;

    @FXML
    ImageView quad1;

    @FXML
    ImageView quad2;

    @FXML
    ImageView quad3;

    @FXML
    ImageView quad4;

    String template;
    String content1;
    String content2;
    String content3;
    String content4;

    @FXML
    private AnchorPane fullPane;

    @FXML
    private AnchorPane halfPane;

    @FXML
    private AnchorPane quadPane;

    @FXML
    private AnchorPane halfQuadPane;

    @FXML
    private AnchorPane quadHalfPane;

    private int pageIndex;
    private final static String FULLPATH = "assets/Content/ContentElements/fullPageContent/";
    private final static String HALFPATH = "assets/Content/ContentElements/halfPageContent/";


    @FXML
    public void modifyPage() throws Exception {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Änderungsmanager");
        a.setContentText("Was möchtest Du bearbeiten?");
        ButtonType buttonTypeOne = new ButtonType("Die aktuelle Seite ändern.");
        ButtonType buttonTypeTwo = new ButtonType("Vor dieser Seite eine neue Seite einfügen.");
        ButtonType buttonTypeThree = new ButtonType("Nach dieser Seite eine neue Seite einfügen.");
        ButtonType buttonTypeFour = new ButtonType("Am Ende weitere Seiten hinzufügen");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        a.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeCancel);
        Optional<ButtonType> result = a.showAndWait();

        if (result.get() == buttonTypeOne) { // modify current page
            mainApp.showEditView();//
            mainApp.goToFoPage(pageIndex);
        } else if (result.get() == buttonTypeTwo) { // inserts before current page
            mainApp.showEditView();//
            mainApp.newPage();
            mainApp.changePageInsertBeforeButton();
        } else if (result.get() == buttonTypeThree) { // insert after current page
            mainApp.showEditView();//
            mainApp.newPage();
            mainApp.changePageInsertAfterButton();
            mainApp.setPageIndex(pageIndex + 1);
        } else if (result.get() == buttonTypeFour) { // add at the end of document
            mainApp.showEditView();//
            mainApp.newPage();
            mainApp.setAddNewPageButtonVisible();
            mainApp.changePageViewSaveButtons();
            mainApp.setPageIndex(mainApp.getMaxIndex() + 1);
        }
    }

    @FXML
    public void setFullVisible(Boolean bool){
        fullPane.setVisible(bool);
    }

    @FXML
    public void setHalfVisible(Boolean bool){
        halfPane.setVisible(bool);
    }

    @FXML
    public void setQuadVisible(Boolean bool){
        quadPane.setVisible(bool);
    }

    @FXML
    public void setQuadHalfVisible(Boolean bool){
        quadHalfPane.setVisible(bool);
    }

    @FXML
    public void setHalfQuadVisible(Boolean bool){
        halfQuadPane.setVisible(bool);
    }

    protected void showFullTemplates() throws Exception {
        mainApp.changeRightView("FullContentView.fxml");
    }

    protected void showHalfTemplates() throws Exception {
        mainApp.changeRightView("HalfContentView.fxml");
    }

    // three buttons under PageOverview

    @FXML
    public void previousPage() throws Exception {
        mainApp.goToPageIndex(mainApp.getPageIndex() - 1);
    }

    @FXML
    public void nextPage()throws Exception{
        mainApp.goToPageIndex(mainApp.getPageIndex() + 1);
    }

    /**
     * Method allows to jump to the requested pagenumber and checks the correctness of the entry
     * @throws Exception
     */
    @FXML
    private void goToPageNumber() throws Exception{
        String pagenumber = goToPage.getText();// pagenumber from user
        if (pagenumber.isEmpty()){ // textfield is empty
            noPage.setText("Du hast keine Seitenzahl angegeben!");
            noPage.setVisible(true);
        } else if (pagenumber.equals("0")){ // entry is "0"
            noPage.setText("Du hast keine gültige Seitenzahl angegeben!");
            noPage.setVisible(true);
        }
        else { // only int allowed
            if (!pagenumber.matches("^[0-9]*$")) {
               pagenumber = pagenumber.replaceAll("[^0-9]", ""); //deletes the non-numeric entry
                goToPage.setText(pagenumber);
            }
            int index = Integer.parseInt(pagenumber) - 1; // conversion to index
            if (index <= mainApp.getMaxIndex()) {//check if index occurs
                mainApp.goToPageIndex(index);
                goToPage.setText("");
                noPage.setVisible(false);
            } else { // if entry > number of existing pages
                noPage.setText("Diese Seitenzahl existiert nicht!");
                noPage.setVisible(true);
            }
        }
    }

    @FXML
    public void setPrevButton(Boolean bool){
        prevPageButton.setDisable(bool);
    }

    @FXML
    public void setNextButton(Boolean bool){
        nextPageButton.setDisable(bool);
    }

    @FXML
    public void loadPage(String template, String  content1, String content2, String content3, String content4, int pageindex) throws Exception {
        this.content1 = (content1);
        this.content2 = (content2);
        this.content3 = (content3);
        this.content4 = (content4);
        pageLabel.setText(String.valueOf(pageindex + 1));
        pageLabel.setTextAlignment(CENTER);
        this.pageIndex = pageindex;

        if (template.equals("fullpage")){
            mainApp.showPageTemplate("planerViewController", "fullpage");
            String imagePath = FULLPATH + content1;
            Image image1 = new Image(imagePath);
            full1.setImage(image1);
        } else if (mainApp.getCurrentTemplate().equals("half")){
            mainApp.showPageTemplate("planerViewController","half");
            String imagePath1 = HALFPATH + content1;
            Image image1 = new Image(imagePath1);
            half1.setImage(image1);

            String imagePath2 = HALFPATH + content2;
            System.out.println(imagePath2);
            Image image2 = new Image(imagePath2);
            half2.setImage(image2);

        } else if (mainApp.getCurrentTemplate().equals("quad")){
            mainApp.showPageTemplate("planerViewController", "quad");
            String imagePath1 = FULLPATH + content1;
            Image image1 = new Image(imagePath1);
            quad1.setImage(image1);

            String imagePath2 = FULLPATH + content2;
            Image image2 = new Image(imagePath2);
            quad2.setImage(image2);

            String imagePath3 = FULLPATH + content3;
            Image image3 = new Image(imagePath3);
            quad3.setImage(image3);

            String imagePath4 = FULLPATH + content4;
            Image image4 = new Image(imagePath4);
            quad4.setImage(image4);

        } else if (mainApp.getCurrentTemplate().equals("halfquad")){
            mainApp.showPageTemplate("planerViewController", "halfQuad");
            String imagePath1 = HALFPATH + content1;
            Image image1 = new Image(imagePath1);
            halfQuad1.setImage(image1);

            String imagePath2 = FULLPATH + content2;
            Image image2 = new Image(imagePath2);
            halfQuad2.setImage(image2);

            String imagePath3 = FULLPATH + content3;
            Image image3 = new Image(imagePath3);
            halfQuad3.setImage(image3);

        } else {
            mainApp.showPageTemplate("planerViewController", "quadHalf");
            String imagePath1 = FULLPATH + content1;
            Image image1 = new Image(imagePath1);
            quadHalf1.setImage(image1);

            String imagePath2 = FULLPATH + content2;
            Image image2 = new Image(imagePath2);
            quadHalf2.setImage(image2);

            String imagePath3 = HALFPATH + content3;
            Image image3 = new Image(imagePath3);
            quadHalf3.setImage(image3);
        }
    }
}



