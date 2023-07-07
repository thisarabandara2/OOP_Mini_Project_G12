import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
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
    private JTextField subBox;
    private JButton deleteButton;
    private JTable table1;
    private JButton searchButton;
    private JButton updateBtn;
    private JComboBox typeBox;


    private PreparedStatement pst;
    private Connection conn;
    private ResultSet rs;

    JDateChooser date1 = new JDateChooser();
    JDateChooser date2 = new JDateChooser();




    public AddMedical() {
        setVisible(true);
        add(pannel4);
        setSize(800, 400);

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
                String tg = tgBox.getText();
                String level = lelBox.getSelectedItem().toString();
                String semester = semBox.getSelectedItem().toString();
                java.util.Date startDate = date1.getDate();
                java.util.Date endDate = date2.getDate();
                String medFile = chooseBox.getText();
                String subjects = subBox.getText();
                String type = typeBox.getSelectedItem().toString();



                if (startDate != null && endDate != null) {
                    long difference = Math.abs(endDate.getTime() - startDate.getTime());
                    long daysDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

                    if (daysDifference > 14) {
                        JOptionPane.showMessageDialog(null, "The gap between the selected dates is more than 14 days.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {

                        String sql = "INSERT INTO medical (tg, level, semester, start_date, end_date, subjects, medFile, type) VALUES ('" + tg + "', '" + level + "', '" + semester + "', '" + startDate + "', '" + endDate + "', '" + subjects + "', '" + medFile + "', '"+type+"')";
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate(sql);



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

                    String newPath = "medicals/";
                    File directory = new File(newPath);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }

                    File destinationFile = new File(newPath + f.getName());

                    Files.copy(f.toPath(), destinationFile.toPath());

                    chooseBox.setText(newPath + f.getName());
                } catch (IOException ex) {
                    Logger.getLogger(AddTimeTables.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
});
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMedDetails();
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Do you want to delete?");

                String tgg = tgBox.getText();
                String level = lelBox.getSelectedItem().toString();

                String semester = semBox.getSelectedItem().toString();

               String subject = subBox.getText();
               String med = chooseBox.getText();

                try {
                    String sql = "DELETE FROM medical  WHERE tg= '"+tgg+"' AND level = '" + level + "' AND semester = '" + semester + "' AND subjects = '" + subject + "' AND medFile='"+med+"' ";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                   JOptionPane.showMessageDialog(null, "Deleted");
                    clear();
                } catch (HeadlessException | NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Not Deleted");
                }


                 getMedDetails();
            }

        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 tabledata();
            }
        });


        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tg = tgBox.getText();
                String level = lelBox.getSelectedItem().toString();
                String semester = semBox.getSelectedItem().toString();
                java.util.Date startDate = date1.getDate();
                java.util.Date endDate = date2.getDate();
                String medFile = chooseBox.getText();
                String subjects = subBox.getText();


                try {
                    String updateSql = "UPDATE medical SET level = '"+level+"', semester = '"+semester+"', start_date= '"+startDate+"', end_date = '"+endDate+"' , subjects = '"+subjects+"', medFile ='"+medFile+"'  WHERE tg = '"+tg+"'";
                    PreparedStatement pstmt = conn.prepareStatement(updateSql);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Data updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to update data in the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
                getMedDetails();

            }
        });
    }

    public void getMedDetails() {
        try {

            String tg = tgBox.getText();
            String sql = "SELECT * FROM medical where tg = '"+tg+"'   ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    public void clear() {

        lelBox.setSelectedItem(null);

        semBox.setSelectedItem(null);
        chooseBox.setText("");
        date1.setDate(null);
        date2.setDate(null);

        subBox.setText("");
    }



    public void tabledata() {
        int selectedRow = table1.getSelectedRow();

        String tg = table1.getValueAt(selectedRow, 0).toString();
            String level = table1.getValueAt(selectedRow, 1).toString();
            String semester = table1.getValueAt(selectedRow, 2).toString();

            String subject= table1.getValueAt(selectedRow, 5).toString();


        tgBox.setText(tg);
            lelBox.setSelectedItem(level);
            semBox.setSelectedItem(semester);

            subBox.setText(subject);


    }


}



