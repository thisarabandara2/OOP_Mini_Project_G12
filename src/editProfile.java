import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editProfile extends JFrame {
    private JTextField texetFirstname;
    private JTextField textLastname;
    private JTextField textPasswword;
    private JButton Updatebutton1;
    private JTable table1;
    private JPanel panel;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;
    private String userId;

     editProfile() {

         setVisible(true);
         setSize(750,450);
         setTitle("EditProfileDetails");
         add(panel);


        userId = User.getUserin();
        DBConnect dbConnect = new DBConnect();
        try {
            conn = dbConnect.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        Updatebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName =texetFirstname .getText();
                String lastName = textLastname.getText();
                String password = textPasswword.getText();
                if (!firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
                    updateProfile(firstName, lastName, password);
                    getProfileDetails();
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        getProfileDetails();
    }

    private void updateProfile(String firstName, String lastName, String password) {
        try {
            String sql = "UPDATE user SET fname = ?, lname = ?, password = ? WHERE user_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, password);
            pst.setString(4, userId);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Profile updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update profile.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void getProfileDetails() {
        try {
            String sql = "SELECT * FROM user WHERE user_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"User ID", "First Name", "Last Name", "Password"},
                    0);

            // Add rows to the model
            while (rs.next()) {
                String userID = rs.getString("user_id");
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String password = rs.getString("password");
                model.addRow(new Object[]{userID, firstName, lastName, password});
            }

            table1.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
