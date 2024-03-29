import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RestrationForm extends JDialog {
    private static JFrame parent;
    private JButton btnRegister;
    private JButton btnCancel;
    private JTextField tffname;
    private JTextField tfemail;
    private JTextField tfbirthday;
    private JTextField tfcontactnumber;
    private JTextField pfpassword;
    private JPasswordField pfconfirmpassword;
    private JPanel registerPanel;
    private JTextField tfuserid;
    private JTextField tflname;
    private JTextField tfaddress;
    private JTextField tfdepartment;
    private JComboBox comboBox1;
    private JComboBox comboBoxDep;

    public RestrationForm(JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserProfiles().table_lord();
                dispose();
            }
        });

        setVisible(true);

    }

    private void registerUser() {
        String user_id = tfuserid.getText();
        String fname = tffname.getText();
        String lname = tflname.getText();
        String address = tfaddress.getText();
        String email = tfemail.getText();
        String birthday = tfbirthday.getText();
        String contactnumber = tfcontactnumber.getText();
        String usertype =comboBox1.getSelectedItem().toString();
        String department = comboBoxDep.getSelectedItem().toString();
        String adminid = "1";
        String password = pfpassword.getText();
        String confirmpassword= pfconfirmpassword.getText();
        System.out.println("pw is" + pfconfirmpassword);

        if (user_id.isEmpty() || fname.isEmpty() || lname.isEmpty() || address.isEmpty() || email.isEmpty() || birthday.isEmpty() || contactnumber.isEmpty() || usertype.isEmpty() || department.isEmpty() || adminid.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmpassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(user_id, fname, lname, address, email, birthday, contactnumber, usertype,department, adminid,password);
        if (user != null) {
            JOptionPane.showMessageDialog(this,
                    "User Successfully Registered!!");

        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    Users user;

    private Users addUserToDatabase(String user_id, String fname, String lname, String address, String email, String birthday, String contactnumber, String usertype, String department, String adminid, String password) {
        Users user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/tecmis";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);


            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO user (user_id, fname, lname, address, email, birthday, contactnumber, usertype,department, adminid, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, lname);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, birthday);
            preparedStatement.setString(7, contactnumber);
            preparedStatement.setString(8, usertype);
            preparedStatement.setString(9, department);
            preparedStatement.setString(10, adminid);
            preparedStatement.setString(11, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new Users();
                user.userid = user_id;
                user.fname = fname;
                user.lname = lname;
                user.address = address;
                user.email = email;
                user.birthday = birthday;
                user.contactnumber = contactnumber;
                user.usertype = usertype;
                user.department = usertype;
                user.adminid = adminid;
                user.password = password;

            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }




    public static void main(String[] args) {
        RestrationForm myForm = new RestrationForm(null);
        Users user = myForm.user;
        if (user != null) {
            System.out.println("Successful registration of: " + user.fname);
        } else {
            System.out.println("Registration canceled");
        }
    }

}


