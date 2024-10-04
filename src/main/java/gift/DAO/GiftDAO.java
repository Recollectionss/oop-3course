package gift.DAO;

import candy.model.Candy;
import gift.interfaces.GiftDAOInterface;

import java.sql.SQLException;

public class GiftDAO implements GiftDAOInterface {
    @Override
    public void create(Candy candy) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Candy candy) throws SQLException {

    }

    @Override
    public void select(int id) throws SQLException {

    }

    @Override
    public int selectWithMaxId() throws SQLException {
        return 0;
    }

    @Override
    public boolean checkTable() throws SQLException {
        return false;
    }

    @Override
    public void createTable() throws SQLException {

    }
}
