import java.sql.*;

public class User {

    private static String userin;
    private static String passwordin;
    public User(String inputuser, String inputpassword) {
        userin = inputuser;
        passwordin = inputpassword;
        databaseCon();


    }

    private void databaseCon() {
        String url = "jdbc:mysql://localhost:3306/tecmis";
        String user = "root";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the MySQL database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                String id = rs.getString("user id");
                String pw = rs.getString("password");
                selectUser(id,pw);

            }

            con.close();
            //System.out.println("Connection closed");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }


    private void selectUser(String id, String pw) {
        if (id.equals(userin)) {
            if (pw.equals(passwordin)){
                //home page
            }else{
                System.out.println("Password Incorrect");
            }

        }
    }

    public class Main {
        public static void main(String[] args) {

        }
    }

}
