import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewCourse extends JFrame {
    private JPanel ViewCourse;
    private JLabel logoimage;
    private JButton backButton;
    private JTable table1;
    private JComboBox comboBoxLevel;
    private JComboBox comboBoxSem;
    private JComboBox comboBoxDip;
    private JButton searchButton;
    private JButton clearButton;
    private JButton deleteSelectedCourseButton;

    Connection con;
    PreparedStatement pst;

    int level;
    String Sem;
    String Dip;
    String CourseID;


    public ViewCourse() {

        setVisible(true);
        setSize(750, 650);
        setTitle("View Courses ");
        add(ViewCourse);
        connect();
        full_table_lord();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Integer.parseInt(comboBoxLevel.getSelectedItem().toString());
                Sem = comboBoxSem.getSelectedItem().toString();
                Dip = comboBoxDip.getSelectedItem().toString();
                table_lord();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        deleteSelectedCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTableData();

                try {
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("delete from course where course_id = '"+CourseID+"';");
                    JOptionPane.showMessageDialog(null,
                            "Course Deleted Successfully  !!");

                    
                    //System.out.println("Connection closed");
                } catch (SQLException a) {
                    System.err.println("Error connecting to the database: " + a.getMessage());
                }
                full_table_lord();

            }
        });
    }

    private void getTableData() {
        int selectedRow = table1.getSelectedRow();
        CourseID = table1.getValueAt(selectedRow,0).toString();

    }

    public void table_lord(){
        try
        {

            pst = con.prepareStatement("select course_id as 'Course ID',course_name as 'Course Name',course_credit as 'Credit',level as 'Level',department as 'Department',semester as 'Semester',course_type as 'Type' from course " +
                    "where level = '"+level+"' and semester = '"+Sem+"' and department = '"+Dip+"';");

            System.out.println(Dip);
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void full_table_lord(){
        try
        {

            pst = con.prepareStatement("select course_id as 'Course ID',course_name as 'Course Name',course_credit as 'Course Credit',level as 'Level',department as 'Department',semester as 'Semester',course_type as 'Course Type' from course;");

            System.out.println(Dip);
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void connect(){
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
    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));
    }
}
