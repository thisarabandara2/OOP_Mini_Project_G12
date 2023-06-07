import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Viewmedicals extends JFrame{
    private JTextField textdtid;
    private JTable table1;
    private JButton searchButton;
    private JPanel panel;
    private JButton viewBtn;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;


    Viewmedicals() {
        add(panel);
        setVisible(true);
        setTitle("Student Details");
        setSize(1000, 600);

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
            String studentId = textdtid.getText();
            if (!studentId.isEmpty()) {
                getMedicalDetails(studentId);
            }
        }
    });

        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table1.getSelectedRow(); //0
                String filePath = (String) table1.getValueAt(selectedRow, 6);

                    try {
                        Desktop.getDesktop().open(new File(filePath));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Failed to open the file.", "File Error", JOptionPane.ERROR_MESSAGE);
                    }

            }
        });


    }

    private void getMedicalDetails(String studentId) {
        try {
            String sql = "SELECT tg as 'Student ID',level,semester,start_date,end_date,subjects,medFile as 'Medical File' FROM medical WHERE tg= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);
            rs = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}

