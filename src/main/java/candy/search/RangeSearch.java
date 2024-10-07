package candy.search;

import candy.model.Candy;
import candy.sort.SortBySugar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class RangeSearch {
    public static List<Candy> searchBySugar(ArrayList<Candy> candies, int low, int high) {
        if (candies == null || candies.isEmpty()) {
            return Collections.emptyList();
        }

        ArrayList<Candy> sortedCandies = new SortBySugar().sort(candies);

        Candy lowCandy = Candy.CandyFactory.generateCandyForSearch();
        lowCandy.setSugar(low);
        int lowIndex = Collections.binarySearch(sortedCandies, lowCandy, Comparator.comparingInt(Candy::getSugarPercentagePer100g));
        if (lowIndex < 0) {
            lowIndex = -lowIndex - 1;
        }

        Candy highCandy = Candy.CandyFactory.generateCandyForSearch();
        highCandy.setSugar(high);
        int highIndex = Collections.binarySearch(sortedCandies, highCandy, Comparator.comparingInt(Candy::getSugar));
        if (highIndex < 0) {
            highIndex = -highIndex - 2;
        }

        if (lowIndex <= highIndex) {
            return sortedCandies.subList(lowIndex, highIndex + 1);
        }

        return Collections.emptyList();
    }

    public static List<Candy> searchBySugarPer100g(ArrayList<Candy> candies, int low, int high) {
        if (candies == null || candies.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Candy> sortedCandies = new SortBySugar().sort(candies);
        return null;
    }
}