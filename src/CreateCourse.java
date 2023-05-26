import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                            "Please Fill all Field !!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }else{

                    
                }
            }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));
    }
}
