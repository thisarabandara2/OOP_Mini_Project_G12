import java.sql.*;
import javax.swing.JOptionPane;

public class User {

    private static String userin;
    private static String passwordin;
    public User(String inputuser, String inputpassword) {
        userin = inputuser;
        passwordin = inputpassword;
        mysqlQuary();


    }

    public User() {
    }

    private void mysqlQuary() {
        String url = "jdbc:mysql://localhost:3306/tecmis";
        String user = "root";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the MySQL database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                String id = rs.getString("user_id");
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
                selectUser();
            }else{
                JOptionPane.showMessageDialog(null, "Incorrect Password");
            }

        }else{
            JOptionPane.showMessageDialog(null, "This User Not Found");
        }
        }

    public static String getUserin(){

        return userin;
    }
    private void selectUser() {
        checkType();

    }

    private void checkType() {
        String url = "jdbc:mysql://localhost:3306/tecmis";
        String user = "root";
        String password = "root";


        try {
            Connection con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the MySQL database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT user_type FROM user where user_id ='"+userin+"'");



           while (rs.next()) {
               String type = rs.getString("user_type");
               if(type.equals("student")){
                   Student std = new Student();
                } else if (type == "lecturer") {
                    //Lecturer lec = new Lecturer();
               } else if (type == "tecnicle officer") {
                    //TecOfficer tecofficer = new TecOfficer();
                }
           }

            con.close();
            //System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("error pass here");
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

    }

    public class Main {
        public static void main(String[] args) {

        }
    }

}
