package helloWorldSaxon;

import java.io.File;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


//https://stackoverflow.com/q/17059227/262852

public class JaxBExample {

//public class JAXBExample {
    private static final Logger LOG = Logger.getLogger(JaxBExample.class.getName());

    public JaxBExample() {
    }

    public void readFromFile() throws Exception {

        JAXBContext jc = JAXBContext.newInstance(Customer.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("src/forum17059227/input.xml");
        Customer customer = (Customer) unmarshaller.unmarshal(xml);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "file:///C:/Documents%20and%20Settings/mojalal/Desktop/FirstXSD.xml");
        marshaller.marshal(customer, System.out);

    }

    public void writeToFile() throws Exception {

        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("mkyong");
        customer.setAge(29);

        File file = new File("/tmp/jaxbexample.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(customer, file);
        jaxbMarshaller.marshal(customer, System.out);

    }
}
