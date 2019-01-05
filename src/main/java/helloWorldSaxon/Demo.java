package helloWorldSaxon;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Demo {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public Demo() {
    }

    public void writeToFile(Document document) throws Exception {
        DOMSource source = new DOMSource(document);
        FileWriter writer = new FileWriter(new File("/tmp/output.xml"));
        StreamResult result = new StreamResult(writer);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
    }

    public Document createDocumentFromURL(URL url) throws Exception {
        LOG.fine(url.toString());

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Foo foo = new Foo();
        foo.setBar("Hello World.............................");

        JAXBContext jaxbContext = JAXBContext.newInstance(Foo.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(foo, document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
        Source source = new SAXSource(xmlReader, new InputSource(url.toString()));

        DOMResult domResult = new DOMResult();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, domResult);

        //     DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = (Document) domResult.getNode();

        return document;
    }

    public void createHelloWorldDocument() throws Exception {
        // Create the JAXBContext
        JAXBContext jaxbContext = JAXBContext.newInstance(Foo.class);

        // Create the Object
        Foo foo = new Foo();
        foo.setBar("Hello World");

        // Create the Document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // Marshal the Object to a Document
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(foo, document);

        writeToFile(document);

        // Output the Document
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(System.out);
        t.transform(source, result);
    }

}
