package helloWorldSaxon;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class Demo {

    public Demo() {
    }

    public void foo() throws Exception {
        // Create the JAXBContext
        JAXBContext jc = JAXBContext.newInstance(Foo.class);

        // Create the Object
        Foo foo = new Foo();
        foo.setBar("Hello World");

        // Create the Document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // Marshal the Object to a Document
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(foo, document);

        // Output the Document
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(System.out);
        t.transform(source, result);
    }

}
