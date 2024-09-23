package gift;

import candy.Candy;

import java.util.ArrayList;

public class Gift {
    private static int _idCounter = 0;
    private int _id;
    private ArrayList<Candy> _candies;
    private int _totalCost;


    public Gift(ArrayList<Candy> candies) {
        _id = ++_idCounter;
        _candies = GiftSorting.sortBySugar(candies);
        for (Candy candy : candies) {
            _totalCost += candy.get_price();
        }
    }
    public Gift(){_id = ++_idCounter;}

    public Gift(int id){
        //need use db for send query for found gift with this id
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

    public Candy findCandyById(int id){
        for (Candy candy : _candies) {
            if(candy.get_id() == id){
                return candy;
            }
        }
        throw new IllegalArgumentException("Candy not found");
    }
    public Candy findCandyBySugar(int sugar_min,int sugar_max){
        return recursiveBinarySearch(sugar_min, sugar_max, 0, _candies.size());
    }
    public Candy findCandyByPrice(int price){return _candies.getFirst();}
    public Candy findCandyByName(int price){return _candies.getFirst();}
    public Candy findCandyByType(int price){return _candies.getFirst();}

    public void set_candies(ArrayList<Candy> candies){
        if (candies.isEmpty()){
            throw new IllegalArgumentException("candies size must be not empty");
        }
        _candies = candies;
    }

    public int get_totalCost() {return _totalCost;}
    public ArrayList<Candy> get_candies() {return _candies;}

    private Candy recursiveBinarySearch(int sugar_min,int sugar_max,int start,int end){
        if (start > end) {
            throw new IllegalArgumentException("Failed to find candy in the specified sugar range.");
        }

        int middle = (start + end) / 2;
        int sugar = _candies.get(middle).get_sugar();

        if (sugar >= sugar_min && sugar <= sugar_max) {
            return _candies.get(middle);
        }

        if (sugar < sugar_min) {
            return recursiveBinarySearch(sugar_min, sugar_max, middle + 1, end);
        } else {
            return recursiveBinarySearch(sugar_min, sugar_max, start, middle - 1);
        }
    }
}
