import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeeNotices extends JFrame {
    private JTable nTable;
    private JButton downloadBtn;
    private JPanel panel3;

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    SeeNotices() {

        add(panel3);
        setVisible(true);
        setTitle("See Notices");
        setSize(700, 500);



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





        downloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = nTable.getSelectedRow();
                if (selectedRow != -1) {
                    String file = nTable.getValueAt(selectedRow, 3).toString();
                    openFile(file);
                }
            }
        });
    }


    public void tableget() {
        try {
            String sql = "SELECT * FROM notice";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"no", "title", "date", "noticeFile"},
                    0);

            while (rs.next()) {
                String no = rs.getString("no");
                String title = rs.getString("title");
                String date = rs.getString("date");
                String file = rs.getString("noticeFile");
                model.addRow(new Object[]{no, title, date, file});
            }

            nTable.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void openFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to open the file.", "File Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File not found.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
