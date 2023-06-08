import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Viewattendance{
    private JComboBox subjectcombo;
    private JTable table;
    private JButton wholeAttendanceButton;
    private JButton greaterThat80Button;
    private JButton lessthan80Button;
    private JButton backButton;
    private JPanel mainpanel;
    DBConnector db=new DBConnector();
    Connection conn=db.getConnection();
    Statement stmt = conn.createStatement();
    String lecid="smn";
    public Viewattendance() throws SQLException {
        JFrame frame=new JFrame();
        frame.setContentPane(mainpanel);
        frame.setTitle("Marks");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ResultSet rs = stmt.executeQuery("SELECT subject FROM `lecsubject` WHERE lecid='" + lecid + "'");
        while (rs.next()) {
            subjectcombo.addItem(rs.getString("subject"));
        }
        wholeAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    wholeattendance();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        lessthan80Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        greaterThat80Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void wholeattendance() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT student_id,theory,practical,total FROM attendancepercentage where subject_id='"+subjectcombo.getSelectedItem().toString()+"'");
        DefaultTableModel model = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            model.addRow(rowData);
        }
        table.setModel(model);
    }
    private void greaterthan() throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT student_id FROM attendancepercentage WHERE total >= 80 and subject_id='"+subjectcombo.getSelectedItem().toString()+"'");
        DefaultTableModel model = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            model.addRow(rowData);
        }
        table.setModel(model);
    }

    private void lessthan() throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT student_id FROM attendancepercentage WHERE total < 80 and  subject_id='" +subjectcombo.getSelectedItem().toString()+"'");
        DefaultTableModel model = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            model.addRow(rowData);
        }
        table.setModel(model);
    }
}
