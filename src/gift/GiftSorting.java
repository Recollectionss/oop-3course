package gift;

import candy.Candy;

import java.util.ArrayList;

public class GiftSorting {
    public static ArrayList<Candy> sortBySugar(ArrayList<Candy> candies) {
        if (candies.size() <= 1) return candies;

        var pivotIndex = candies.size() / 2;
        var pivot = candies.get(pivotIndex);

        ArrayList<Candy> lessCandies = new ArrayList<>();
        ArrayList<Candy> greaterCandies = new ArrayList<>();

        for (int i = 0; i < candies.size(); i++) {
            if (i == pivotIndex) continue;
            if (candies.get(i).get_sugar() < pivot.get_sugar()) {
                lessCandies.add(candies.get(i));
            } else {
                greaterCandies.add(candies.get(i));
            }
        }

        ArrayList<Candy> sortedCandies = sortBySugar(lessCandies);
        sortedCandies.add(pivot);
        sortedCandies.addAll(sortBySugar(greaterCandies));

        return sortedCandies;
    }

}