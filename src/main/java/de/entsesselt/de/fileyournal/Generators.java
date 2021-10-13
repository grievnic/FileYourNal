package de.entsesselt.de.fileyournal;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Generators {
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
