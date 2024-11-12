//package parsers.sax;
//
//import org.xml.sax.SAXException;
//import org.xml.sax.Attributes;
//import org.xml.sax.helpers.DefaultHandler;
//
//import java.util.Objects;
//
//public class UserHandler extends DefaultHandler {
//    private StringBuilder buffer = new StringBuilder();
//    private int indentLevel = 0;
//
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        if (Objects.equals(qName, "root")) {
//            System.out.println("Start Element: " + qName);
//        } else {
//           if(Objects.equals(qName, "candy")) {
//               indentLevel++;
//               printIndent();
//               System.out.print(qName + ": ");
//               System.out.print('\n');
//               indentLevel++;
//           }else if(Objects.equals(qName, "value") || Objects.equals(qName, "ingredients")){
//               printIndent();
//               System.out.print(qName + ": ");
//               System.out.print('\n');
//               indentLevel++;
//           }else{
//               printIndent();
//               System.out.print(qName + ": ");
//               indentLevel++;
//           }
//        }
//    }
//
//    public void endElement(String uri, String localName, String qName) {
//        if (Objects.equals(qName, "root")) {
//            System.out.println("End Element: " + qName);
//        } else {
//
//            if (buffer.length() > 0) {
//                System.out.println(buffer.toString().trim());
//            }
//            buffer.setLength(0);
//            if(Objects.equals(qName, "candy")){
//                indentLevel-=2;
//            }else{
//                indentLevel--;
//            }
//        }
//    }
//
//    public void characters(char[] ch, int start, int length) {
//        buffer.append(new String(ch, start, length));
//    }
//
//    private void printIndent() {
//        for (int i = 0; i < indentLevel; i++) {
//            System.out.print("    ");
//        }
//    }
//}
package parsers.sax;

import candy.Candy;
import candy.Candy.Ingredients;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserHandler extends DefaultHandler {
    private StringBuilder buffer = new StringBuilder();
    private int indentLevel = 0;
    private List<Candy> candyList = new ArrayList<>(); // Коллекция для хранения объектов Candy
    private Candy currentCandy = null; // Текущий объект Candy
    private Ingredients currentIngredients = null; // Текущий объект Ingredients
    private boolean isInIngredients = false; // Флаг для отслеживания, находимся ли внутри <ingredients>
    private boolean isInValue = false; // Флаг для отслеживания, находимся ли внутри <value>

    // Метод для получения списка всех конфигурируемых конфет
    public List<Candy> getCandyList() {
        return candyList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (Objects.equals(qName, "candy")) {
            // Новый объект Candy
            currentCandy = new Candy();
            candyList.add(currentCandy);
            isInIngredients = false;
            isInValue = false;
            indentLevel++;
        } else if (Objects.equals(qName, "ingredients")) {
            // Создаем объект Ingredients
            currentIngredients = new Ingredients();
            isInIngredients = true;
            indentLevel++;
        } else if (Objects.equals(qName, "value")) {
            // Устанавливаем флаг, что парсим значения
            isInValue = true;
            indentLevel++;
        } else {
            indentLevel++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (Objects.equals(qName, "candy")) {
            // Добавляем текущую конфету в список и сбрасываем объект
            if (currentCandy != null) {
                currentCandy.setIngredients(currentIngredients); // Привязываем ingredients к конфете
                currentCandy = null;
            }
            indentLevel--;
        } else if (Objects.equals(qName, "ingredients")) {
            // Завершаем парсинг ingredients
            currentCandy.setIngredients(currentIngredients); // Привязываем ingredients к конфете
            currentIngredients = null;
            isInIngredients = false;
            indentLevel--;
        } else if (Objects.equals(qName, "value")) {
            // Завершаем парсинг value
            isInValue = false;
            indentLevel--;
        } else {
            indentLevel--;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (content.isEmpty()) return;

        // Обрабатываем данные в зависимости от текущего элемента
        if (isInIngredients) {
            if (buffer.length() > 0) {
                String tagName = buffer.toString();
                switch (tagName) {
                    case "water":
                        currentIngredients.setWater(Float.parseFloat(content));
                        break;
                    case "sugar":
                        currentIngredients.setSugar(Float.parseFloat(content));
                        break;
                    case "fructose":
                        currentIngredients.setFructose(Float.parseFloat(content));
                        break;
                    case "chocolateType":
                        currentIngredients.setChocolateType(content);
                        break;
                    case "vanillin":
                        currentIngredients.setVanillin(Float.parseFloat(content));
                        break;
                }
            }
        } else if (isInValue) {
            if (buffer.length() > 0) {
                String tagName = buffer.toString();
                switch (tagName) {
                    case "proteins":
                        currentCandy.setProteins(Float.parseFloat(content));
                        break;
                    case "fats":
                        currentCandy.setFats(Float.parseFloat(content));
                        break;
                    case "carbohydrates":
                        currentCandy.setCarbohydrates(Float.parseFloat(content));
                        break;
                }
            }
        } else {
            // Обрабатываем основные теги конфеты
            switch (buffer.toString()) {
                case "id":
                    currentCandy.setId(content);
                    break;
                case "name":
                    currentCandy.setName(content);
                    break;
                case "energy":
                    currentCandy.setEnergy(Integer.parseInt(content));
                    break;
                case "type":
                    currentCandy.setType(content);
                    break;
                case "production":
                    currentCandy.setProduction(content);
                    break;
            }
        }
        buffer.setLength(0); // Очищаем буфер
    }
}

