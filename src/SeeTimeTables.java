import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeeTimeTables extends JFrame {
    private JButton downloadBtn;
    private JPanel panel2;
    private JComboBox<String> pdfBox;
    private Connection connection;

    SeeTimeTables() {
        add(panel2);
        setVisible(true);
        setTitle("See Time Tables");
        setSize(370, 450);




        DBConnect dbConnect = new DBConnect();
        try {
            connection = dbConnect.getConnection();
            if (connection == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }





        downloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String refNo = pdfBox.getSelectedItem().toString();

                try {
                    String sql = "SELECT file FROM timetable WHERE refNo = ?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, refNo);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String filePath = rs.getString("file");
                        File file = new File(filePath);

                        if (file.exists()) {

                            try {
                                Desktop.getDesktop().open(file);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "File not found.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No file");
                    }

                    rs.close();
                    pst.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to execute the query.");
                }
            } });
    }
}
