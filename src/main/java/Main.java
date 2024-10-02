package main.java;

import main.java.candy.DAO.CandyDAO;
import main.java.candy.model.Candy;
import main.java.gift.model.Gift;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String url = "jdbc:sqlite:program_db.sqlite";
        try {
            Connection connection = DriverManager.getConnection(url);
            CandyDAO candyDAO = new CandyDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList <Gift> gifts = new ArrayList<>();
        int selectedOption;
        boolean exit = true;
        while(exit) {
            printVariants(mainVariants);
            selectedOption = getUserInputInt();
            switch(selectedOption) {
            case 1: {
                Gift newGift = new Gift(getUserInputName(true));
                gifts.add(newGift);
                if (getUserInputShowMenu(true)) {
                    giftMenu(newGift);
                }
                break;
            }
            case 2: {
                Gift newGift = new Gift(getUserInputName(true));

                int candyCount = 0;
                System.out.print("Enter candy (must be > 0): ");
                candyCount = getUserInputInt();

                for (int i = 0; i < candyCount; i++) {
                    newGift.addCandy(new Candy.CandyFactory().generateRandom());
                }

                gifts.add(newGift);
                if (getUserInputShowMenu(true)) {
                    giftMenu(newGift);
                }
                break;
            }
            case 3:

                break;
            case 0:
                exit = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedOption);
            }
        }
    }
    static void giftMenu(Gift gift) {
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
