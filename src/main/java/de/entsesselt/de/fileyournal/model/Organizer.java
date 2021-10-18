package de.entsesselt.de.fileyournal.model;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
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


    private final static String FILENAME = "/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/Organizer.fo";
    private final static File FILE = new File(FILENAME);

    private final static String NAMESPACE = "http://www.w3.org/1999/XSL/Format";
    Namespace fo = Namespace.getNamespace("fo", NAMESPACE);

    private Document createDoc(String rootElement) {
        Document doc = new Document();
        /*Element root = new Element(rootElement, NAMESPACE);*/
        Element root = new Element("root","fo",NAMESPACE);
        root.addNamespaceDeclaration(fo);
        doc.setRootElement(root);
        return doc;
    }

    private void writeDoc(Document doc) {
        Element layoutMasterSet = new Element("layout-master-set",fo);

        doc.getRootElement().addContent(layoutMasterSet);

        /*Element simplePageMaster  = new Element("simple-page-master");*/
        Element simplePageMaster = new Element("simple-page-master",fo);
        simplePageMaster.setAttribute("master-name", "Organizer");
        simplePageMaster.setAttribute("page-height", "297mm");
        simplePageMaster.setAttribute("page-width", "210mm");
        simplePageMaster.setAttribute("margin-left", "12mm");
        simplePageMaster.setAttribute("margin-right", "12mm");
        simplePageMaster.setAttribute("margin-top", "20mm");
        simplePageMaster.setAttribute("margin-bottom", "20mm");

        layoutMasterSet.addContent(simplePageMaster);

        Element regionBody = new Element("region-body",fo);
        /*regionBody.setAttribute("margin","10mm 10mm 10mm 10mm");*/
        regionBody.setAttribute("margin-top", "10mm");
        regionBody.setAttribute("margin-bottom", "10mm");
        /*regionBody.setAttribute("column-counts","2");
        regionBody.setAttribute("column-gap", "4mm");*/

        /*Element regionBefore = new Element("region-before",fo);
        regionBefore.setAttribute("extent", "20mm");

        Element regionAfter = new Element("region-after",fo);
        regionAfter.setAttribute("extent", "20mm");*/



        simplePageMaster.addContent(regionBody);
       /* simplePageMaster.addContent(regionBefore);
        simplePageMaster.addContent(regionAfter);*/


        Element pageSequence = new Element("page-sequence",fo);
        pageSequence.setAttribute("master-reference","Organizer");

        doc.getRootElement().addContent(pageSequence);

        Element flow = new Element("flow",fo);
        flow.setAttribute("flow-name","xsl-region-body");

        pageSequence.addContent(flow);

        /** Anlegen des Blocks für den Inhalt einer ganzen Seite
         *
         */
        Element block = new Element("block",fo);
        /*block.setText("Dieses ist ein Text");*/

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
        /*left.setAttribute("absolute-position","left");*/
        /*left.setAttribute("break-after", "column");*/
        left.setText("testtttt");

        blockContainerLinks.addContent(left);

        //Block-Container 1/2 Seite unten
        Element blockContainerRechts = new Element("block-container",fo);
        /*blockContainerRechts.setAttribute("height","50%");*/
        blockContainerRechts.setAttribute("width","50%");
        blockContainerRechts.setAttribute("background-color", "green");
        blockContainerRechts.setAttribute("absolute-position","fixed");
        blockContainerRechts.setAttribute("left","100mm");
        blockContainerRechts.setAttribute("top","120mm");

        blockContainerUnten.addContent(blockContainerRechts);



        Element right = new Element("block",fo);
        right.setText("ssssssset");

        blockContainerRechts.addContent(right);


    }

    /*private void writeDoc(Document doc) {
        Element layoutMasterSet = new Element("fo:layout-master-set");

        doc.getRootElement().addContent(layoutMasterSet);

        Element simplePageMaster  = new Element("fo:simple-page-master");
        simplePageMaster.setAttribute("master-name", "Organizer");
        simplePageMaster.setAttribute("page-height", "297mm");
        simplePageMaster.setAttribute("page-width", "210mm");
        simplePageMaster.setAttribute("margin-left", "12mm");
        simplePageMaster.setAttribute("margin-right", "12mm");
        simplePageMaster.setAttribute("margin-top", "20mm");
        simplePageMaster.setAttribute("margin-bottom", "20mm");

        layoutMasterSet.addContent(simplePageMaster);

        Element regionBefore = new Element("fo:region-before");
        regionBefore.setAttribute("extent", "20mm");

        Element regionAfter = new Element("fo:region-after");
        regionAfter.setAttribute("extent", "20mm");

        Element regionBody = new Element("fo:region-body");
        regionBody.setAttribute("margin-top", "10mm");
        regionBody.setAttribute("margin-bottom", "10mm");

        simplePageMaster.addContent(regionBefore);
        simplePageMaster.addContent(regionAfter);
        simplePageMaster.addContent(regionBody);

        Element pageSequence = new Element("fo:page-sequence");
        pageSequence.setAttribute("master-reference","Test");

        doc.getRootElement().addContent(pageSequence);

        Element flow = new Element("fo:flow");
        flow.setAttribute("flow-name","xsl-region-body");

        pageSequence.addContent(flow);

        Element block = new Element("fo:block");
        block.setText("Dieses ist ein Text");

        flow.addContent(block);
    }*/

    private void writeXML(Document doc) {
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        try (FileOutputStream fos = new FileOutputStream(new File("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/organizer.fo"))) {
            XMLOutputter op = new XMLOutputter(format);
            op.output(doc, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static void write(Organizer org) {
            Document doc = org.createDoc("root");
            org.writeDoc(doc);
            org.writeXML(doc);
    }

    public static void foToPdf() throws Exception {
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
            Source foIn = new StreamSource(new File("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/Organizer.fo"));
            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Step 6: Start XSLT transformation and FOP processing
            transformer.transform(foIn, res);

        } finally {
            //Clean-up
            pdfOut.close();
        }
    }
}
