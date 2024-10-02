package main.java.candy.interfaces;

import main.java.candy.enums.CandyType;

public interface CandyInterface {
    int getId();
    String getName();
    CandyType getCandyType() ;
    int getSugar();
    int getPrice();
    int getSugarPercentagePer100g();
    int getWeight();
}
