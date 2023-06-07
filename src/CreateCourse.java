import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreateCourse extends JFrame {

    private JLabel logoimage;
    private JRadioButton theoryRadioButton;
    private JRadioButton practicleRadioButton;
    private JComboBox comboBoxDepartment;
    private JComboBox comboBoxSemester;
    private JComboBox comboBoxLevel;
    private JButton submitButton;
    private JButton clearButton;
    private JTextField courseid;
    private JTextField courseName;
    private JTextField courseCredit;
    private JButton viewCurruntCoursesButton;
    private JButton backButton;
    private JPanel Course;
    private JLabel arrowimage;
    private JComboBox comboBoxgpa;

    CreateCourse(){
        setVisible(true);
        setSize(750, 650);
        setTitle("Course Details");
        add(Course);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(courseid.getText().isEmpty() || courseName.getText().isEmpty() || courseCredit.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Please Fill all Field !!",         //Check the fields is empty
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }else{
////////////////////////////////////////////////////////////////////////////////////
                    ButtonGroup courseType = new ButtonGroup();
                    courseType.add(theoryRadioButton);
                    courseType.add(practicleRadioButton);

                    String cId = courseid.getText();
                    String cName = courseName.getText();
                    int cCredit = Integer.parseInt(courseCredit.getText());
                    String cDep = comboBoxDepartment.getSelectedItem().toString();
                    String cSemester = comboBoxSemester.getSelectedItem().toString();
                    int cLevel = Integer.parseInt(comboBoxLevel.getSelectedItem().toString());
                    String gpastatus = comboBoxgpa.getSelectedItem().toString();
                    String cType;



                    if(theoryRadioButton.isSelected()){
                        cType = "Theory";
                    }else{
                        cType = "Practical";
                    }

                    String url = "jdbc:mysql://localhost:3306/tecmis";
                    String user = "root";
                    String password = "";

                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        //System.out.println("Connected to the MySQL database");

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("INSERT INTO course (`course_id`, `course_name`, `course_credit`, `department`, `course_type`, `semester`, `level`,`gpastatus`) VALUES " +
                                "('"+cId+"', '"+cName+"', '"+cCredit+"', '"+cDep+"', '"+cType+"', '"+cSemester+"', '"+cLevel+"' , '"+gpastatus+"'); ");
                        JOptionPane.showMessageDialog(null,
                                "Course Detail Successfully added !!");



                        con.close();
                        //System.out.println("Connection closed");
                    } catch (SQLException a) {
                        System.err.println("Error connecting to the database: " + a.getMessage());
                    }
                    //////////////////////////////////////////////
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseid.setText("");
                courseName.setText("");
                courseCredit.setText("");

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Adminpanel();
                dispose();
            }
        });
        viewCurruntCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCourse obj = new ViewCourse();
            }
        });
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));
        arrowimage = new JLabel(new ImageIcon("Arrow.png"));
    }
}
