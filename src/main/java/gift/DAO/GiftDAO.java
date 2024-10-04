package gift.DAO;

import candy.model.Candy;
import gift.interfaces.GiftDAOInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class GiftDAO implements GiftDAOInterface {
    final static String _createQuery ="INSERT INTO Gift(name, total_price) VALUES (?,?,?)";
    final static String _deleteQuery = "DELETE FROM Gift WHERE id = ?";
    final static String _updateQuery = "UPDATE Gift SET name = ?, total_price = ? WHERE id = ?";
    final static String _selectQuery = "SELECT * FROM Gift WHERE id = ?";
    final static String _checkTableExistQuery = "SELECT id FROM Gift";
    final static String _selectMaxIdQuery = "SELECT id FROM Gift ORDER BY id DESC LIMIT 1";
    final static String _createTableQuery =
            "CREATE TABLE IF NOT EXISTS Gift(" +
            "id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "total_price INTEGER," +
            ");";

    Connection _connection = null;

    public GiftDAO(Connection connection) {
        if(connection == null) {
            throw new NullPointerException("connection is null");
        }
        this._connection = connection;

        try {
            if (!checkTable()){
                createTable();
                System.out.println("Table created");
            }else{
                System.out.println("Table already exists");
            }
            System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
