package parsers.sax;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;



public class ParserSAX {
    public static void parse(String path, boolean useFile) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        UserHandler userHandler = new UserHandler();

        if (useFile) {
            File xmlFile = new File(path);
            saxParser.parse(xmlFile, userHandler);
        } else {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(
                    "<?xml version=\"1.0\"?> <rootElement></rootElement>".getBytes(StandardCharsets.UTF_8)
            );
            saxParser.parse(inputStream, userHandler);
        }

    }
}

