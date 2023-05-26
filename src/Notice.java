import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Notice extends JFrame {
    private JLabel logoimage;
    private JLabel Name;
    private JButton logOutButton;
    private JPanel notice;
    private JLabel date1;
    private JLabel title1;
    private JLabel date2;
    private JPanel title2;
    private JButton downlord2;
    private JButton downlord1;
    private JTable table1;

    public Notice() {

        setVisible(true);
        setSize(750, 650);
        setTitle("Notice");
        add(notice);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logging log3 = new Logging();
            }
        });
        Name.setText("Welcome " + Student.getFullName());
        printNotice();

    }

    private void printNotice() {


    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));
    }
}
