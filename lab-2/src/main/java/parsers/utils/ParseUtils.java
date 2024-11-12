package parsers.utils;

import candy.Candy;
import candy.Candy.Ingredients;

import org.w3c.dom.Element;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class ParseUtils {
    public static void setCandyProperty(Candy candy, String tag, String content) {
        switch (tag) {
            case "id": candy.setId(content); break;
            case "name": candy.setName(content); break;
            case "energy": candy.setEnergy(Integer.parseInt(content)); break;
            case "type": candy.setType(content); break;
            case "production": candy.setProduction(content); break;
        }
    }

    public static void setIngredientsProperty(Ingredients ingredients, String tag, String content) {
        switch (tag) {
            case "water": ingredients.setWater(Float.parseFloat(content)); break;
            case "sugar": ingredients.setSugar(Float.parseFloat(content)); break;
            case "fructose": ingredients.setFructose(Float.parseFloat(content)); break;
            case "chocolateType": ingredients.setChocolateType(content); break;
            case "vanillin": ingredients.setVanillin(Float.parseFloat(content)); break;
        }
    }

    public static void setValueProperty(Candy candy, String tag, String content) {
        switch (tag) {
            case "proteins": candy.setProteins(Float.parseFloat(content)); break;
            case "fats": candy.setFats(Float.parseFloat(content)); break;
            case "carbohydrates": candy.setCarbohydrates(Float.parseFloat(content)); break;
        }
    }

    public static String getCharacterData(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event = eventReader.nextEvent();
        if (event instanceof Characters) {
            return event.asCharacters().getData();
        }
        return "";
    }

    public static float parseFloatElement(Element parent, String tagName) {
        return parent.getElementsByTagName(tagName).getLength() > 0
                ? Float.parseFloat(parent.getElementsByTagName(tagName).item(0).getTextContent()) : 0;
    }

    public static String parseStringElement(Element parent, String tagName) {
        return parent.getElementsByTagName(tagName).getLength() > 0
                ? parent.getElementsByTagName(tagName).item(0).getTextContent() : null;
    }
}
