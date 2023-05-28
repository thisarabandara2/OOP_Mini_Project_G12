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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddTimeTables extends JFrame {

    private JPanel panel5;
    private JComboBox<String> refBox;
    private JComboBox<String> lelBox;
    private JComboBox<String> depBox;
    private JComboBox<String> semBox;
    private JComboBox<String> dayBox;
    private JTextField timeBox;
    private JTextField subBox;
    private JTextField chooseBox;
    private JButton browseBtn;
    private JButton uploadBtn;
    private JButton deleteBtn;
    private JTable jTable1;
    private JButton clearButton;



    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    AddTimeTables(){
        add(panel5);
        setVisible(true);

        setTitle("Add Time Tables" );
        setSize(600, 600);

//Database Connectivity

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

//Database Connectivity

        tableget();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        browseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    File f = chooser.getSelectedFile();
                    String filename = ((File) f).getAbsolutePath();

                    Path pathAbsolute = f.toPath();
                    Path pathBase = Paths.get("").toAbsolutePath();
                    Path pathRelative = pathBase.relativize(pathAbsolute);
                    String relativePath = pathRelative.toString().replace(File.separator, "/");
                    chooseBox.setText("uploads/" + f.getName());

                    String newPath = "uploads/";
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabledata();
            }
        });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        uploadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = refBox.getSelectedItem().toString();
                String department = depBox.getSelectedItem().toString();
                int level = Integer.parseInt(lelBox.getSelectedItem().toString());
                int semester = Integer.parseInt(semBox.getSelectedItem().toString());
                String day = dayBox.getSelectedItem().toString();
                String time = timeBox.getText();
                String subject = subBox.getText();
                String choose = chooseBox.getText();

                try {
                    String sql = "INSERT INTO timetable (refNo, department, level, semester, day, time, subject, file) VALUES ('" + name + "', '" + department + "', " + level + ", " + semester + ", '" + day + "', '" + time + "', '" + subject + "', '" + choose + "')";

                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Inserted");
                    tableget();
                    clear();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Do you want to delete?");

                String level = lelBox.getSelectedItem().toString();
                String department = depBox.getSelectedItem().toString();
                String semester = semBox.getSelectedItem().toString();
                String time = timeBox.getText();
                String day = dayBox.getSelectedItem().toString();
                String subject = subBox.getText();

                try {
                    String sql = "DELETE FROM timetable WHERE level = '" + level + "' AND department = '" + department + "' AND semester = '" + semester + "' AND time = '" + time + "' AND day = '" + day + "' AND subject = '" + subject + "'";
                    pst = conn.prepareStatement(sql);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Deleted");
                    clear();
                } catch (HeadlessException | NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Delete");
                }


                tableget();
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });



    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void tableget() {
        try {
            String sql = "SELECT department, level, semester, day, time, subject, file FROM timetable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public void tabledata() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String department = jTable1.getValueAt(selectedRow, 0).toString();
            String level = jTable1.getValueAt(selectedRow, 1).toString();
            String semester = jTable1.getValueAt(selectedRow, 2).toString();
            String day = jTable1.getValueAt(selectedRow, 3).toString();
            String time = jTable1.getValueAt(selectedRow, 4).toString();
            String subject = jTable1.getValueAt(selectedRow, 5).toString();
            String file = jTable1.getValueAt(selectedRow, 6).toString();

            depBox.setSelectedItem(department);
            lelBox.setSelectedItem(level);
            semBox.setSelectedItem(semester);
            dayBox.setSelectedItem(day);
            timeBox.setText(time);
            subBox.setText(subject);
            chooseBox.setText(file);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public void clear() {
        lelBox.setSelectedItem(null);
        depBox.setSelectedItem(null);
        semBox.setSelectedItem(null);
        chooseBox.setText("");
        dayBox.setSelectedItem(null);
        timeBox.setText("");
        subBox.setText("");
    }

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

