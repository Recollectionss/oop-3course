package main.SQLiteDB.interfaces;

import java.sql.Connection;

public interface DB_Interface {
    Connection connection = null;

    Connection getConnection();
    void closeConnection();
    void create();
    void delete();
    void update();
    void read();

    void createTableIfNotExist();
    void createDefaultData();
}
