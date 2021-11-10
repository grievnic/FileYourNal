package de.entsesselt.fileyournal.model;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class Organizer {

    private final static String FILENAME = "/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/OrganizerTest.fo";
    private final static File FILE = new File(FILENAME);
    private static Document currentOrganizer;

    private final static String NAMESPACE = "http://www.w3.org/1999/XSL/Format";
    static Namespace fo = Namespace.getNamespace("fo", NAMESPACE);

    /*private Document createDoc(String rootElement) {
        Document doc = new Document();
        *//*Element root = new Element(rootElement, NAMESPACE);*//*
        Element root = new Element("root","fo",NAMESPACE);
        root.addNamespaceDeclaration(fo);
        doc.setRootElement(root);
        return doc;
    }*/

    /*private void createDocTemplate(Document doc) {
        Element layoutMasterSet = new Element("layout-master-set",fo);

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



    public void readFO() {

        try {
            // Create a DocumentBuilder
            SAXBuilder saxBuilder = new SAXBuilder();
            // Create a Document from a file or stream
            File inputFile = new File("input.txt");
            currentOrganizer = saxBuilder.build("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/PageTemplateDinA4.fo");
            System.out.println("Root element :" + currentOrganizer.getRootElement().getName());
            System.out.println("----------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document getCurrentOrganizer() {
        return currentOrganizer;
    }

    // kann vermutlich bald weg
    public static String addPageTemplate(String templateType){
        String templateFilename = templateType + ".fo";
        return templateFilename;
    }

    public static void addPage(Element newPage){
        //TODO Seite (Template und Content) in den Organizer-Baum schreiben
        //mit XPath das <fo:flow>-Element finden
        Element root = currentOrganizer.getRootElement();
        Element pageSequence = root.getChild("page-sequence",fo);
        Element flow = pageSequence.getChild("flow", fo);
        System.out.println(newPage.toString());
        /*flow.addContent(newPage);*/
    }


    public void writeFO(){
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        try (FileOutputStream fos = new FileOutputStream(new File(FILENAME))) {
            XMLOutputter op = new XMLOutputter(format);
            op.output(currentOrganizer, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void createOrganizer(Organizer org) {
            /*Document doc = org.createDoc("root");*/
            /*org.createDocTemplate(doc);*/

    }



    public void foToPdf() throws Exception {
        /*org.writeFO(currentOrganizer);*/
        // Step 1: Construct a FopFactory
// (reuse if you plan to render multiple documents!)
        FopFactory fopFactory = FopFactory.newInstance(new File("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/fop.xconf"));

// Step 2: Set up output stream.
// Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
        OutputStream pdfOut = new BufferedOutputStream(new FileOutputStream(new File("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/meinePDF.pdf")));

        try {
            // Step 3: Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, pdfOut);

            // Step 4: Setup JAXP using identity transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(); // identity transformer

            // Step 5: Setup input and output for XSLT transformation
            // Setup input stream
            Source foIn = new StreamSource(new File("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/Templates/Organizer.fo"));
            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Step 6: Start XSLT transformation and FOP processing
            transformer.transform(foIn, res);

        } catch (FOPException e) {
            e.printStackTrace();}
        finally {
            //Clean-up
            pdfOut.close();
        }
    }
}
