package org.example;

import org.xml.sax.SAXException;
import parsers.sax.ParserSAX;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class App
{
    public static void main( String[] args ){
       try{
           ParserSAX.parse("src/candy.xml",true);
       }catch(ParserConfigurationException | SAXException | IOException e){
           e.printStackTrace();
       }
    }
}
