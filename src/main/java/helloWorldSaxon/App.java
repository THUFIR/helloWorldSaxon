package helloWorldSaxon;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();
    private Customer customer = new Customer();

    public static void main(String[] args) throws Exception {
        LOG.fine("starting..");
        new App().doStuff();
    }

    private void doStuff() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/saxon.xml"));
        URL url = new URL(properties.getProperty("books"));

        JaxBExample jxb = new JaxBExample();
        //   customer = jxb.dummyCustomer();
        //   jxb.writeCustomerToFile(customer);
        customer = jxb.readCustomerFromFile();
        LOG.info(customer.toString());
    }

}
