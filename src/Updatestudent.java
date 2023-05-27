import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Updatestudent extends JFrame{
    private JTextField textaddress;
    private JTextField textemail;
    private JTextField textnumber;
    private JButton UPLOADButton;
    private JButton UPDATEButton;
    private JLabel profilePicturelabel;
    private JPanel panel;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;
    private String userId;
    private String profilePicturePath = "";


    public Updatestudent() {
        setVisible(true);
        setSize(750,450);
        setTitle("EditProfileDetails");
        add(panel);

        userId = userId;//add method
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


        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = textaddress.getText();
                String email = textemail.getText();
                String number = textnumber.getText();
                if (!address.isEmpty() && !email.isEmpty() && !number.isEmpty()) {
                    Updatestudent(address, email, number);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        UPLOADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String extension = getFileExtension(selectedFile.getName());
                    if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                        String destinationPath = "profile_pictures/" + userId + "." + extension;
                        try {
                            Files.copy(selectedFile.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            profilePicturePath = destinationPath;
                            ImageIcon imageIcon = new ImageIcon(profilePicturePath);
                            profilePicturelabel.setIcon(imageIcon);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Failed to upload profile picture.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid file format. Please upload an image (jpg, jpeg, or png).", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        getProfileDetails();
    }
    private void Updatestudent(String address, String email, String number) {
        try {
            String sql = "UPDATE user SET address = ?, email = ?, contactnumber = ?, profile_picture = ? WHERE user_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, address);
            pst.setString(2, email);
            pst.setString(3, number);
            pst.setString(4, profilePicturePath);
            pst.setString(5, userId);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Profile details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update profile details.", "Error", JOptionPane.ERROR_MESSAGE);
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

            while (rs.next()) {
                String address = rs.getString("address");
                String email = rs.getString("email");
                String number = rs.getString("number");

                textaddress.setText(address);
                textemail.setText(email);
                textnumber.setText(number);

                String profilePictureFilename = userId + getFileExtension(rs.getString("profile_picture"));
                profilePicturePath = "profile_pictures/" + profilePictureFilename;
                ImageIcon imageIcon = new ImageIcon(profilePicturePath);
                profilePicturelabel.setIcon(imageIcon);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        } else {
            return "";
        }
    }
    
}
