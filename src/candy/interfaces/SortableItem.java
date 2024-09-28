package candy.interfaces;

import candy.enums.CandyType;

public interface SortableItem {
    int getId();
    String getName();
    CandyType getCandyType() ;
    int getSugar();
    int getPrice();
    int getSugarPercentagePer100g();
    int getWeight();
}
