package helloWorldSaxon;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String[] args) throws TransformerException, TransformerConfigurationException, IOException, SAXException {
        LOG.fine("starting..");
        new App().foo();
    }

    private void foo() throws TransformerConfigurationException, TransformerException, IOException, SAXException {
        properties.loadFromXML(App.class.getResourceAsStream("/saxon.xml"));
        String url = properties.getProperty("mobi");

        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source input = new SAXSource(xmlReader, new InputSource(url));

      //  StreamSource source = new StreamSource(new StringReader("<xml>blabla</xml>"));
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.transform(input, result);
        String strResult = writer.toString();
        LOG.info(strResult);
    }

    private void scrapeBooks() throws TransformerConfigurationException, TransformerException, SAXException, IOException {
        properties.loadFromXML(App.class.getResourceAsStream("/saxon.xml"));
        String url = properties.getProperty("url");

        TransformerFactory factory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source input = new SAXSource(xmlReader, new InputSource(url));
        Result output = new StreamResult(System.out);
        //        StreamResult result = new StreamResult(new File(dir + "\\XML\\" + name + ".xml"));
        factory.newTransformer().transform(input, (Result) output);

    }

}
