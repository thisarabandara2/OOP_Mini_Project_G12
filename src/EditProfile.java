import javax.swing.*;

public class EditProfile extends JFrame{
    private JPanel edit;
    private JLabel logoimage;
    private JLabel Name;
    private JButton button1;


    EditProfile(){
        setVisible(true);
        setSize(750, 650);
        setTitle("Edit Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(edit);

    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));
    }
}
