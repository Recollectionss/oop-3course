import candy.Candy;

public class Main {
    final static String[] mainVariants ={
            "\n1. Create empty gift",
            "2. Create fill in gift",
            "3. Find gift",
            "0. Exit\n",
    };
    final static String[] giftVariants ={
            "Gift menu",
            "1. Add candy",
            "2. Find candy (by id)",
            "3. Remove candy(need find by id)",
            "4. Change info about candy(empty input stay default values)",
            "5. View all candy's",
            "6. View sorted candy",
            "7. Delete gift",
            "9. Exit from this menu",
    };
    final static String[] addCandyVariants ={
            "Add candy :",
            "1. Custom",
            "2. Generate random candy",
            "9. Exit from this menu",
    };
    final static String[] sortVariants ={
            "Sort by:",
            "1. Id",
            "2. Sugar",
            "3. Name",
            "4. Type",
            "9. Exit from this menu",
    };

    public static void main(String[] args) {
        int variant;
        boolean exit = true;
        while(exit) {
            printVariants(mainVariants);
            variant = Integer.parseInt(System.console().readLine());
            switch(variant) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                exit = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + variant);
            }
            giftMenu();
        }
    }
    static void giftMenu(){
        int variant;
        boolean exit = true;
        while(exit) {
            printVariants(giftVariants);
            variant = Integer.parseInt(System.console().readLine());
            switch(variant) {
                case 1:
                    addCandyMenu();
                    break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 9: break;
            }
        }
    }
    static void addCandyMenu(){
        int variant;
        boolean exit = true;
        while(exit) {
            printVariants(addCandyVariants);
            variant = Integer.parseInt(System.console().readLine());
            switch(variant) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 9: break;
            }
        }
    }

    static void printVariants(String[] variant){
        for (String s : variant) {
            System.out.println(s);
        }
    }
}
