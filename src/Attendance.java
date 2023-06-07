import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Attendance extends JFrame {

    private JTable table;
    private JButton searchButton;
    private JPanel pannel;


    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    String student_id;

    Attendance() {
        add(pannel);
        setVisible(true);
        setTitle("Attendance");
        setSize(400, 350);

        DBConnect dbConnect = new DBConnect();
        try {
            conn = dbConnect.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student_id = User.getUserin();

                    getAttendance();

        }
    });
}

    private void getAttendance() {
        try {
            String sql = "SELECT attendance_id,date,time,course_id FROM attendance WHERE student_id = '"+ student_id+"'   ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}