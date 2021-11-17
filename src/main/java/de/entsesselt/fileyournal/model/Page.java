package de.entsesselt.fileyournal.model;


import de.entsesselt.fileyournal.HelloApplication;
import org.jdom.Element;
import org.jdom.Namespace;

public class Page {

    private String templateType;
    private String content1;
    private String content2;
    private String content3;
    private String content4;

    private final static String FULLPATH = "/Users/nicolegrieve/IdeaProjects/FileYournal/New/src/main/resources/assets/Content/ContentElements/fullPageContent/";
    private final static String HALFPATH = "/Users/nicolegrieve/IdeaProjects/FileYournal/New/src/main/resources/assets/Content/ContentElements/halfPageContent/";
    private final static String FULLWIDTH = "190mm";
    private final static String FULLHIGHT = "275mm";
    private final static String HALFWIDTH = "95mm";
    private final static String HALFHIGHT = "137.5mm";
    private final static String NOSPACE = "0mm";

    private String templateFilename;
    private String pageID;
    private String anotherPageId;
    private final static String NAMESPACE = "http://www.w3.org/1999/XSL/Format";
    Namespace fo = Namespace.getNamespace("fo", NAMESPACE);

    private static int currentPageNumber = 0;

    /*Organizer org = new Organizer();*/

    // Reference to the main application.
     private HelloApplication mainApp;

    // FullPage-Contructor
    public Page(String typ, String content1) {
        this(typ, content1,"","","");
    }

    // HalfHalfPage-Contructor
    public Page(String typ, String content1, String content2) {
        this(typ, content1, content2,"","");
    }

    // QuadHalf & HalfQuad-Constructor
    public Page(String typ, String content1, String content2, String content3){
        this(typ, content1, content2, content3,"");
    }

    // QuadQuadPage-Contructor
    public Page(String typ, String content1, String content2, String content3, String content4) {
        this.templateType = typ;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.content4 = content4;
        currentPageNumber = ++currentPageNumber;
        /*mainApp.setPageID(String.valueOf(currentPageNumber))*/;
    }

    // sollte mit einem  zu ladenden XML-Template gearbeitet werden
    /*public void addPageTemplate(String templateType) {
        templateFilename = templateType + ".xml";
    }*/


    public Element pageCreator(){
        System.out.println("Page-Nummer: " + currentPageNumber);
        Element newPage;
    // HALF
        if (templateType.equals("half")){ // wenn der TemplateTyp "halbe/halbe Seite" ist
            // <fo:block> inkl. Seitenumbruch für eine neue Seite
            newPage = new Element("block", fo);
            newPage.setAttribute("page-break-before", "always"); //Seitenumbruch
            pageID = "half" + currentPageNumber;
            newPage.setAttribute("id",pageID);

            // <fo:block-container> für die Positionierung der oberen Hälfte auf der Seite
            Element topContainer = new Element("block-container", fo);
            topContainer.setAttribute("absolute-position", "absolute");
            topContainer.setAttribute("top", NOSPACE);
            topContainer.setAttribute("left", NOSPACE);
            topContainer.setAttribute("height", HALFHIGHT);
            topContainer.setAttribute("width", FULLWIDTH);
            newPage.addContent(topContainer);

            // <fo:block> für den Content oben
            Element firstContent = new Element("block", fo);
            topContainer.addContent(firstContent);

            Element contentImage1 = new Element("external-graphic", fo);
            contentImage1.setAttribute("alignment-adjust", "central");
            contentImage1.setAttribute("src",HALFPATH + content1 + ".png");
            contentImage1.setAttribute("content-width", HALFWIDTH);
            firstContent.addContent(contentImage1);

            // <fo:block-container> für die Positionierung der unteren Hälfte auf der Seite
            Element bottomContainer = new Element("block-container", fo);
            bottomContainer.setAttribute("absolute-position", "absolute");
            bottomContainer.setAttribute("bottom", NOSPACE);
            bottomContainer.setAttribute("left", NOSPACE);
            bottomContainer.setAttribute("height", HALFHIGHT);
            bottomContainer.setAttribute("width", FULLWIDTH);
            newPage.addContent(bottomContainer);

            // <fo:block> für den Content unten
            Element secondContent = new Element("block", fo);
            bottomContainer.addContent(secondContent);

            Element contentImage2 = new Element("external-graphic", fo);
            contentImage2.setAttribute("alignment-adjust", "central");
            contentImage2.setAttribute("src",HALFPATH + content2 + ".png");
            contentImage2.setAttribute("content-width", FULLWIDTH);
            secondContent.addContent(contentImage2);
    //QUAD
        }else if (templateType.equals("quad")){ // wenn der TemplateTyp 4/4 Seite ist
            // <fo:block> inkl. Seitenumbruch für eine neue Seite
            newPage = new Element("block", fo);
            newPage.setAttribute("page-break-before", "always");
            pageID = "quad" + currentPageNumber;
            newPage.setAttribute("id",pageID);
            //currentPage.getRootElement().addContent(neueSeite);

            // <fo:block-container> für die Positionierung des linken Viertels der oberen Hälfte auf der Seite
            Element firstContainer = new Element("block-container", fo);
            firstContainer.setAttribute("absolute-position", "absolute");
            firstContainer.setAttribute("top", NOSPACE);
            firstContainer.setAttribute("left", NOSPACE);
            firstContainer.setAttribute("height", HALFHIGHT);
            firstContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(firstContainer);
            // <fo:block> für den Content
            Element firstContent = new Element("block", fo);
            firstContainer.addContent(firstContent);
            // Inhalt für das Viertel oben links
            Element contentImage1 = new Element("external-graphic", fo);
            contentImage1.setAttribute("alignment-adjust", "central");
            contentImage1.setAttribute("src",FULLPATH + content1 + ".png");
            contentImage1.setAttribute("content-width", HALFWIDTH);
            firstContent.addContent(contentImage1);

            // <fo:block-container> für die Positionierung des rechten Viertels der oberen Hälfte auf der Seite
            Element secondContainer = new Element("block-container", fo);
            secondContainer.setAttribute("absolute-position", "absolute");
            secondContainer.setAttribute("top", NOSPACE);
            secondContainer.setAttribute("right", NOSPACE);
            secondContainer.setAttribute("height", HALFHIGHT);
            secondContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(secondContainer);
            // <fo:block> für den Content
            Element secondContent = new Element("block", fo);
            secondContainer.addContent(secondContent);
            //
            Element contentImage2 = new Element("external-graphic", fo);
            contentImage2.setAttribute("alignment-adjust", "central");
            contentImage2.setAttribute("src",FULLPATH + content2 + ".png");
            contentImage2.setAttribute("content-width", HALFWIDTH);
            secondContent.addContent(contentImage2);

            // <fo:block-container> für die Positionierung des linken Viertels der unteren Hälfte auf der Seite
            Element thirdContainer = new Element("block-container", fo);
            thirdContainer.setAttribute("absolute-position", "absolute");
            thirdContainer.setAttribute("bottom", NOSPACE);
            thirdContainer.setAttribute("left", NOSPACE);
            thirdContainer.setAttribute("height", HALFHIGHT);
            thirdContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(thirdContainer);
            // <fo:block> für den Content

            Element thirdContent = new Element("block", fo);
            thirdContainer.addContent(thirdContent);
            //
            Element contentImage3 = new Element("external-graphic", fo);
            contentImage3.setAttribute("alignment-adjust", "central");
            contentImage3.setAttribute("src",FULLPATH + content3 + ".png");
            contentImage3.setAttribute("content-width", HALFWIDTH);
            thirdContent.addContent(contentImage3);

            // <fo:block-container> für die Positionierung des rechten Viertels der unteren Hälfte auf der Seite
            Element forthContainer = new Element("block-container", fo);
            forthContainer.setAttribute("absolute-position", "absolute");
            forthContainer.setAttribute("bottom", NOSPACE);
            forthContainer.setAttribute("right", NOSPACE);
            forthContainer.setAttribute("height", HALFHIGHT);
            forthContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(forthContainer);
            // <fo:block> für den Content
            Element forthContent = new Element("block", fo);
            forthContainer.addContent(forthContent);
            // Inhalt für das rechte untere Viertel
            Element contentImage4 = new Element("external-graphic", fo);
            contentImage4.setAttribute("alignment-adjust", "central");
            contentImage4.setAttribute("src",FULLPATH + content4 + ".png");
            contentImage4.setAttribute("content-width", HALFWIDTH);
            forthContent.addContent(contentImage4);

    //HALFQUAD
        }else if (templateType.equals("halfQuad")){ // wenn oben 1/2 und unten 2/4
            // <fo:block> inkl. Seitenumbruch für eine neue Seite
            newPage = new Element("block", fo);
            newPage.setAttribute("page-break-before", "always");
            pageID = "halfquad" + currentPageNumber;
            newPage.setAttribute("id",pageID);
            //currentPage.getRootElement().addContent(neueSeite);

            // <fo:block-container> für die Positionierung der oberen Hälfte auf der Seite
            Element topContainer = new Element("block-container", fo);
            topContainer.setAttribute("absolute-position", "absolute");
            topContainer.setAttribute("top", NOSPACE);
            topContainer.setAttribute("left", NOSPACE);
            topContainer.setAttribute("height", HALFHIGHT);
            topContainer.setAttribute("width", FULLWIDTH);
            newPage.addContent(topContainer);

            // <fo:block> für den Content
            Element firstContent = new Element("block", fo);
            topContainer.addContent(firstContent);
            // Content für die obere Hälfte
            Element contentImage1 = new Element("external-graphic", fo);
            contentImage1.setAttribute("alignment-adjust", "central");
            contentImage1.setAttribute("src",HALFPATH + content1 + ".png");
            contentImage1.setAttribute("content-width", FULLWIDTH);
            firstContent.addContent(contentImage1);

            // <fo:block-container> für die Positionierung des linken Viertels der unteren Hälfte auf der Seite
            Element leftContainer = new Element("block-container", fo);
            leftContainer.setAttribute("absolute-position", "absolute");
            leftContainer.setAttribute("bottom", NOSPACE);
            leftContainer.setAttribute("left", NOSPACE);
            leftContainer.setAttribute("height", HALFHIGHT);
            leftContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(leftContainer);
            // <fo:block> für den Content
            Element secondContent = new Element("block", fo);
            leftContainer.addContent(secondContent);
            // Content für das untere linke Viertel
            Element contentImage2 = new Element("external-graphic", fo);
            contentImage2.setAttribute("alignment-adjust", "central");
            contentImage2.setAttribute("src",FULLPATH + content2 + ".png");
            contentImage2.setAttribute("content-width", HALFWIDTH);
            secondContent.addContent(contentImage2);

            // <fo:block-container> für die Positionierung des rechten Viertels der unteren Hälfte auf der Seite
            Element rightContainer = new Element("block-container", fo);
            rightContainer.setAttribute("absolute-position", "absolute");
            rightContainer.setAttribute("bottom", NOSPACE);
            rightContainer.setAttribute("right", NOSPACE);
            rightContainer.setAttribute("height", HALFHIGHT);
            rightContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(rightContainer);
            // <fo:block> für den Content
            Element thirdContent = new Element("block", fo);
            rightContainer.addContent(thirdContent);
            // Content für das untere rechte Viertel
            Element contentImage3 = new Element("external-graphic", fo);
            contentImage3.setAttribute("alignment-adjust", "central");
            contentImage3.setAttribute("src",FULLPATH + content3 + ".png");
            contentImage3.setAttribute("content-width", HALFWIDTH);
            thirdContent.addContent(contentImage3);

    //QUADHALF
        }else if (templateType.equals("quadHalf")){ // wenn oben 2/4 und unten 1/2
            // <fo:block> inkl. Seitenumbruch für eine neue Seite
            newPage = new Element("block", fo);
            newPage.setAttribute("page-break-before", "always");
            pageID = "quadhalf" + currentPageNumber;
            newPage.setAttribute("id",pageID);

            // <fo:block-container> für die Positionierung des linken Viertels der oberen Hälfte auf der Seite
            Element firstContainer = new Element("block-container", fo);
            firstContainer.setAttribute("absolute-position", "absolute");
            firstContainer.setAttribute("top", NOSPACE);
            firstContainer.setAttribute("left", NOSPACE);
            firstContainer.setAttribute("height", HALFHIGHT);
            firstContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(firstContainer);
            // <fo:block> für den Content
            Element firstContent = new Element("block", fo);
            firstContainer.addContent(firstContent);

            Element contentImage1 = new Element("external-graphic", fo);
            contentImage1.setAttribute("alignment-adjust", "central");
            contentImage1.setAttribute("src",FULLPATH + content1 + ".png");
            contentImage1.setAttribute("content-width", HALFWIDTH);
            firstContent.addContent(contentImage1);

            // <fo:block-container> für die Positionierung des rechten Viertels der oberen Hälfte auf der Seite
            Element secondContainer = new Element("block-container", fo);
            secondContainer.setAttribute("absolute-position", "absolute");
            secondContainer.setAttribute("top", NOSPACE);
            secondContainer.setAttribute("right", NOSPACE);
            secondContainer.setAttribute("height", HALFHIGHT);
            secondContainer.setAttribute("width", HALFWIDTH);
            newPage.addContent(secondContainer);
            // <fo:block> für den Content
            Element secondContent = new Element("block", fo);
            secondContainer.addContent(secondContent);
            // Content für das rechte obere Viertel
            Element contentImage2 = new Element("external-graphic", fo);
            contentImage2.setAttribute("alignment-adjust", "central");
            contentImage2.setAttribute("src",FULLPATH + content2 + ".png");
            contentImage2.setAttribute("content-width", HALFWIDTH);
            secondContent.addContent(contentImage2);

            // <fo:block-container> für die Positionierung der unteren Hälfte auf der Seite
            Element bottomContainer = new Element("block-container", fo);
            bottomContainer.setAttribute("absolute-position", "absolute");
            bottomContainer.setAttribute("bottom", NOSPACE);
            bottomContainer.setAttribute("left", NOSPACE);
            bottomContainer.setAttribute("height", HALFHIGHT);
            bottomContainer.setAttribute("width", FULLWIDTH);
            newPage.addContent(bottomContainer);
            // <fo:block> für den Content
            Element thirdContent = new Element("block", fo);
            bottomContainer.addContent(thirdContent);
            // Content für die untere Hälfte
            Element contentImage3 = new Element("external-graphic", fo);
            contentImage3.setAttribute("alignment-adjust", "central");
            contentImage3.setAttribute("src",HALFPATH + content3 + ".png");
            contentImage3.setAttribute("content-width", FULLWIDTH);
            thirdContent.addContent(contentImage3);

    //FULL
        }else { // templateType is "full", also ganze Seite
            // <fo:block> inkl. Seitenumbruch für eine neue Seite
            newPage = new Element("block", fo);
            newPage.setAttribute("page-break-before", "always");
            pageID = "fullpage" + currentPageNumber;
            newPage.setAttribute("id",pageID);
            //currentPage.getRootElement().addContent(neueSeite);

            // <fo:block-container> für die Positionierung innerhalb der Seite
            Element container = new Element("block-container", fo);
            container.setAttribute("absolute-position", "absolute");
            container.setAttribute("top", NOSPACE);
            container.setAttribute("left", NOSPACE);
            container.setAttribute("height", FULLHIGHT);
            container.setAttribute("width", FULLWIDTH);
            newPage.addContent(container);
            // <fo:block> für den Content
            Element firstContent = new Element("block", fo);
            container.addContent(firstContent);
            // erster und einziger Content
            Element contentImage1 = new Element("external-graphic", fo);
            contentImage1.setAttribute("alignment-adjust", "central");
            contentImage1.setAttribute("src",FULLPATH + content1 + ".png");
            /*contentImage1.setAttribute("src","/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/Blocks/5ColumnSystem.png");
*/
            contentImage1.setAttribute("content-width", FULLWIDTH);
            firstContent.addContent(contentImage1);
            System.out.println("pageCreator BEGIN: " + content1 + content2 + content3 + content4);
        } return newPage;
    }





     /*private void createPageTemplate(Document quad) {
        Element root = doc.getRootElement();


        doc.getRootElement().addContent(layoutMasterSet);

        *//*Element simplePageMaster  = new Element("simple-page-master");*//*
        Element simplePageMaster = new Element("simple-page-master",fo);
        simplePageMaster.setAttribute("master-name", "Organizer");
        simplePageMaster.setAttribute("page-height", "297mm");
        simplePageMaster.setAttribute("page-width", "210mm");
        simplePageMaster.setAttribute("margin-left", "12mm");
        simplePageMaster.setAttribute("margin-right", "12mm");
        simplePageMaster.setAttribute("margin-top", "12mm");
        simplePageMaster.setAttribute("margin-bottom", "12mm");

        layoutMasterSet.addContent(simplePageMaster);

        Element regionBody = new Element("region-body",fo);

        regionBody.setAttribute("margin-top", "10mm");
        regionBody.setAttribute("margin-bottom", "10mm");

        simplePageMaster.addContent(regionBody);

        Element pageSequence = new Element("page-sequence",fo);
        pageSequence.setAttribute("master-reference","Organizer");

        doc.getRootElement().addContent(pageSequence); // Einhängen des <fo:page-sequence> -Elements in den DOM-Baum als Child von <fo:root>

        Element flow = new Element("flow",fo);
        flow.setAttribute("flow-name","xsl-region-body");

        pageSequence.addContent(flow); // Einhängen des <fo:flow>-Elements in den DOM-Baum als Child von <fo:page-sequence>

        // Anlegen des Blocks für den Inhalt einer ganzen Seite
        Element block = new Element("block",fo);
        *//*block.setText("Dieses ist ein Text");*//*

        flow.addContent(block); // Einhängen in den DOM-Baum als Child von <fo:flow>

        //Block-Container 1/2 Seite oben
        Element blockContainerOben = new Element("block-container",fo);
        blockContainerOben.setAttribute("height","50%");
        blockContainerOben.setAttribute("width","100%");
        blockContainerOben.setAttribute("background-color", "yellow");
        blockContainerOben.setAttribute("absolute-position","fixed");
        blockContainerOben.setAttribute("left","10mm");
        blockContainerOben.setAttribute("top","10mm");

        Element topHalf = new Element("block",fo);
        topHalf.setText("ssssssset");

        blockContainerOben.addContent(topHalf);

        block.addContent(blockContainerOben); // Einhängen des Containers in den DOM-Baum als Child von <fo:block>

        //Block-Container 1/2 Seite unten
        Element blockContainerUnten = new Element("block-container",fo);
        blockContainerUnten.setAttribute("height","50%");
        blockContainerUnten.setAttribute("width","100%");
        blockContainerUnten.setAttribute("background-color", "grey");
        blockContainerUnten.setAttribute("absolute-position","fixed");
        blockContainerUnten.setAttribute("left","10mm");
        blockContainerUnten.setAttribute("top","100mm");

        block.addContent(blockContainerUnten);

        //Block-Container 1/2 Seite unten
        Element blockContainerLinks = new Element("block-container",fo);
        blockContainerLinks.setAttribute("height","50%");
        blockContainerLinks.setAttribute("width","50%");
        blockContainerLinks.setAttribute("background-color", "red");
        blockContainerLinks.setAttribute("absolute-position","fixed");
        blockContainerLinks.setAttribute("left","10mm");
        blockContainerLinks.setAttribute("top","120mm");

        blockContainerUnten.addContent(blockContainerLinks);

        Element left = new Element("block",fo);
        *//*left.setAttribute("absolute-position","left");*//*
     *//*left.setAttribute("break-after", "column");*//*
        left.setText("testtttt");

        blockContainerLinks.addContent(left);

        //Block-Container 1/2 Seite unten
        Element blockContainerRechts = new Element("block-container",fo);
        *//*blockContainerRechts.setAttribute("height","50%");*//*
        blockContainerRechts.setAttribute("width","50%");
        blockContainerRechts.setAttribute("background-color", "green");
        blockContainerRechts.setAttribute("absolute-position","fixed");
        blockContainerRechts.setAttribute("left","100mm");
        blockContainerRechts.setAttribute("top","120mm");

        blockContainerUnten.addContent(blockContainerRechts);

        Element right = new Element("block",fo);
        right.setText("ssssssset");

        blockContainerRechts.addContent(right);
    }*/

   /* public void loadXML() {
        try {
            // Create a DocumentBuilder
            SAXBuilder saxBuilder = new SAXBuilder();
            // Create a Document from a file or stream
            File xmlFile = new File("pageTemplate.txt");
            currentPage = saxBuilder.build("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/PageTemplateDinA4.fo");
            System.out.println("Root element :" + currentPage.getRootElement().getName());
            System.out.println("----------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getContent4() {
        return content4;
    }

    public void setContent4(String content4) {
        this.content4 = content4;
    }

    public static int getCurrentPageNumber() {
        return currentPageNumber;
    }

    /*public static int getCurrentPageNumber() {
        return currentPageNumber;
    }*/

    /*public Document getCurrentPage() {
        return currentPage;
    }



    public void setCurrentPage(Document currentPage) {
        this.currentPage = currentPage;
    }*/

    public String getTemplateFilename() {
        return templateFilename;
    }

    public void setTemplateFilename(String templateFilename) {
        this.templateFilename = templateFilename;
    }
}

