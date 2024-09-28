package gift.model;

import candy.interfaces.CandySortStrategy;
import candy.model.Candy;

import java.util.ArrayList;

public class Gift {
    private static int _idCounter = 0;
    private int _id;
    private ArrayList<Candy> _candies;
    private int _totalCost;


    public Gift(ArrayList<Candy> candies) {
        _id = ++_idCounter;
        _candies = new ArrayList<Candy>(candies);
        for (Candy candy : candies) {
            _totalCost += candy.get_price();
        }
    }
    public Gift(){
        _id = ++_idCounter;
        _candies = new ArrayList<>();
    }

    public Gift(int id){
        //need use db for send query for found gift with this id
    }

    public Candy findCandyById(int id){
        for (Candy candy : _candies) {
            if(candy.get_id() == id){
                return candy;
            }
        }
        throw new IllegalArgumentException("Candy not found");
    }

    public void add_candy(Candy candy){
        _candies.add(candy);
        _totalCost += candy.get_price();
    }

    public void deleteCandy(int id){
        if (id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        Candy candy = findCandyById(id);
        _candies.remove(candy);
        _totalCost -= candy.get_price();
    }

    public void set_candies(ArrayList<Candy> candies){
        if (candies.isEmpty()){
            throw new IllegalArgumentException("candies size must be not empty");
        }
        _candies = candies;
    }

    public int get_totalCost() {return _totalCost;}
    public ArrayList<Candy> get_candies() {return new ArrayList<>(_candies);}


    private Candy recursiveBinarySearchBySugarPer100(int sugar_min,int sugar_max,int start,int end){
        if (start > end) {
            throw new IllegalArgumentException("Failed to find candy in the specified sugar range.");
        }

        int middle = (start + end) / 2;
        int sugar = _candies.get(middle).get_sugarPercentagePer100g();

        if (sugar >= sugar_min && sugar <= sugar_max) {
            return _candies.get(middle);
        }

        if (sugar < sugar_min) {
            return recursiveBinarySearchBySugarPer100(sugar_min, sugar_max, middle + 1, end);
        } else {
            return recursiveBinarySearchBySugarPer100(sugar_min, sugar_max, start, middle - 1);
        }
    }

    public ArrayList<Candy> sort(CandySortStrategy strategy){
        ArrayList<Candy> candies = new ArrayList<>(_candies);
        strategy.sort(candies);
        return candies;
    }
}
