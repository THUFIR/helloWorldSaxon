package helloWorldSaxon;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String[] args) throws TransformerException, SAXException, ParserConfigurationException, IOException {
        LOG.fine("starting..");
        new App().scrapeHTML();
    }

    private void scrapeHTML() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        properties.loadFromXML(App.class.getResourceAsStream("/saxon.xml"));
        URL url = new URL(properties.getProperty("books"));
        HandlerForXML handler = new HandlerForXML(url);
        Document document = handler.createDocumentFromURL();
      //  handler.printDoc(doc);
      handler.writeToFile(document);
    }

}
