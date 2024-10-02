package recourse.JSON.model;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;


public class JSON {
    public static String getRandomCandyName(){
        try (Reader reader = new FileReader(new File("./src/main/resources/JSON/randomCandyName.json"))){

            return "";
        }catch (Exception e){
            return "";
        }
    }
}
