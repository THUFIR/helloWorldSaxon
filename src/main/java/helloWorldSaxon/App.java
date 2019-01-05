package helloWorldSaxon;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

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
        B b = new B();
        b.build();
    }

}
