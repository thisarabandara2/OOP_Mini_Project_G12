import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Logging extends JFrame {
    private JPanel Loging;
    private JLabel Username;
    private JPasswordField inputpassword;
    private JTextField inputuser;
    private JButton OKButton;
    private JButton cancelButton;
    private JLabel logoimage;
    private JLabel incorrectpw;

    public Logging() {

        setVisible(true);
        setSize(750, 650);
        setTitle("Loging");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(Loging);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = inputuser.getText();
                String pword = inputpassword.getText();

                if (Objects.equals(uname, "admin")){
                    if(pword.equals("abc")){
                        Adminpanel adminpanel = new Adminpanel();
                    }else{
                        JOptionPane.showMessageDialog(null,"Incorrect Username or Password","Try again",JOptionPane.ERROR_MESSAGE);
//                        Logging log1 = new Logging();

                    }
                }
                else{

                        User user1 = new User(uname,pword);
                    {
                    }

                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void createUIComponents() {
        // TODO: place custom component creation code here

        logoimage = new JLabel(new ImageIcon("UOR.png"));

    }
}

