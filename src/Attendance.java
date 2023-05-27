import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Attendance extends JFrame {
    private JTextField textstID;
    private JTable table;
    private JButton searchButton;
    private JPanel pannel;


    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    Attendance() {
        add(pannel);
        setVisible(true);
        setTitle("Attendance");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                String student_id = textstID.getText();
                if (!student_id.isEmpty()) {
                    getAttendance(student_id);
            }
        }
    });
}

    private void getAttendance(String  student_id) {
        try {
            String sql = "SELECT attendance_id,date,time,course_id,student_id FROM attendance WHERE student_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, student_id);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"attendance_id","date","time","course_id","student_id"},
                    0);

            // Add rows to the model
            while (rs.next()) {
                String attendanceId = rs.getString("attendance_id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String courseid = rs.getString("course_id");
                String studentId = rs.getString("student_id");
                model.addRow(new Object[]{attendanceId,date,time,courseid,studentId});
            }


            table.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}