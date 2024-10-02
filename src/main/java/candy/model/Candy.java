package main.java.candy.model;
import main.java.candy.enums.CandyType;

public class Candy implements main.java.candy.interfaces.CandyInterface {
    private static int _idCounter = 0;
    private final int _id;
    private String _name;
    private CandyType _candyType;
    private int _sugar;
    private int _price;
    private int _sugarPercentagePer100g;
    private int _weight;

    public void printCandyInfo(){
        System.out.println("//////////////////////////");
        System.out.println("ID: " + _id);
        System.out.println("Name: " + _name);
        System.out.println("CandyType: " + _candyType);
        System.out.println("Sugar: " + _sugar);
        System.out.println("Price: " + _price);
        System.out.println("SugarPercentagePer100g: " + _sugarPercentagePer100g);
        System.out.println("Weight: " + _weight);
        System.out.println("//////////////////////////");
    }


    private Candy(String name, CandyType candyType, int sugar, int price, int weight) {
        _id = ++_idCounter;
        _name = name;
        _candyType = candyType;
        _sugar = sugar;
        _price = price;
        _weight = weight;
        calc_sugarPercentagePer100g();
    }

    private void calc_sugarPercentagePer100g() {
        this._sugarPercentagePer100g = (int) Math.ceil((double) this._sugar / this._weight * 100);
    }



    public int getId() {return _id;}
    public String getName() {return _name;}
    public CandyType getCandyType() {return _candyType;}
    public int getSugar() {return _sugar;}
    public int getPrice() {return _price;}
    public int getSugarPercentagePer100g() {return _sugarPercentagePer100g;}
    public int getWeight() {return _weight;}

    public void setName(String name) {
        if (name.length() <= 5) {
            throw new IllegalArgumentException("Candy's name must have at least 5 characters");
        }
        this._name = _name;
    }

    public void setCandyType(CandyType candyType) {
        this._candyType = candyType;
    }

    public void setSugar(int sugar) {
        if (sugar <= 0) {
            throw new IllegalArgumentException("Candy's sugar must have a positive value");
        }
        this._sugar = sugar;
        calc_sugarPercentagePer100g();
    }

    public void setPrice(int price) {
        if (price <= 1) {
            throw new IllegalArgumentException("Candy's price must have a positive value");
        }
        this._price = price;
    }

    public void setWeight(int weight) {
        if (weight <= 1) {
            throw new IllegalArgumentException("Candy's weight must have a positive value");
        }
        this._weight = weight;
        calc_sugarPercentagePer100g();
    }


    public static class CandyBuilder {
        final String[] names = {
                "1",
                "2",
                "3",
                "4",
                "5",
        };

        String _name = "default";
        CandyType _candyType = CandyType.DEFAULT;
        int _sugar = 0;
        int _price = 0;
        int _weight = 0;



        public CandyBuilder withName(String name ){
            if (name.equals("default")){
                name = names[(int) (Math.random() * names.length)];
            }
            this._name = name;
            return this;
        }

        public CandyBuilder withCandyType(CandyType _candyType) {
            if (_candyType == CandyType.DEFAULT){
                _candyType = CandyType.HARD_CANDY;
            }
            this._candyType = _candyType;
            return this;
        }

        public CandyBuilder withSugar(int sugar) {
            if (sugar == 0){
                this._sugar = (int)(Math.random()*100) + 1;
                return this;
            }
            if (_sugar < 0){
                throw new IllegalArgumentException("Candy's sugar must have a positive value");
            }
            this._sugar = sugar;
            return this;
        }

        public CandyBuilder withPrice(int _price) {
            if (_price == 0){
                this._price = (int)(Math.random()*100) + 1;
                return this;
            }
            if (_price < 0){
                throw new IllegalArgumentException("Candy's price must have a positive value");
            }
            this._price = _price;
            return this;
        }
        public CandyBuilder withWeight(int weight) {
            if (weight == 0){
                this._weight = (int)(Math.random()*100) + 1;
            }
            if(weight < 0){
                throw new IllegalArgumentException("Candy's weight must have a positive value");
            }
            this._weight = weight;
            return this;
        }

        public Candy build(){
            return new Candy(_name, _candyType, _sugar, _price, _weight);
        }
    }
    public static class CandyFactory{
        public Candy generateRandom(){
            return new CandyBuilder().withName("default").withCandyType(CandyType.DEFAULT).withWeight(0).withSugar(0).withPrice(0).build();
        }
    }
}