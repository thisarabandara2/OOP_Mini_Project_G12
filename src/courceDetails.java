import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class courceDetails extends JFrame {

    private JButton Sbutton;
    private JPanel pannel;
    private JTable table1;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    public courceDetails() {
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

        Sbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = textstudentid.getText();//pakooo method eka add karahan
                if (!student_id.isEmpty()) {
                    getcourceDetails(student_id);
                }


            }
        });
    }

    private void getcourceDetails(String studentId) {
        try {
            String sql = "SELECT student_id as Student ID, department_name as Department Name,course_id as Course ID,course_name as Course Name FROM student WHERE student_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);

            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"student_id","department_name", "course_id,course_name"},
                    0);

            // Add rows to the model
            while (rs.next()) {
                studentId = rs.getString("student_id");
                String departmentName=rs.getString("department_name");
                String courseId = rs.getString("course_id");
                String courseName=rs.getString("course_name");

                model.addRow(new Object[]{studentId, departmentName, courseId,courseName});
            }

            table1.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("courceDetails");
        frame.setContentPane(new courceDetails().pannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
