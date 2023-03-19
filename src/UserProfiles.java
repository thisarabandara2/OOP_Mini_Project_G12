import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UserProfiles extends JFrame {

    private JLabel logoimage;
    private JButton createNewUserButton;
    private JPanel userProfile;
    private JPanel displayUsers;
    private JTable table_1;
    private JButton removeButton;
    private JTextField textField1;

    public UserProfiles() {

        connect();
        table_lord();
        setVisible(true);
        setSize(750, 650);
        setTitle("Users");
        add(userProfile);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add user registration part
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });
    }

    public void removeUser() {

        String userid;
        userid = textField1.getText();

        try {
            pst = con.prepareStatement("delete from user  where user_id = ?");

            pst.setString(1, userid);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
            table_lord();

        }

        catch (SQLException e1)
        {

            e1.printStackTrace();
        }

        }


    Connection con;
    PreparedStatement pst;
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/tecmis", "root","root");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    public void table_lord(){
        try
        {
            pst = con.prepareStatement("select user_id as 'User ID',fname as 'First Name',lname as 'Last Name',address as 'Address',email as 'Email',birth_day as 'Birth Date',contact_number as 'Contact Number',user_type as 'User Type' from user");
            ResultSet rs = pst.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));

    }
}
