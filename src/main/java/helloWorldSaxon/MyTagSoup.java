package helloWorldSaxon;

//import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2DOM;
import java.util.logging.Logger;
import org.ccil.cowan.tagsoup.Parser;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class MyTagSoup {

    private static final Logger LOG = Logger.getLogger(MyTagSoup.class.getName());

    public MyTagSoup() {
    }

    /*
    public void foo(Node document) throws Exception {

        final Parser parser = new Parser();

        SAX2DOM sax2dom = new SAX2DOM();
        parser.setContentHandler(sax2dom);
        parser.setFeature(Parser.namespacesFeature, false);
        String urlIS = null;
        parser.parse(new InputSource(urlIS));

        document = sax2dom.getDOM();

    }
*/
}
