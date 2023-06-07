import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/tecmis";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public void writeData(String query) {

             try {
                 Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 Statement statement = connection.createStatement();
                 statement.executeUpdate(query);
                 connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
