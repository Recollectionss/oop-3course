package parsers.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ParserDOM {
    public static void parse(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();

        File xmlFile = new File(path);

        ByteArrayInputStream input = new ByteArrayInputStream("<?xml version=\"1.0\"?> <rootElement></rootElement>".getBytes(StandardCharsets.UTF_8));

        Document xmldoc = docBuilder.parse(input);

        Element element = xmldoc.getDocumentElement();
        System.out.println("Root element name is "+element.getTagName());

    }
}
