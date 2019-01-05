package helloWorldSaxon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class HandlerForXML {

    private static final Logger LOG = Logger.getLogger(HandlerForXML.class.getName());
    private URL url = null;

    private HandlerForXML() {
    }

    public HandlerForXML(URL url) {
        this.url = url;
    }

    public Document createDocumentFromURL() throws SAXException, IOException, TransformerException, ParserConfigurationException {
        LOG.fine(url.toString());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source source = new SAXSource(xmlReader, new InputSource(url.toString()));

        DOMResult domResult = new DOMResult();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, domResult);

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = (Document) domResult.getNode();

        return document;
    }

    public void printDoc(Document document) throws TransformerConfigurationException, TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(System.out));
    }

    public void writeToFile(Document document) throws IOException, TransformerException {
        DOMSource source = new DOMSource(document);
        FileWriter writer = new FileWriter(new File("/tmp/output.xml"));
        StreamResult result = new StreamResult(writer);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
    }
}
