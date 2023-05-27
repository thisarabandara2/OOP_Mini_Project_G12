import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class courceDetails extends JFrame {

    private JButton Sbutton;
    private JPanel pannel;
    private JTable table1;
    private JButton registerCourseButton;
    private JScrollPane scll;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    private String CourseName;
    private  int CoursCredit;
    String courseId;

    public courceDetails() {
        setVisible(true);
        add(pannel);
        setTitle("Course Details");
        setSize(750,650);



        DBConnect dbConnect = new DBConnect();
        try {
            conn = dbConnect.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        Sbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = User.getUserin();
                getcourceDetails(studentId);


            }
        });
        registerCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentCourseRegister stu = new StudentCourseRegister();
            }
        });
    }

    private void getcourceDetails(String studentId) {

        try {
            String sql = "SELECT student_id,department_name,course_id FROM student WHERE student_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);

            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"student_id","department_name","course_id"},
                    0);

            // Add rows to the model
            while (rs.next()) {
                studentId = rs.getString("student_id");
                String departmentName=rs.getString("department_name");
                courseId = rs.getString("course_id");


                model.addRow(new Object[]{studentId, departmentName, courseId});
            }

            table1.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
