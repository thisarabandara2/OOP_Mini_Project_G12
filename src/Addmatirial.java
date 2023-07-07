import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

public class Addmatirial extends JFrame {
    private JComboBox<String> combosubject;
    private JTextField titletext;
    private JButton add;
    private JButton delete;
    private JTable table1;
    private JButton browsbtn;
    private JTextField chooseBox;
    private JPanel Material;
    DBConnector db = new DBConnector();
    Connection conn = db.getConnection();
    Statement stmt = conn.createStatement();

    public Addmatirial() throws SQLException {
        setVisible(true);
        setSize(500, 500);
        setContentPane(Material);
        String lecid = "lec01";
        tableget();
        ResultSet rs = stmt.executeQuery("SELECT subject FROM `lecsubject` WHERE lecid='" + lecid + "'");
        while (rs.next()) {
            combosubject.addItem(rs.getString("subject"));
        }

        browsbtn.addActionListener(new ActionListener() {
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
                    chooseBox.setText("material/" + f.getName());

                    String newPath = "material/";
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
                    ex.printStackTrace();
                    System.out.println("error");
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titletext.getText().isEmpty() && chooseBox.getText().isEmpty()) {
                    System.out.println("enter all field");
                } else {
                    String subject = combosubject.getSelectedItem().toString();
                    String title = titletext.getText();
                    String filepath = chooseBox.getText();
                    String query = "insert into material value('" + subject + "','" + title + "','" + filepath + "')";
                    db.writeData(query);
                    tableget();
                }
            }

        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    // Get data from the selected row
                    String subject = table1.getValueAt(selectedRow, 0).toString();
                    String title = table1.getValueAt(selectedRow, 1).toString();
                    String filepath = table1.getValueAt(selectedRow, 2).toString();

                    // Populate the text fields
                    combosubject.setSelectedItem(subject);
                    titletext.setText(title);
                    chooseBox.setText(filepath);
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "DELETE FROM material WHERE Tittle = '" + titletext.getText() + "'";
                db.writeData(query);
                tableget();
            }
        });
    }

    private void tableget() {
        try {
            String sql = "SELECT * FROM material";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Create the table model with column names
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add data rows to the table model
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }

            table1.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
