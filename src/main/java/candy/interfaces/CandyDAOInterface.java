package main.java.candy.interfaces;

import main.java.candy.model.Candy;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CandyDAOInterface {

    void create(Candy candy)throws SQLException;
    void delete(int id)throws SQLException;
    void update(Candy candy)throws SQLException;
    void select(int id)throws SQLException;
    ArrayList<Candy> selectAll()throws SQLException;

    boolean checkTable() throws SQLException;
    void createTable() throws SQLException;
}
