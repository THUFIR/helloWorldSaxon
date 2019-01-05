package helloWorldSaxon;

import java.io.File;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//https://stackoverflow.com/q/4142046/262852
public class C {

    private static final Logger LOG = Logger.getLogger(C.class.getName());

    public C() {

    }

    public void b2() throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("CONFIGURATION");
        doc.appendChild(rootElement);
        Element browser = doc.createElement("BROWSER");
        browser.appendChild(doc.createTextNode("chrome"));
        rootElement.appendChild(browser);
        Element base = doc.createElement("BASE");
        base.appendChild(doc.createTextNode("http:fut"));
        rootElement.appendChild(base);
        Element employee = doc.createElement("EMPLOYEE");
        rootElement.appendChild(employee);
        Element empName = doc.createElement("EMP_NAME");
        empName.appendChild(doc.createTextNode("Anhorn, Irene"));
        employee.appendChild(empName);
        Element actDate = doc.createElement("ACT_DATE");
        actDate.appendChild(doc.createTextNode("20131201"));
        employee.appendChild(actDate);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("/tmp/b2.xml"));
        transformer.transform(source, result);
        System.out.println("File saved!");
    }

    public void b1() throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        //root elements
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("company");
        doc.appendChild(rootElement);

        //staff elements
        Element staff = doc.createElement("Staff");
        rootElement.appendChild(staff);

        //set attribute to staff element
        Attr attr = doc.createAttribute("id");
        attr.setValue("1");
        staff.setAttributeNode(attr);

        //shorten way
        //staff.setAttribute("id", "1");
        //firstname elements
        Element firstname = doc.createElement("firstname");
        firstname.appendChild(doc.createTextNode("yong"));
        staff.appendChild(firstname);

        //lastname elements
        Element lastname = doc.createElement("lastname");
        lastname.appendChild(doc.createTextNode("mook kim"));
        staff.appendChild(lastname);

        //nickname elements
        Element nickname = doc.createElement("nickname");
        nickname.appendChild(doc.createTextNode("mkyong"));
        staff.appendChild(nickname);

        //salary elements
        Element salary = doc.createElement("salary");
        salary.appendChild(doc.createTextNode("100000"));
        staff.appendChild(salary);

        //write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File("/tmp/b1.xml"));
        transformer.transform(source, result);

        System.out.println("Done");

    }

}
