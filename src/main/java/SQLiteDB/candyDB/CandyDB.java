package main.java.SQLiteDB.candyDB;

import main.java.SQLiteDB.interfaces.DB_Interface;

import java.sql.Connection;
import java.sql.DriverManager;

public class CandyDB implements DB_Interface {
    Connection connection = null;

    @Override
    public void getConnection() {
        try {
            String url = "jdbc:sqlite:/Users/ilagoncarenko/Downloads/ALL/Work/InteliJ/oop-3course-lab-1/program_db.sqlite";

            connection = DriverManager.getConnection(url);

            System.out.println("Connected to database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {

    }

    @Override
    public void create() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void read() {

    }

    @Override
    public void createTableIfNotExist() {

    }

    @Override
    public void createDefaultData() {

    }
}
