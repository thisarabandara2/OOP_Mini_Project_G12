import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Adminpanel extends JFrame {
    private JButton userProfilesButton;
    private JButton norticesButton;
    private JButton cousersButton;
    private JButton timeTablesButton;
    private JPanel adminpanel;
    private JLabel logoimage;
    private JButton logOutButton;

    public Adminpanel(){

        setVisible(true);
        setSize(750, 650);
        setTitle("Admin Panel");
        add(adminpanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        userProfilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createUserProfile();
            }
        });
        norticesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNotice();
            }
        });
        cousersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCourse();
            }
        });
        timeTablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTimeTable();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createTimeTable() {
            // add timetable part

    }

    private void createCourse() {
        CreateCourse course = new CreateCourse();
        dispose();
    }

    private void createNotice() {
        //add nortice part
        dispose();
    }

    private void createUserProfile() {

        UserProfiles userpro = new UserProfiles();
        dispose();
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));
    }

    
    
}
