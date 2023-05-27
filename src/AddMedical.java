import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddMedical extends JFrame {
    private JTextField tgBox;
    private JComboBox<String> lelBox;
    private JComboBox<String> semBox;
    private JPanel starpannel;
    private JPanel pannel4;
    private JPanel endpannel;
    private JTextField chooseBox;
    private JButton browseBtn;
    private JButton submitBtn;


    private PreparedStatement pst;
    private Connection conn;

    JDateChooser date1 = new JDateChooser();
    JDateChooser date2 = new JDateChooser();

    public class DBConnect {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/Teclms";
        private static final String DB_USERNAME = "root";
        private static final String DB_PASSWORD = "";

        public Connection getConnection() throws SQLException {
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    public AddMedical() {
        setVisible(true);
        add(pannel4);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 400);

        starpannel.add(date1);
        endpannel.add(date2);

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

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected values
                String tg = tgBox.getText();
                String level = lelBox.getSelectedItem().toString();
                String semester = semBox.getSelectedItem().toString();
                java.util.Date startDate = date1.getDate();
                java.util.Date endDate = date2.getDate();
                String medFile = chooseBox.getText();

                if (startDate != null && endDate != null) {
                    long difference = Math.abs(endDate.getTime() - startDate.getTime());
                    long daysDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

                    if (daysDifference > 14) {
                        JOptionPane.showMessageDialog(null, "The gap between the selected dates is more than 14 days.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    String subjects = "";


                    try {
                        String sql = "INSERT INTO medical (tg, level, semester, start_date, end_date, subjects, medFile) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        pst = conn.prepareStatement(sql);
                        pst.setString(1, tg);
                        pst.setString(2, level);
                        pst.setString(3, semester);
                        pst.setString(4, startDate.toString());
                        pst.setString(5, endDate.toString());
                        pst.setString(6, subjects);
                        pst.setString(7, medFile);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Data inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Failed to insert data into the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select both start and end dates.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        browseBtn.addActionListener(new ActionListener() {
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
                    chooseBox.setText("medicals/" + f.getName());

                    String newPath = "medicals/";
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
        });
    }

    public static void main(String[] args) {
        AddMedical med = new AddMedical();
    }
}

