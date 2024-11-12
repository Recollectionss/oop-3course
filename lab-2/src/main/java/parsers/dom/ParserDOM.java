package parsers.dom;

import candy.Candy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParserDOM {
    public static void parse(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();

        File xmlFile = new File(path);
        Document xmldoc = docBuilder.parse(xmlFile);

        Element rootElement = xmldoc.getDocumentElement();
        System.out.println("Root element name is " + rootElement.getTagName());

        NodeList nodes = rootElement.getElementsByTagName("candy");

        List<Candy> candyList = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element candyElement = (Element) node;

                // Отримання основних атрибутів та елементів
                String id = candyElement.getAttribute("id");
                String name = candyElement.getElementsByTagName("name").item(0).getTextContent();
                int energy = Integer.parseInt(candyElement.getElementsByTagName("energy").item(0).getTextContent());
                String type = candyElement.getElementsByTagName("type").item(0).getTextContent();
                String production = candyElement.getElementsByTagName("production").item(0).getTextContent();

                // Отримання вкладеного елемента ingredients
                Element ingredientsElement = (Element) candyElement.getElementsByTagName("ingredients").item(0);
                float water = parseFloatElement(ingredientsElement, "water");
                float sugar = parseFloatElement(ingredientsElement, "sugar");
                float fructose = parseFloatElement(ingredientsElement, "fructose");
                String chocolateType = parseStringElement(ingredientsElement, "chocolateType");
                float vanillin = parseFloatElement(ingredientsElement, "vanillin");

                // Отримання вкладеного елемента value
                Element valueElement = (Element) candyElement.getElementsByTagName("value").item(0);
                float proteins = Float.parseFloat(valueElement.getElementsByTagName("proteins").item(0).getTextContent());
                float fats = Float.parseFloat(valueElement.getElementsByTagName("fats").item(0).getTextContent());
                float carbohydrates = Float.parseFloat(valueElement.getElementsByTagName("carbohydrates").item(0).getTextContent());

                // Створення об'єкта Candy та додавання до списку
                Candy candy = new Candy(id, name, energy, type, production, water, sugar, fructose, chocolateType, vanillin, proteins, fats, carbohydrates);
                candyList.add(candy);
            }
        }

        // Виведення інформації про цукерки
        for (Candy candy : candyList) {
            System.out.println(candy);
        }
    }

    private static float parseFloatElement(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        return nodeList.getLength() > 0 ? Float.parseFloat(nodeList.item(0).getTextContent()) : 0;
    }

    private static String parseStringElement(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        return nodeList.getLength() > 0 ? nodeList.item(0).getTextContent() : null;
    }
}
