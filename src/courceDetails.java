import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class courceDetails extends JFrame {

    private JPanel pannel;
    private JTable table1;
    private JButton registerCourseButton;
    private JScrollPane scll;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;


    String studentId = User.getUserin();;


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

        getcourceDetails();

        registerCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentCourseRegister stu = new StudentCourseRegister();
            }
        });

    }


    public void getcourceDetails() {
        try {
            String sql = "SELECT student_id , department_name ,course_id,course_name ,course_credit FROM student where student_id = '"+studentId+"'   ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
