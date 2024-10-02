package main.java.gift.model;

import main.java.candy.model.Candy;
import main.java.gift.interfaces.GiftInterface;

import java.util.ArrayList;

public class Gift implements GiftInterface {
    private static int _idCounter = 0;
    private String _name;
    private int _id;
    private ArrayList<Candy> _candies;
    private int _totalCost;


    public Gift(String name,ArrayList<Candy> candies) {
        _id = ++_idCounter;
        _name = name;
        _candies = new ArrayList<Candy>(candies);
        for (Candy candy : candies) {
            _totalCost += candy.getPrice();
        }
    }
    public Gift(String name){
        _id = ++_idCounter;
        _name = name;
        _candies = new ArrayList<>();
    }

    public Gift(int id){
        //need use db for send query for found gift with this id
    }

    public void printGiftInfo(){
        System.out.println("//////////////////////////");
        System.out.println("Id:" + _id);
        System.out.println("Name:" + _name);
        System.out.println("TotalCost:" + _totalCost);
        System.out.println("Candies contains:" + _candies.size());
        System.out.println("//////////////////////////");
    }

    public Candy findCandyById(int id){
        for (Candy candy : _candies) {
            if(candy.getId() == id){
                return candy;
            }
        }
        throw new IllegalArgumentException("Candy not found");
    }

    public void addCandy(Candy candy){
        _candies.add(candy);
        _totalCost += candy.getPrice();
    }

    public void deleteCandy(int id){
        if (id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        Candy candy = findCandyById(id);
        _candies.remove(candy);
        _totalCost -= candy.getPrice();
    }

    public void setCandies(ArrayList<Candy> candies){
        if (candies.isEmpty()){
            throw new IllegalArgumentException("candies size must be not empty");
        }
        _candies = candies;
    }
    public void setName(String name){
        if (name == null){
            throw new IllegalArgumentException("name must not be null");
        }
        _name = name;
    }

    public int getId(){return _id;}
    public String getName(){return _name;}
    public int getTotalCost() {return _totalCost;}
    public ArrayList<Candy> get_candies() {return new ArrayList<>(_candies);}


    private Candy recursiveBinarySearchBySugarPer100(int sugar_min,int sugar_max,int start,int end){
        if (start > end) {
            throw new IllegalArgumentException("Failed to find candy in the specified sugar range.");
        }

        int middle = (start + end) / 2;
        int sugar = _candies.get(middle).getSugarPercentagePer100g();

        if (sugar >= sugar_min && sugar <= sugar_max) {
            return _candies.get(middle);
        }

        if (sugar < sugar_min) {
            return recursiveBinarySearchBySugarPer100(sugar_min, sugar_max, middle + 1, end);
        } else {
            return recursiveBinarySearchBySugarPer100(sugar_min, sugar_max, start, middle - 1);
        }
    }

    public ArrayList<Candy> sort(main.java.candy.interfaces.CandySortStrategyInterface strategy){
        ArrayList<Candy> candies = new ArrayList<>(_candies);
        strategy.sort(candies);
        return candies;
    }
}
