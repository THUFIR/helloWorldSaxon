package helloWorldSaxon;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String[] args) throws TransformerException, TransformerConfigurationException, IOException, SAXException {
        LOG.fine("starting..");
        new App().tidySomeWebPage();
    }

    private void tidySomeWebPage() throws TransformerConfigurationException, TransformerException, IOException, SAXException {
        properties.loadFromXML(App.class.getResourceAsStream("/saxon.xml"));
        String url = properties.getProperty("mobi");

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        TransformerFactory factory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source source = new SAXSource(xmlReader, new InputSource(url));

        Transformer transformer = factory.newTransformer();
        transformer.transform(source, result);
        
        String stringResult = writer.toString();
        LOG.info(stringResult);
    }

}
