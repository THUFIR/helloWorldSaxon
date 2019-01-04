package helloWorldSaxon;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
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

    public String fetchFromURL() throws TransformerConfigurationException, TransformerException, IOException, SAXException {
        StringWriter writer = new StringWriter();
        StreamResult streamResult = new StreamResult(writer);

        TransformerFactory factory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source source = new SAXSource(xmlReader, new InputSource(url.toString()));

        Transformer transformer = factory.newTransformer();
        transformer.transform(source, streamResult);

        String stringResult = writer.toString();
        LOG.fine(stringResult);
        return stringResult;
    }

    public Document parseString(String string) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document;
        document = builder.parse(new InputSource(new StringReader(string)));
     //   String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
     //   SchemaFactory sfactory = SchemaFactory.newInstance(language);
    //    Schema schema = sfactory.newSchema(); //????  what is the validation schema
    //    Validator validator = schema.newValidator();
    //    validator.validate(new DOMSource(document));
        return document;
    }

}
