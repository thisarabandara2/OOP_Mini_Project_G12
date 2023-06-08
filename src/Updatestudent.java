import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Updatestudent extends JFrame{
    private JTextField textaddress;
    private JTextField textemail;
    private JTextField textnumber;
    private JButton UPLOADButton;
    private JButton UPDATEButton;

    private JPanel panel;
    private JTextField textupload;
    private JButton backButton;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;
    private String userId;
      String profilePicturePath  ;
    String address;
    String email ;
    String number;


    public Updatestudent() {
        setVisible(true);
        setSize(500,600);
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


        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                address = textaddress.getText();
                email = textemail.getText();
                number = textnumber.getText();
                profilePicturePath = textupload.getText();
                if (!address.isEmpty() && !email.isEmpty() && !number.isEmpty()) {
                    Updatestudent(address, email, number,profilePicturePath);
                    new Student();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }



            }
        });


        UPLOADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    File f = chooser.getSelectedFile();
                    String filename = f.getAbsolutePath();

                    Path pathAbsolute = f.toPath();
                    Path pathBase = Paths.get("").toAbsolutePath();
                    Path pathRelative = pathBase.relativize(pathAbsolute);
                    String relativePath = pathRelative.toString().replace(File.separator, "/");
                    textupload.setText("profile/" + f.getName());

                    String newPath = "profile/";
                    File directory = new File(newPath);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }

                    File sourceFile = new File(filename);
                    String extension = filename.substring(filename.lastIndexOf('.') + 1);
                    String newName = f.getName();

                    File destinationFile = new File(newPath + newName);

                    Files.copy(sourceFile.toPath(), destinationFile.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(AddTimeTables.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });        //getProfileDetails();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Student();
            }
        });
    }
    private void Updatestudent(String address, String email, String number, String profilePicturePath) {
        try {




            String sql = "UPDATE user SET address = ?, email = ?, contactnumber = ?, profile_picture = ? WHERE user_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.address);
            pst.setString(2, this.email);
            pst.setString(3, this.number);
            pst.setString(4, this.profilePicturePath);
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



    
}
