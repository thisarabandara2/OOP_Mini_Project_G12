import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Lectureupdate extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton updateButton;
    private JTextArea textArea1;
    private JPanel Update;
    private JButton homeButton;
    String user=User.getUserin();
    Connection conn = null;
    public Lectureupdate() {
        setContentPane(Update);
        setTitle("Lecture");
        setBounds(600, 200, 600, 300);
        setVisible(true);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tecmis", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user` WHERE user_id='"+user+"'");
            while (rs.next()) {

                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String birthday = rs.getString("birthday");
                String contact = rs.getString("contactnumber");

                textField1.setText(fname);
                textField2.setText(lname);
                textArea1.setText(address);
                textField3.setText(contact);
                textField5.setText(birthday);
                textField4.setText(email);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

//                conn = DriverManager.getConnection("jdbc:mysql://localhost/tecmis", "root", "");
//                Statement stmt = conn.createStatement();
//               stmt.executeUpdate("update user set fname='"+ textField1.getText()+"',lname='"+textField2.getText()+"',address='"+ textArea1.getText()+"',email='"+textField3.getText()+"',birthday='"+ textField5.getText()+"',contactnumber='"+textField4.getText()+"'where user_id='"+user+"'");
//                JOptionPane.showMessageDialog(null, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
//                stmt.close();
//                conn.close();
                JOptionPane.showMessageDialog(null, "success", "update", JOptionPane.ERROR_MESSAGE);






        }
    });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Lecture lectureobject=new Lecture();
                dispose();

            }
        });
    }
}
