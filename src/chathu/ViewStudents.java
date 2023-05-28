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
            String sql = "SELECT * FROM student WHERE student_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, studentId);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"student_id", "department_name", "user_id", "course_name", "course_credit"},
                    0);

            // Add rows to the model
            while (rs.next()) {
                String id = rs.getString("student_id");
                String dep_name = rs.getString("department_name");
                String user_id = rs.getString("user_id");
                String course_name = rs.getString("course_name");
                String course_credit = rs.getString("course_credit");
                model.addRow(new Object[]{id, dep_name, user_id, course_name,course_credit);
            }

                myTable.setModel(model);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }


        public static void main(String[] args) {
            ViewStudents viewStudents = new ViewStudents();
        }
    }
