import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableOneView {
    private Connection conn;

    public TableOneView(Connection conn) {
        this.conn = conn;
    }

    public void displayTable(String query) {

        try {
            // Create a PreparedStatement to execute a SELECT query on the table
            PreparedStatement stmt = conn.prepareStatement(query);

            // Execute the query and get the ResultSet
            ResultSet rs = stmt.executeQuery();

            // Display the results
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

            // Close the ResultSet and PreparedStatement
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
