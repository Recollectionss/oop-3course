package candy.sort;

import candy.interfaces.CandySortStrategy;
import candy.model.Candy;

import java.util.ArrayList;
import java.util.Comparator;

public class SortById implements CandySortStrategy {
    @Override
    public void sort(ArrayList<Candy> candies) {candies.sort(Comparator.comparing(Candy::getId));}
}
