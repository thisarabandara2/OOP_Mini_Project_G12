import java.sql.*;

public class DatabaseConnection {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/tecmis";
    private final String user = "root";
    private final String password = "";
 public void DatabaseConnection() {
            // Initialize the database connection
     String url = this.url;
    String user = this.user;
    String password = this.password;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
 }
    public void writeData(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public Connection getConnection() {
        return conn;
    }}
































































































































































