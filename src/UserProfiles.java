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

    void table_lord(){
        try
        {
            pst = con.prepareStatement("select user_id,fname,lname,address,email,birth_day,contact_number,user_type from user");
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
