package candy.search;

import candy.model.Candy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RangeSearch {

    private static List<Candy> searchByRange(ArrayList<Candy> sortedCandies, int low, int high, Comparator<Candy> comparator, RangeValueSetter rangeValueSetter) {
        Candy lowCandy = Candy.CandyFactory.generateCandyForSearch();
        rangeValueSetter.setRangeValue(lowCandy, low);
        int lowIndex = Collections.binarySearch(sortedCandies, lowCandy, comparator);
        if (lowIndex < 0) {
            lowIndex = -lowIndex - 1;
        }

        Candy highCandy = Candy.CandyFactory.generateCandyForSearch();
        rangeValueSetter.setRangeValue(highCandy, high);
        int highIndex = Collections.binarySearch(sortedCandies, highCandy, comparator);
        if (highIndex < 0) {
            highIndex = -highIndex - 2;
        }

        if (lowIndex <= highIndex) {
            return sortedCandies.subList(lowIndex, highIndex + 1);
        }

        return Collections.emptyList();
    }

    private static ArrayList<Candy> getSortedCandies(ArrayList<Candy> candies, Comparator<Candy> comparator) {
        ArrayList<Candy> sortedCandies = new ArrayList<>(candies);
        sortedCandies.sort(comparator);
        return sortedCandies;
    }

    @FunctionalInterface
    private interface RangeValueSetter {
        void setRangeValue(Candy candy, int value);
    }

    public static List<Candy> searchBySugar(ArrayList<Candy> candies, int low, int high) {
        if (candies == null || candies.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Candy> sortedCandies = getSortedCandies(candies, Comparator.comparingInt(Candy::getSugar));
        return searchByRange(sortedCandies, low, high, Comparator.comparingInt(Candy::getSugar), Candy::setSugar);
    }

    public static List<Candy> searchBySugarPer100g(ArrayList<Candy> candies, int low, int high) {
        if (candies == null || candies.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Candy> sortedCandies = getSortedCandies(candies, Comparator.comparingInt(Candy::getSugarPercentagePer100g));
        return searchByRange(sortedCandies, low, high, Comparator.comparingInt(Candy::getSugarPercentagePer100g), (candy, value) -> candy.setSugar(value));
    }
}
