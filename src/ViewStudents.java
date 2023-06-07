import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewStudents extends JFrame {
    private JTextField idField;
    private JButton detailsBtn;
    private JPanel myPanel;
    private JTable myTable;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    ViewStudents() {
        add(myPanel);
        setVisible(true);
        setTitle("Student Details");
        setSize(1000, 500);

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

        detailsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = idField.getText();
                if (!studentId.isEmpty()) {
                    getStudentDetails(studentId);
                }
            }
        });
    }

    private void getStudentDetails(String studentId) {
        try {
            String sql = "SELECT fname as 'First name',lname as 'Last name',address,email,birthday,contactnumber,department FROM user WHERE user_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);
            rs = pst.executeQuery();

            myTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
