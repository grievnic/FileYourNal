package de.entsesselt.fileyournal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    private int currentPageIndex;
    private final static String FULLPATH = "assets/Content/ContentElements/fullPageContent/";
    private final static String HALFPATH = "assets/Content/ContentElements/halfPageContent/";

    // controls all page views

    /**
     * page view for template: full / fullpage
     * @param bool true = visible
     */
    @FXML
    public void setFullVisible(Boolean bool){
        fullPane.setVisible(bool);
    }

    /**
     * page view for template: half
     * @param bool true = visible
     */
    @FXML
    public void setHalfVisible(Boolean bool){
        halfPane.setVisible(bool);
    }

    /**
     * page view for template: quad
     * @param bool true = visible
     */
    @FXML
    public void setQuadVisible(Boolean bool){
        quadPane.setVisible(bool);
    }

    /**
     * page view for template: quadHalf
     * @param bool true = visible
     */
    @FXML
    public void setQuadHalfVisible(Boolean bool){
        quadHalfPane.setVisible(bool);
    }

    /**
     * page view for template: halfQuad
     * @param bool true = visible
     */
    @FXML
    public void setHalfQuadVisible(Boolean bool){
        halfQuadPane.setVisible(bool);
    }


    // three buttons under PageOverview

    /**
     * decrements the index from current page and loads the page view
     * @throws Exception if error occurs
     */
    @FXML
    public void previousPage() throws Exception {
        mainApp.goToPageIndex(currentPageIndex - 1);
    }

    /**
     * increments the index from current page and loads the page view
     * @throws Exception if error occurs
     */
    @FXML
    public void nextPage()throws Exception{
        mainApp.goToPageIndex(currentPageIndex + 1);
    }

    /**
     * goto page number -
     * Method allows jumping to the requested page number and checks the correctness of the entry and gives feedback
     * @throws Exception if error occurs
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

    /**
     * service to control access logically
     * @param bool true = disable
     */
    @FXML
    public void setPrevButton(Boolean bool){
        prevPageButton.setDisable(bool);
    }

    /**
     * service to control access logically
     * @param bool true = disable
     */
    @FXML
    public void setNextButton(Boolean bool){
        nextPageButton.setDisable(bool);
    }

    /**
     * method to load the content in the page view
     * @param template template type
     * @param content1 for fullpage, half1, quad1, halfquad1, quadhalf1
     * @param content2 for half2, quad2, halfquad2, quadhalf2
     * @param content3 for quad3, halfquad3, quadhalf3
     * @param content4 for quad4
     * @param pageindex index of the current page (to display the page number at the bottom of the page)
     * @throws Exception if error occurs
     */
    @FXML
    public void loadPage(String template, String  content1, String content2, String content3, String content4, int pageindex) throws Exception {
        currentPageIndex = pageindex;
        pageLabel.setText(String.valueOf(pageindex + 1)); // page number
        pageLabel.setTextAlignment(CENTER);

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