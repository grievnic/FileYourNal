package de.entsesselt.de.fileyournal.model;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.File;

public class Page {

    private String templateType = "";
    private String content1;
    private String content2;
    private String content3;
    private String content4;

    private Document currentPage;
    private String templateFilename;

    // FullPage-Contructor
    public Page(String typ, String content1) {
        this(typ, content1,"","","");
    }

    // HalfHalfPage-Contructor
    public Page(String typ, String content1, String content2) {
        this(typ, content1, content2,"","");
    }

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
    }

    public void addPageTemplate(String templateType) {
        templateFilename = templateType + ".xml";
    }

    public void loadXML() {
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
    }
}

