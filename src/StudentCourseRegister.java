import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentCourseRegister extends JFrame {
    private JButton backButton;
    private JTable courseTable;
    private JComboBox comboBoxSemester;
    private JButton searchButton;
    private JButton registerSelectedCourseButton;
    private JPanel CourseRegistation;
    private JComboBox comboBoxlevel;
    private JScrollPane scll;
    private JLabel txtt;
    private JLabel slecttxt;
    private JLabel sentxt;
    private JLabel headline;
    Connection con;

    PreparedStatement pst;
    String Dep;

    String username;
    int level;
    String Sem;
    String CourseID;
    String CourseName;
    int CourseCredit;

    public StudentCourseRegister() {
        System.out.println("Run construct");
        setVisible(true);
        setTitle("Course Registration");
        setSize(750,650);
        add(CourseRegistation);
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             Sem = comboBoxSemester.getSelectedItem().toString();
            level = Integer.parseInt(comboBoxlevel.getSelectedItem().toString());
            username = User.getUserin();
            dbconnect();
            table_lord();


        }
    });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new courceDetails();
            }
        });
        registerSelectedCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTableData();

                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs1 = stmt.executeQuery("SELECT department FROM user where user_id ='"+username+"'");



                    while (rs1.next()) {
                        Dep = rs1.getString("department");

                    }

                    //System.out.println("Connection closed");
                } catch (SQLException a) {
                    System.out.println("error pass here");
                    System.err.println("Error connecting to the database: " + a.getMessage());
                }

                try {

                    Statement stmt = con.createStatement();
                    System.out.println(CourseID+CourseName+CourseCredit);
                    stmt.executeUpdate("INSERT INTO student (`student_id`, `department_name`, `user_id`, `course_id`, `course_name`, `course_credit`) VALUES " +
                            "('"+username+"', '"+Dep+"', '"+username+"', '"+CourseID+"', '"+CourseName+"', "+CourseCredit+");");

                    JOptionPane.showMessageDialog(null,
                            "Course Detail Successfully added !!");

                    con.close();
                    //System.out.println("Connection closed");
                } catch (SQLException a) {
                    JOptionPane.showMessageDialog(null,
                            "Can't Register !! , System Error!!");
                    System.err.println("Error connecting to the database: " + a.getMessage());
                }


            }
        });
    }

    private void getTableData() {
        int selectedRow = courseTable.getSelectedRow();
        CourseID = courseTable.getValueAt(selectedRow,0).toString();
        CourseName = courseTable.getValueAt(selectedRow,1).toString();
        CourseCredit = Integer.parseInt(courseTable.getValueAt(selectedRow,2).toString());

    }

    public void dbconnect(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/tecmis", "root","");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    public void table_lord(){
        try
        {

            pst = con.prepareStatement("select c.course_id as 'Course ID',c.course_name as 'Course Name',c.course_credit as 'Credit' from course c " +
                    "join user u ON u.department = c.department where u.user_id = '"+username+"' and c.level = '"+level+"' and c.semester = '"+Sem+"';");

            ResultSet rs = pst.executeQuery();
            courseTable.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}
