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

    private static Document currentOrganizer;
    private static Organizer INSTANCE = null;

    private final static String NAMESPACE = "http://www.w3.org/1999/XSL/Format";
    static Namespace fo = Namespace.getNamespace("fo", NAMESPACE);


    /**
     * singleton
     * @return the only instance from organizer as singleton
     */
    public static Organizer getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Organizer();
        } return INSTANCE;
    }

    /**
     * This method reads the XSL-FO-File (from path) and saves a JDOM-tree in variable currentOrganizer
     * @param foPath path to XSL-FO file
     */
    public void readFO(String foPath) {
        try {
            // Create a DocumentBuilder
            SAXBuilder saxBuilder = new SAXBuilder();
            // saves the tree-data in variable
            currentOrganizer = saxBuilder.build(foPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * add new page to the end of the index
     * @param newPage the new page
     */
    public static void addPage(Element newPage) {
        fetchPageParent().addContent(newPage);
    }

    /**
     * replace page content from current page
     * @param pageIndex current page
     * @param modifiedPage the new page
     */
    public void addModifiedContent(int pageIndex, Element modifiedPage){
        fetchPageParent().setContent(pageIndex, modifiedPage);
    }

    /**
     * insert new page at index (before or after current)
     * @param pageIndex place to insert
     * @param newPage the new page
     */
    public void insertContent(int pageIndex, Element newPage){
        fetchPageParent().addContent(pageIndex, newPage);
    }

    /**
     * delete current page
     * @param pageIndex from current page
     */
    public void deletePage (int pageIndex) {
        fetchPageParent().removeContent(pageIndex);
    }

    /**
     * This method finds the <fo: flow>-Element, as Parent from all <fo:block>-Elements representing pages (and content)
     * @return flow
     */
    public static Element fetchPageParent(){
        Element root = currentOrganizer.getRootElement();
        Element pageSequence = root.getChild("page-sequence",fo);
        return pageSequence.getChild("flow", fo);
    }


    /**
     * saves the XSL-FO at the user given path
     * @param filePath where to write the XSL-FO file
     */
    public void writeFO(String filePath){
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        System.out.println(filePath);
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            XMLOutputter op = new XMLOutputter(format);
            op.output(currentOrganizer, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * exports the XSL-FO document to pdf
     * @param filePath the path to XSL-FO document
     * @param targetPath given from user at the start - as offer for the save dialogue
     * @throws Exception if error occurs
     */
    public void foToPdf(String filePath, String targetPath) throws Exception {
        // Construct a FopFactory
        FopFactory fopFactory = FopFactory.newInstance(new File("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/fop.xconf"));
        // Set up output stream.
        // Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
        try (OutputStream pdfOut = new BufferedOutputStream(new FileOutputStream(targetPath))) {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, pdfOut);
            // Step 4: Setup JAXP using identity transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(); // identity transformer
            // Setup input and output for XSLT transformation
            // Setup input stream
            Source foIn = new StreamSource(new File(filePath));
            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());
            // Start XSLT transformation and FOP processing
            transformer.transform(foIn, res);
        } catch (FOPException e) {
            e.printStackTrace();
        }
        //Clean-up
    }
}
