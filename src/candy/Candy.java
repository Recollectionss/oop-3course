package candy;

public class Candy {
    static int _idCounter;
    private final int _id;
    private String _name;
    private CandyType _candyType;
    private int _sugar;
    private int _price;
    private int _sugarPercentagePer100g;
    private int _weight;


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
        this._sugarPercentagePer100g =  (int) Math.ceil((double) this._weight /this._sugar);
    }

    public int get_id() {return _id;}

    public String get_name() {return _name;}

    public CandyType get_candyType() {return _candyType;}
    public int get_sugar() {return _sugar;}
    public int get_price() {return _price;}
    public int get_sugarPercentagePer100g() {return _sugarPercentagePer100g;}
    public int get_weight() {return _weight;}

    public void set_name(String name) {
        if (name.length() <= 5) {
            throw new IllegalArgumentException("Candy's name must have at least 5 characters");
        }
        this._name = _name;
    }

    public void set_candyType(CandyType candyType) {
        this._candyType = candyType;
    }

    public void set_sugar(int sugar) {
        if (sugar <= 0) {
            throw new IllegalArgumentException("Candy's sugar must have a positive value");
        }
        this._sugar = sugar;
        calc_sugarPercentagePer100g();
    }

    public void set_price(int price) {
        if (price <= 1) {
            throw new IllegalArgumentException("Candy's price must have a positive value");
        }
        this._price = price;
    }

    public void set_weight(int weight) {
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



        public CandyBuilder setName(String name ){
            if (name.equals("default")){
                name = names[(int) (Math.random() * names.length)];
            }
            this._name = name;
            return this;
        }

        public CandyBuilder setCandyType(CandyType _candyType) {
            if (_candyType == CandyType.DEFAULT){
                _candyType = CandyType.HARD_CANDY;
            }
            this._candyType = _candyType;
            return this;
        }

        public CandyBuilder setSugar(int sugar) {
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

        public CandyBuilder setPrice(int _price) {
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
        public CandyBuilder setWeight(int weight) {
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
            return new CandyBuilder().setName("default").setCandyType(CandyType.DEFAULT).setWeight(0).setSugar(0).setPrice(0).build();
        }
    }


    public enum CandyType {
        DEFAULT,
        CHOCOLATE,
        CARAMEL,
        GUMMY,
        HARD_CANDY,
        LOLLIPOP
    }
}