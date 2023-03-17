import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Singin extends JFrame {
    private JPanel Singin;
    private JButton submitButton;
    private JButton cancelButton;
    private JTextField firstnameinput;
    private JTextField lastnameinput;
    private JTextField registernumberinput;
    private JComboBox positionbox;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    public Singin(){
        add(Singin);
        setTitle("Sing in");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }


    private void registerUser() {

        String Fname = firstnameinput.getText();
        String Lname = lastnameinput.getText();
        String RegNo = registernumberinput.getText();
       // String position = JComboBox.
    }


    public static void main(String[] args) {
        Singin obj1 = new Singin();
        //JComboBox<String> choice = new JComboBox<String>(positionbox.getModel());
        //choice.setVisible(true);
    }

}

