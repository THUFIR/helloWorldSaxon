package helloWorldSaxon;

import java.io.File;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;

//https://stackoverflow.com/q/17059227/262852
public class JaxBExample {

    private static final Logger LOG = Logger.getLogger(JaxBExample.class.getName());
    private String pth = "/home/thufir/jaxb/jaxbexample.xml";

    public JaxBExample() {
    }

    public Customer readCustomerFromFile() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Customer.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File(pth);
        Customer customer = (Customer) unmarshaller.unmarshal(xml);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "/tmp/bla-bla.xsd");
        Result result = new DOMResult();
        marshaller.marshal(customer, result);
        return customer;
    }

    public Customer dummyCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("mkyong");
        customer.setAge(29);
        return customer;
    }

    public void writeCustomerToFile(Customer customer) throws Exception {

        File file = new File(pth);
        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(customer, file);
//        jaxbMarshaller.marshal(customer, System.out);
    }
}
