package helloWorldSaxon;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
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

    public void createDocumentFromURL() throws SAXException, IOException, TransformerException, ParserConfigurationException {
        LOG.info(url.toString());

        InputStream inputStream = url.openStream();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source source = new SAXSource(xmlReader, new InputSource(url.toString()));

        StringWriter writer = new StringWriter();
        StreamResult streamResult = new StreamResult(writer);

//        DOMResult dr;
        DOMResult domResult = new DOMResult();
        
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, domResult);
//        transformer.transform(source, streamResult);

        
        String stringResult = writer.toString();
        LOG.info(domResult.toString());
        
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = db.newDocument();
       // DOMSource domSource = new DOMSource(db.parse(inputStream));

  //      LOG.info(document.toString());
    }
}
