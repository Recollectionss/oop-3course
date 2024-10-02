package main.java.candy.sort;

import main.java.candy.model.Candy;

import java.util.ArrayList;
import java.util.Comparator;

public class SortBySugarPer100 implements main.java.candy.interfaces.CandySortStrategyInterface {
    @Override
    public void sort(ArrayList<Candy> candies) {candies.sort(Comparator.comparing(Candy::getSugarPercentagePer100g));}
}