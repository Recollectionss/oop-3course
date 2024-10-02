package main.java.SQLiteDB.interfaces;

public interface DB_Interface {

    void getConnection();
    void closeConnection();
    void create();
    void delete();
    void update();
    void read();

    void createTableIfNotExist();
    void createDefaultData();
}
