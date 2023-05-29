import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Lecture extends JFrame{
    private JButton updateProfileButton;
    private JButton courseMaterealButton;
    private JButton maintainMarksButton;
    private JButton studentDetailsButton;
    private JButton studentEligibilityButton;
    private JButton MarksButton;
    private JButton studentAttendanceButton;
    private JButton noticesButton;
    private JPanel Lecture;

    public Lecture() {
        setContentPane(Lecture);
        setTitle("Lecture");
        setBounds(600, 200, 600, 300);
        setVisible(true);
        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        Lectureupdate update=new Lectureupdate();
            }
        });
        studentEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        courseMaterealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AddMaterials met = new AddMaterials();
            }
        });
        MarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Marks marks=new Marks();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        studentAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        studentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStudents Vstu = new ViewStudents();
            }
        });
        noticesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeeNotices notice = new SeeNotices();
            }
        });

    }
}