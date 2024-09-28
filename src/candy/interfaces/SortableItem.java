package candy.interfaces;

import candy.model.Candy;

public interface SortableItem {
    int get_id();
    String get_name();
    Candy.CandyType get_candyType() ;
    int get_sugar();
    int get_price();
    int get_sugarPercentagePer100g();
    int get_weight();
}
