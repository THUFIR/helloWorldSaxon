package helloWorldSaxon;

import java.io.File;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

//https://stackoverflow.com/q/4142046/262852
public class JaxBExample {

//public class JAXBExample {
    private static final Logger LOG = Logger.getLogger(JaxBExample.class.getName());

    public JaxBExample() {
    }

    public void foo() throws Exception {

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
