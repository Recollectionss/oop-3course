package candy.sort;

import candy.model.Candy;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByWeight implements candy.interfaces.CandySortStrategyInterface {
    @Override
    public void sort (ArrayList<Candy> candies) {candies.sort(Comparator.comparing(Candy::getWeight));}
}
