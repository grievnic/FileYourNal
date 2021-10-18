package de.entsesselt.de.fileyournal;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class Generators {


    public class JDOMSchreiben {

        private final static String FILENAME = "Organizer.fo";
        private final static File FILE = new File(FILENAME);

        private final static String NAMESPACE = "http://www.w3.org/1999/XSL/Format";

        private Document createDoc(String rootElement) {
            Document doc = new Document();
            Element root = new Element(rootElement, NAMESPACE);
            doc.setRootElement(root);
            return doc;
        }


        private void writeDoc(Document doc) {
            Element e1 = new Element("e1");
            e1.setAttribute("name", e1.getName());
            e1.setAttribute("value", "Wert 1");
            doc.getRootElement().addContent(e1);
            Element e2 = new Element("e2");
            Element n2 = new Element("name");
            n2.setText("e2");
            Element v2 = new Element("value");
            v2.setText("Wert 2");
            e2.addContent(n2);
            e2.addContent(v2);
            doc.getRootElement().addContent(e2);
        }

        private void writeXML(Document doc) {
            Format format = Format.getPrettyFormat();
            format.setIndent("    ");
            try (FileOutputStream fos = new FileOutputStream(FILE)) {
                XMLOutputter op = new XMLOutputter(format);
                op.output(doc, fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*public static void main(String[] args) {
            JDOMSchreiben jds = new JDOMSchreiben();
            Document doc = jds.createDoc("elements");
            jds.writeDoc(doc);
            jds.writeXML(doc);
        }*/
    }

    public static void foToPdf() throws Exception
    {
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
            Source foIn = new StreamSource(new File("/Users/nicolegrieve/Documents/GitHub/Bachelorarbeit/Document.fo"));
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
