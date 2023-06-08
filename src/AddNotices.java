import net.proteanit.sql.DbUtils;

import javax.swing.*;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNotices extends JFrame {

    private JTextField titleBox;
    private JTextField chooseBox;
    private JButton browseBtn;
    private JButton uploadBtn;
    private JButton deleteBtn;
    private JPanel panel3;
    private JTable noticeTable;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    AddNotices() {
        add(panel3);
        setVisible(true);
        setTitle("Add Notices");
        setSize(750, 650);

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
        tableget();

        uploadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = titleBox.getText();
                String choose = chooseBox.getText();
                String dateTime = getCurrentDateTime();  // Get the current date and time

                try {
                    String sql = "INSERT INTO notice (title, date, noticeFile) VALUES ('"+name+"', '"+dateTime+"' , '"+choose+"')";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Inserted");

                    tableget();
                    titleBox.setText("");
                    chooseBox.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
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
                    chooseBox.setText("notices/" + f.getName());

                    String newPath = "notices/";
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

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Do you want to delete?");

                    try {
                        String titlee = titleBox.getText();
                        String filename = chooseBox.getText();

                        String sql = "DELETE FROM notice WHERE title = '"+titlee+"' AND noticeFile = '"+filename+"'";
                        pst = conn.prepareStatement(sql);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Deleted");
                        tableget();
                        titleBox.setText("");
                        chooseBox.setText("");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Delete failed");
                    }

            }
        });

        noticeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabledata();
            }
        });
    }

    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }


    public void tableget() {
        try {
            String sql = "SELECT title, date, noticeFile FROM notice";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            noticeTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    public void tabledata() {
        int selectedRow = noticeTable.getSelectedRow();
        if (selectedRow != -1) {
           String title = noticeTable.getValueAt(selectedRow, 0).toString();
            String file = noticeTable.getValueAt(selectedRow, 2).toString();

            titleBox.setText(title);
            chooseBox.setText(file);
        }
    }








}
