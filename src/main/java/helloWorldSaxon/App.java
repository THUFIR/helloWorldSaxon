package helloWorldSaxon;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import org.w3c.dom.Document;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String[] args) throws Exception {
        LOG.fine("starting..");
        new App().scrapeHTML();
    }

    private void scrapeHTML() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/saxon.xml"));
        URL url = new URL(properties.getProperty("books"));
        // Document document = handler.createDocumentFromURL();
        //  handler.writeToFile(document);

        Demo demo = new Demo();
        // d.createHelloWorldDocument();
        Document document = demo.createDocumentFromURL(url);

        HandlerForXML handler = new HandlerForXML(url);
        handler.printDoc(document);

    }

}
