package de.entsesselt.fileyournal.model;

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
    private final static String FULLWIDTH = "190";
    private final static String FULLHEIGHT = "275mm";
    private final static String HALFWIDTH = "95mm";
    private final static String HALFHEIGHT = "137.5mm";
    private final static String NOSPACE = "0mm";
    private final static String BOTTOM = "10mm";

    private final static String NAMESPACE = "http://www.w3.org/1999/XSL/Format";
    Namespace fo = Namespace.getNamespace("fo", NAMESPACE);

    private static int currentPageNumber = 0;

    /**
     * constructor
     * @param typ template
     * @param content1 for fullpage, half1, quad1, halfquad1, quadhalf1
     * @param content2 for half2, quad2, halfquad2, quadhalf2
     * @param content3 for quad3, halfquad3, quadhalf3
     * @param content4 for quad4
     */
    public Page(String typ, String content1, String content2, String content3, String content4) {
        this.templateType = typ;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.content4 = content4;
    }

    /**
     * This method creates the <fo:block>- Element per JDOM based on the data from template & content
     * @return newPage - <fo:block>- Element for the whole page
     */
    public Element pageCreator(){
        currentPageNumber++; // pagecounter for an unique id made up of templatename and pagenumber
        Element newPage;
    // HALF
        String pageID;
        switch (templateType) {
            case "half" -> { // if template type is half
                // creating a <fo:block> incl. page break
                newPage = new Element("block", fo);
                newPage.setAttribute("page-break-before", "always"); //page brake

                pageID = "half" + currentPageNumber;
                newPage.setAttribute("id", pageID);

                // <fo:block-container> for positioning at the top half
                Element topContainer = new Element("block-container", fo);
                topContainer.setAttribute("absolute-position", "absolute");
                topContainer.setAttribute("top", NOSPACE);
                topContainer.setAttribute("left", NOSPACE);
                topContainer.setAttribute("height", HALFHEIGHT);
                topContainer.setAttribute("width", FULLWIDTH);
                newPage.addContent(topContainer);

                // <fo:block> for the top content
                Element firstContent = new Element("block", fo);
                topContainer.addContent(firstContent);

                Element contentImage1 = new Element("external-graphic", fo);
                contentImage1.setAttribute("alignment-adjust", "central");
                contentImage1.setAttribute("src", HALFPATH + content1);
                contentImage1.setAttribute("content-height", HALFHEIGHT);
                firstContent.addContent(contentImage1);

                // <fo:block-container> for positioning at the button half
                Element bottomContainer = new Element("block-container", fo);
                bottomContainer.setAttribute("absolute-position", "absolute");
                bottomContainer.setAttribute("bottom", BOTTOM);
                bottomContainer.setAttribute("left", NOSPACE);
                bottomContainer.setAttribute("height", HALFHEIGHT);
                bottomContainer.setAttribute("width", FULLWIDTH);
                newPage.addContent(bottomContainer);

                // <fo:block> for bottom content
                Element secondContent = new Element("block", fo);
                bottomContainer.addContent(secondContent);

                Element contentImage2 = new Element("external-graphic", fo);
                contentImage2.setAttribute("alignment-adjust", "central");
                contentImage2.setAttribute("src", HALFPATH + content2);
                contentImage2.setAttribute("content-height", HALFHEIGHT);
                secondContent.addContent(contentImage2);
                //QUAD
                break;
            }
            case "quad" -> { // if template ist only quarters
                // <fo:block> incl. page break
                newPage = new Element("block", fo);
                newPage.setAttribute("page-break-before", "always");
                pageID = "quad" + currentPageNumber;
                newPage.setAttribute("id", pageID);

                // <fo:block-container> for positioning at the top left quarter
                Element firstContainer = new Element("block-container", fo);
                firstContainer.setAttribute("absolute-position", "absolute");
                firstContainer.setAttribute("top", NOSPACE);
                firstContainer.setAttribute("left", NOSPACE);
                firstContainer.setAttribute("height", HALFHEIGHT);
                firstContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(firstContainer);
                // <fo:block> for the content
                Element firstContent = new Element("block", fo);
                firstContainer.addContent(firstContent);
                // content for the top left quarter
                Element contentImage1 = new Element("external-graphic", fo);
                contentImage1.setAttribute("alignment-adjust", "central");
                contentImage1.setAttribute("src", FULLPATH + content1);
                contentImage1.setAttribute("content-height", HALFHEIGHT);
                firstContent.addContent(contentImage1);

                // <fo:block-container> for positioning at the top right quarter
                Element secondContainer = new Element("block-container", fo);
                secondContainer.setAttribute("absolute-position", "absolute");
                secondContainer.setAttribute("top", NOSPACE);
                secondContainer.setAttribute("right", NOSPACE);
                secondContainer.setAttribute("height", HALFHEIGHT);
                secondContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(secondContainer);
                // <fo:block> for the content
                Element secondContent = new Element("block", fo);
                secondContainer.addContent(secondContent);
                //content
                Element contentImage2 = new Element("external-graphic", fo);
                contentImage2.setAttribute("alignment-adjust", "central");
                contentImage2.setAttribute("src", FULLPATH + content2);
                contentImage2.setAttribute("content-height", HALFHEIGHT);
                secondContent.addContent(contentImage2);

                // <fo:block-container> for positioning at the bottom left quarter
                Element thirdContainer = new Element("block-container", fo);
                thirdContainer.setAttribute("absolute-position", "absolute");
                thirdContainer.setAttribute("bottom", BOTTOM);
                thirdContainer.setAttribute("left", NOSPACE);
                thirdContainer.setAttribute("height", HALFHEIGHT);
                thirdContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(thirdContainer);
                // <fo:block>
                Element thirdContent = new Element("block", fo);
                thirdContainer.addContent(thirdContent);
                //content
                Element contentImage3 = new Element("external-graphic", fo);
                contentImage3.setAttribute("alignment-adjust", "central");
                contentImage3.setAttribute("src", FULLPATH + content3);
                contentImage3.setAttribute("content-height", HALFHEIGHT);
                thirdContent.addContent(contentImage3);

                // <fo:block-container> for positioning at the bottom right quarter
                Element forthContainer = new Element("block-container", fo);
                forthContainer.setAttribute("absolute-position", "absolute");
                forthContainer.setAttribute("bottom", BOTTOM);
                forthContainer.setAttribute("right", NOSPACE);
                forthContainer.setAttribute("height", HALFHEIGHT);
                forthContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(forthContainer);
                // <fo:block>
                Element forthContent = new Element("block", fo);
                forthContainer.addContent(forthContent);
                // content
                Element contentImage4 = new Element("external-graphic", fo);
                contentImage4.setAttribute("alignment-adjust", "central");
                contentImage4.setAttribute("src", FULLPATH + content4);
                contentImage4.setAttribute("content-height", HALFHEIGHT);
                forthContent.addContent(contentImage4);

                //HALFQUAD
                break;
            }
            case "halfQuad" -> { // half top - quarters at bottom
                // <fo:block> incl. page break
                newPage = new Element("block", fo);
                newPage.setAttribute("page-break-before", "always");
                pageID = "halfquad" + currentPageNumber;
                newPage.setAttribute("id", pageID);

                // <fo:block-container> for positioning at the top half
                Element topContainer = new Element("block-container", fo);
                topContainer.setAttribute("absolute-position", "absolute");
                topContainer.setAttribute("top", NOSPACE);
                topContainer.setAttribute("left", NOSPACE);
                topContainer.setAttribute("height", HALFHEIGHT);
                topContainer.setAttribute("width", FULLWIDTH);
                newPage.addContent(topContainer);

                // <fo:block>
                Element firstContent = new Element("block", fo);
                topContainer.addContent(firstContent);
                // Content
                Element contentImage1 = new Element("external-graphic", fo);
                contentImage1.setAttribute("alignment-adjust", "central");
                contentImage1.setAttribute("src", HALFPATH + content1);
                contentImage1.setAttribute("content-height", HALFHEIGHT);
                firstContent.addContent(contentImage1);

                // <fo:block-container> for positioning at the bottom left quarter
                Element leftContainer = new Element("block-container", fo);
                leftContainer.setAttribute("absolute-position", "absolute");
                leftContainer.setAttribute("bottom", BOTTOM);
                leftContainer.setAttribute("left", NOSPACE);
                leftContainer.setAttribute("height", HALFHEIGHT);
                leftContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(leftContainer);
                // <fo:block>
                Element secondContent = new Element("block", fo);
                leftContainer.addContent(secondContent);
                // Content
                Element contentImage2 = new Element("external-graphic", fo);
                contentImage2.setAttribute("alignment-adjust", "central");
                contentImage2.setAttribute("src", FULLPATH + content2);
                contentImage2.setAttribute("content-height", HALFHEIGHT);
                secondContent.addContent(contentImage2);

                // <fo:block-container> for positioning at the bottom right quarter
                Element rightContainer = new Element("block-container", fo);
                rightContainer.setAttribute("absolute-position", "absolute");
                rightContainer.setAttribute("bottom", BOTTOM);
                rightContainer.setAttribute("right", NOSPACE);
                rightContainer.setAttribute("height", HALFHEIGHT);
                rightContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(rightContainer);
                // <fo:block>
                Element thirdContent = new Element("block", fo);
                rightContainer.addContent(thirdContent);
                // Content
                Element contentImage3 = new Element("external-graphic", fo);
                contentImage3.setAttribute("alignment-adjust", "central");
                contentImage3.setAttribute("src", FULLPATH + content3);
                contentImage3.setAttribute("content-height", HALFHEIGHT);
                thirdContent.addContent(contentImage3);

                //QUADHALF
                break;
            }
            case "quadHalf" -> { // top quarters - bottom half
                // <fo:block> incl. page break
                newPage = new Element("block", fo);
                newPage.setAttribute("page-break-before", "always");
                pageID = "quadhalf" + currentPageNumber;
                newPage.setAttribute("id", pageID);

                // <fo:block-container> for positioning at the top left quarter
                Element firstContainer = new Element("block-container", fo);
                firstContainer.setAttribute("absolute-position", "absolute");
                firstContainer.setAttribute("top", NOSPACE);
                firstContainer.setAttribute("left", NOSPACE);
                firstContainer.setAttribute("height", HALFHEIGHT);
                firstContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(firstContainer);
                // <fo:block>
                Element firstContent = new Element("block", fo);
                firstContainer.addContent(firstContent);
                // content
                Element contentImage1 = new Element("external-graphic", fo);
                contentImage1.setAttribute("alignment-adjust", "central");
                contentImage1.setAttribute("src", FULLPATH + content1);
                contentImage1.setAttribute("content-height", HALFHEIGHT);
                firstContent.addContent(contentImage1);

                // <fo:block-container> for positioning at the top right quarter
                Element secondContainer = new Element("block-container", fo);
                secondContainer.setAttribute("absolute-position", "absolute");
                secondContainer.setAttribute("top", NOSPACE);
                secondContainer.setAttribute("right", NOSPACE);
                secondContainer.setAttribute("height", HALFHEIGHT);
                secondContainer.setAttribute("width", HALFWIDTH);
                newPage.addContent(secondContainer);
                // <fo:block>
                Element secondContent = new Element("block", fo);
                secondContainer.addContent(secondContent);
                // Content
                Element contentImage2 = new Element("external-graphic", fo);
                contentImage2.setAttribute("alignment-adjust", "central");
                contentImage2.setAttribute("src", FULLPATH + content2);
                contentImage2.setAttribute("content-height", HALFHEIGHT);
                secondContent.addContent(contentImage2);

                // <fo:block-container> for positioning at the bottom half
                Element bottomContainer = new Element("block-container", fo);
                bottomContainer.setAttribute("absolute-position", "absolute");
                bottomContainer.setAttribute("bottom", BOTTOM);
                bottomContainer.setAttribute("left", NOSPACE);
                bottomContainer.setAttribute("height", HALFHEIGHT);
                bottomContainer.setAttribute("width", FULLWIDTH);
                newPage.addContent(bottomContainer);
                // <fo:block>
                Element thirdContent = new Element("block", fo);
                bottomContainer.addContent(thirdContent);
                // Content
                Element contentImage3 = new Element("external-graphic", fo);
                contentImage3.setAttribute("alignment-adjust", "central");
                contentImage3.setAttribute("src", HALFPATH + content3);
                contentImage3.setAttribute("content-height", HALFHEIGHT);
                thirdContent.addContent(contentImage3);

                //FULL
                break;
            }
            default -> { // templateType is "full" - fullpage
                // <fo:block> incl. page break
                newPage = new Element("block", fo);
                newPage.setAttribute("page-break-before", "always");
                pageID = "fullpage" + currentPageNumber;
                newPage.setAttribute("id", pageID);

                // <fo:block-container> for positioning at the page
                Element container = new Element("block-container", fo);
                container.setAttribute("absolute-position", "absolute");
                container.setAttribute("top", NOSPACE);
                container.setAttribute("left", NOSPACE);
                container.setAttribute("height", FULLHEIGHT);
                newPage.addContent(container);
                // <fo:block>
                Element firstContent = new Element("block", fo);
                container.addContent(firstContent);
                // content
                Element contentImage1 = new Element("external-graphic", fo);
                contentImage1.setAttribute("src", FULLPATH + content1);
                contentImage1.setAttribute("content-height", FULLHEIGHT);
                firstContent.addContent(contentImage1);
                break;
            }
        }
        return newPage;
    }

    // getter & setter

    public static void setCurrentPageNumber(int currentPageNumber) {
        Page.currentPageNumber = currentPageNumber;
    }

    // for testing reason
    public static int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public String getContent1() {
        return content1;
    }

    public String getContent2() {
        return content2;
    }

    public String getContent3() {
        return content3;
    }

    public String getContent4() {
        return content4;
    }
}


