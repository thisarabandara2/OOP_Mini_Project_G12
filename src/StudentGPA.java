import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentGPA extends  JFrame {
    private JButton buttonback;
    private JPanel gpa;
    private JTable tablegpa;


    StudentGPA(){

        setVisible(true);
        setTitle("Course Registration");
        setSize(750,650);
        add(gpa);
        importGPA();


        //buttonback.setName();
        buttonback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Student();
                dispose();
            }
        });
    }


    private void importGPA() {

        String username = User.getUserin();

        String url = "jdbc:mysql://localhost:3306/tecmis";
        String user = "root";
        String password = "";


        try {
            Connection con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the MySQL database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gpa where stuid ='" + username + "'");


//            while (rs.next()) {
//                gpa = rs.getString("fname");
//
//
//            }

            tablegpa.setModel(DbUtils.resultSetToTableModel(rs));

            //System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("error pass here");
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

    }
}




