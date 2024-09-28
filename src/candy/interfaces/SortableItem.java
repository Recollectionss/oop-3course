package candy.interfaces;

import candy.enums.CandyType;

public interface SortableItem {
    int get_id();
    String get_name();
    CandyType get_candyType() ;
    int get_sugar();
    int get_price();
    int get_sugarPercentagePer100g();
    int get_weight();
}
