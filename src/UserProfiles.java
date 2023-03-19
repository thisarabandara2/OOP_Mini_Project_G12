import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserProfiles extends JFrame {

    private JLabel logoimage;
    private JButton createNewUserButton;
    private JPanel userProfile;
    private JPanel displayUsers;

    public UserProfiles() {

        setVisible(true);
        setSize(750, 650);
        setTitle("Users");
        add(userProfile);
        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add user registration part
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));

    }
}
