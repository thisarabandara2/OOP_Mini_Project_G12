import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewStudent {
    private JTextField textid;
    private JTextField textname;
    private JTable table1;
    private JButton searchButton;
    private JButton clearButton;
    private JTextField textdepartment;
    private JTextField  textcontact;
    private JTextField textcource;
    private JScrollPane table_1;

    Connection con;
    PreparedStatement pst;
    private Container ViewStudent;

    public void connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/t e c m i s", "root","");
            System.out.println("success");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    void table_load()
    {
        try
        {
            pst = con.prepareStatement("select * from student01");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
public ViewStudent() {
    connect();
    table_load();
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                String studentid = textid.getText();

                pst = con.prepareStatement("select departmentid,contactnum,course,name from student01 where studentid = ?");
                pst.setString(1, studentid);
                ResultSet rs = pst.executeQuery();

                if(rs.next()==true)
                {
                    String departmentid = rs.getString(2);
                    String contactnum = rs.getString(3);
                    String course = rs.getString(3);
                    String name = rs.getString(1);

                    textdepartment.setText(departmentid);
                    textcontact.setText(contactnum );
                    textcource.setText(course);
                    textname.setText(name);

                }
                else
                {
                    textdepartment.setText("");
                    textcontact.setText("");
                    textcource.setText("");
                    textname.setText("");
                    JOptionPane.showMessageDialog(null,"Invalid Employee No");

                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    });
    clearButton.addActionListener(new ActionListener() {


        @Override
        public void actionPerformed(ActionEvent e) {
            textdepartment.setText("");
            textcontact.setText("");
            textcource.setText("");
            textname.setText("");
        }
    });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ViewStudent");
        frame.setContentPane(new ViewStudent().ViewStudent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}





