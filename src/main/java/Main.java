import DAO.MainDAO.BaseDAO;
import candy.model.Candy;
import gift.model.Gift;

import java.sql.SQLException;

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

    static BaseDAO dao = new BaseDAO();
    static Gift gift = null;
    static Candy candy = null;

    public static void main(String[] args) {
//        try {
//            gift = dao.openConnection().getGiftWithCandy(1);
//            dao.closeConnection();
//            gift.printGiftInfo();
//            for(Candy candy : gift.get_candies()){
//                candy.printCandyInfo();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Candy candy = Candy.CandyFactory.generateRandom();
        candy.printCandyInfo();
        int selectedOption;
        boolean exit = true;
        while(exit) {
            printVariants(mainVariants);
            selectedOption = getUserInputInt();
            switch(selectedOption) {
            case 1: {
                gift = new Gift(getUserInputName(true));
                try {
                    dao.openConnection().getGiftDAO().create(gift);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                gift.printGiftInfo();
                if (getUserInputShowMenu(true)) {
                    giftMenu();
                }
                break;
            }
            case 2: {
                gift = new Gift(getUserInputName(true));

                int candyCount = 0;
                System.out.print("Enter candy (must be > 0): ");
                candyCount = getUserInputInt();

                for (int i = 0; i < candyCount; i++) {
                    gift.addCandy(Candy.CandyFactory.generateRandomForGift(gift.getId()));
                }

                gift.printGiftInfo();
                if (getUserInputShowMenu(true)) {
                    giftMenu();
                }
                break;
            }
            case 3:
                int giftId = getUserInputInt();
                try {
                    gift = dao.openConnection().getGiftDAO().select(giftId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                giftMenu();
                break;
            case 0:
                exit = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedOption);
            }
        }
    }
    static void giftMenu() {
        int variant;
        boolean exit = true;
        while(exit) {
            printVariants(giftVariants);
            variant = Integer.parseInt(System.console().readLine());
            switch(variant) {
                case 1:
                    addCandyMenu();
                    break;
                case 2:{
                    int id = getUserInputInt();

                    break;
                }
                case 3:{

                    break;
                }case 4:{

                    break;
                }case 5:{

                    break;
                }case 6:{

                    break;
                }case 7:{

                    break;
                }
                case 9:
                    exit = false;
                    break;
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

    private static String getUserInputName(boolean variant){
        String variantName = variant ? "Gift" : "Candy";
        System.out.print("Please enter " + variantName + " name: ");

        String name = "";

        boolean exit = false;
        while (!exit) {
            name = System.console().readLine();
            if (!name.isEmpty()){
                exit = true;
            }else {
                System.out.println("Please enter a valid name: ");
            }
        }

        return name;
    }

    private static boolean getUserInputShowMenu(boolean variant){
        String variantName = variant ? "Gift" : "Candy";
        System.out.print("Show menu about this " + variantName + " :  y/n");
        String input = "";

        boolean exit = false;
        while (!exit) {
            input = System.console().readLine();
            if (input.equals("y") || input.equals("n")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }

        return input.equals("y");
    }

    private static int getUserInputInt(){
        int input = 0;
        boolean exit = false;
        while (!exit) {
            try {

                input = Integer.parseInt(System.console().readLine());
                if (input < 0){
                    System.out.println("Please enter a valid number.( => 0)");
                }else {
                    exit = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        return input;
    }
}
