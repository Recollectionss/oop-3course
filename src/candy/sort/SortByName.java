package candy.sort;

import candy.model.Candy;
import candy.interfaces.CandySortStrategy;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByName implements CandySortStrategy {
    @Override
    public void sort(ArrayList<Candy> candies) {
        candies.sort(Comparator.comparing(Candy::get_name));
    }
}

