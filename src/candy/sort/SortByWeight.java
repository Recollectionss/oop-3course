package candy.sort;

import candy.interfaces.CandySortStrategy;
import candy.model.Candy;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByWeight implements CandySortStrategy {
    @Override
    public void sort (ArrayList<Candy> candies) {candies.sort(Comparator.comparing(Candy::get_weight));}
}