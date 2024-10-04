package gift.interfaces;

import candy.model.Candy;

import java.sql.SQLException;

public interface GiftDAOInterface {
    void create(Candy candy)throws SQLException;
    void delete(int id)throws SQLException;
    void update(Candy candy)throws SQLException;
    void select(int id)throws SQLException;
    int selectWithMaxId()throws SQLException;

    boolean checkTable() throws SQLException;
    void createTable() throws SQLException;
}
