package helloWorldSaxon;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;

//https://stackoverflow.com/q/17059227/262852

public class JaxBExample {

    private static final Logger LOG = Logger.getLogger(JaxBExample.class.getName());
    private String pathToFile = "/home/thufir/jaxb/jaxbexample.xml";
    private int id = 0;

    public JaxBExample() {
    }

    public Customer readCustomerFromFile() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Customer.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File(pathToFile);
        Customer customer = (Customer) unmarshaller.unmarshal(xml);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "/tmp/bla-bla.xsd"); //???
        Result result = new DOMResult();  //what to do with result?? nothing?
        marshaller.marshal(customer, result);
        return customer;
    }

    public Customer dummyCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("mkyong");
        customer.setAge((int) (Math.random() * 100));
        id++;
        return customer;
    }

    public void writeCustomersTofile(List<Customer> customers) {
        //new file, overwrite old file.
        //write a collection to the file
        //so that each customer can then be read in.
        //import to basex
    }

    public void writeCustomerToFile(Customer customer) throws Exception {
        File file = new File(pathToFile);
        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(customer, file);
    }

}
