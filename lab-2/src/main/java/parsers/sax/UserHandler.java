package parsers.sax;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

public class UserHandler extends DefaultHandler {
    private StringBuilder buffer = new StringBuilder();
    private int indentLevel = 0;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (Objects.equals(qName, "root")) {
            System.out.println("Start Element: " + qName);
        } else {
           if(Objects.equals(qName, "candy")) {
               indentLevel++;
               printIndent();
               System.out.print(qName + ": ");
               System.out.print('\n');
               indentLevel++;
           }else if(Objects.equals(qName, "value") || Objects.equals(qName, "ingredients")){
               printIndent();
               System.out.print(qName + ": ");
               System.out.print('\n');
               indentLevel++;
           }else{
               printIndent();
               System.out.print(qName + ": ");
               indentLevel++;
           }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (Objects.equals(qName, "root")) {
            System.out.println("End Element: " + qName);
        } else {

            if (buffer.length() > 0) {
                System.out.println(buffer.toString().trim());
            }
            buffer.setLength(0);
            if(Objects.equals(qName, "candy")){
                indentLevel-=2;
            }else{
                indentLevel--;
            }
        }
    }

    public void characters(char[] ch, int start, int length) {
        buffer.append(new String(ch, start, length));
    }

    private void printIndent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("    ");
        }
    }
}

