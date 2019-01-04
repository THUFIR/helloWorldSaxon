package helloWorldSaxon;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
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

    public static void main(String[] args) throws TransformerException, TransformerConfigurationException, SAXException, IOException   {
        LOG.fine("starting..");
        new App().scrapeBooks();
    }

    private void scrapeBooks() throws TransformerConfigurationException, TransformerException, SAXException, IOException     {
        properties.loadFromXML(App.class.getResourceAsStream("/saxon.xml"));
        String url = properties.getProperty("url");

        TransformerFactory factory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source input = new SAXSource(xmlReader,new InputSource(url));
        Result output = new StreamResult(System.out);
        factory.newTransformer().transform(input, output);

    }

}
