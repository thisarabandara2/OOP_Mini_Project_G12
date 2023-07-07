import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewEligibal extends  JFrame{
    private JComboBox comboBox1;
    private JTable table1;
    private JLabel eligibalview;
    private JButton viewButton;
    private JPanel jpane;
    private JButton GPAButton;
    String lecid="lec1";

    Connection conn;

//    public static void main(String[] args) {
//        ViewEligibal ve=new ViewEligibal();
//    }
public ViewEligibal() {

    setVisible(true);
    setTitle("View Eligibility");
    setSize(750,650);
    add(jpane);




    viewButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ViewEli();
        }
    });


    GPAButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
}

    private void ViewEli() {

        String username = User.getUserin();

        String url = "jdbc:mysql://localhost:3306/tecmis";
        String user = "root";
        String password = "";


        try {

            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM final_eligibal where subject_id ='" + comboBox1.getSelectedItem().toString() + "'");


            table1.setModel(DbUtils.resultSetToTableModel(rs1));

        } catch (SQLException e) {
            System.out.println("error pass here");
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

    }
}


