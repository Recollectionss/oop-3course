package gift;

import candy.model.Candy;

import java.util.ArrayList;
import java.util.Comparator;

public class Gift {
    private static int _idCounter = 0;
    private int _id;
    private ArrayList<Candy> _candies;
    private int _totalCost;
    private enum sortType{
        SUGAR_PER_100,
        NAME,
        ID,
        PRICE,
        WEIGHT,
        CANDY_TYPE,
    };


    public Gift(ArrayList<Candy> candies) {
        _id = ++_idCounter;
        _candies = sort(sortType.SUGAR_PER_100);
        for (Candy candy : candies) {
            _totalCost += candy.get_price();
        }
    }
    public Gift(){_id = ++_idCounter;}

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
    public Candy findCandyBySugar(int sugar_min,int sugar_max){
        return recursiveBinarySearchBySugarPer100(sugar_min, sugar_max, 0, _candies.size());
    }

    public Candy findCandyByPrice(int price){return _candies.getFirst();}
    public Candy findCandyByName(int price){return _candies.getFirst();}
    public Candy findCandyByType(int price){return _candies.getFirst();}

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

    public ArrayList<Candy> get_candies() {return _candies;}
    public ArrayList<Candy> get_sorted_candies_by_name() {return sort(sortType.NAME);}
    public ArrayList<Candy> get_sorted_candies_by_id() {return sort(sortType.ID);}
    public ArrayList<Candy> get_sorted_candies_by_weight() {return sort(sortType.WEIGHT);}
    public ArrayList<Candy> get_sorted_candies_by_price() {return sort(sortType.PRICE);}
    public ArrayList<Candy> get_sorted_candies_by_type() {return sort(sortType.CANDY_TYPE);}



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
    private ArrayList<Candy> sort (sortType sortType){
        ArrayList<Candy> sortedCandies = new ArrayList<Candy>(_candies);
        switch (sortType){
            case SUGAR_PER_100:
                sortedCandies.sort(compareBySugarPer100());
                break;
            case NAME:
                sortedCandies.sort(compareByName());
                break;
            case ID:
                sortedCandies.sort(compareById());
                break;
            case PRICE:
                sortedCandies.sort(compareByPrice());
                break;
            case WEIGHT:
                sortedCandies.sort(compareByWeight());
                break;
            case CANDY_TYPE:
                sortedCandies.sort(compareByCandyType());
                break;
        }
        return sortedCandies;
    }

    private Comparator<Candy> compareBySugarPer100(){return Comparator.comparing(Candy::get_sugarPercentagePer100g);}
    private Comparator<Candy> compareById(){return Comparator.comparing(Candy::get_id);}
    private Comparator<Candy> compareByName(){return Comparator.comparing(Candy::get_name);}
    private Comparator<Candy> compareByPrice(){return Comparator.comparing(Candy::get_price);}
    private Comparator<Candy> compareByWeight(){return Comparator.comparing(Candy::get_weight);}
    private Comparator<Candy> compareByCandyType(){return Comparator.comparing(Candy::get_candyType);}
}
