import javax.swing.*;
import java.sql.*;

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

    private void mysqlQuary() { // Database connection and get data
        String url = "jdbc:mysql://localhost:3306/tecmis";
        String user = "root";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the MySQL database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                String id = rs.getString("user_id");
                String pw = rs.getString("password");
                if(id.equals(userin)){
                    selectUser(id,pw);
                }

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
                JOptionPane.showMessageDialog(null,"Incorrect Password!!","Try again",JOptionPane.ERROR_MESSAGE);
            }

        }else{
            JOptionPane.showMessageDialog(null,"This User Not Found","Try again",JOptionPane.ERROR_MESSAGE);
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
        String password = "";


        try {
            Connection con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the MySQL database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usertype FROM user where user_id ='"+userin+"'");
            System.out.println(userin);



           while (rs.next()) {
               String type = rs.getString("usertype");
               if(type.equals("Student")){
                   Student std = new Student();
                } else if (type.equals("Lecturer")) {
                   System.out.println("run lecture");
                   Lecture lec=new Lecture();
               } else if (type.equals("Technical officer")) {
                    //TecOfficer tecofficer = new TecOfficer(); add technicle officer part
                }
           }

            con.close();
            //System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("error pass here");
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

    }



}
