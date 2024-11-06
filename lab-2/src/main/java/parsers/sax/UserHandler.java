package parsers.sax;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class UserHandler extends DefaultHandler {
    public void startElement( String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        System.out.println("Start Element : " + qName);
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.println("End Element : " + qName);
    }
    public void characters(char[] ch, int start, int length) {
        System.out.println("Text Content : " + new String(ch, start, length));
    }
}
