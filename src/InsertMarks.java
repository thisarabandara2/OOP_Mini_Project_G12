import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

import java.util.Arrays;
public class InsertMarks extends JFrame {
    private JPanel Panel;
    String lecid = "Lec001";
    private JComboBox Subjectcombo;
    private JButton AddCA, EditCA, AddFinalbutton, Addquiz;
    private JTextField quiz1;
    private JTextField quiz2;
    private JTextField quiz3;
    private JTextField quiz4;
    private JTextField quiz5;
    private JTextField evaluatequiz;
    private JTextField AssignmentText1, AssignmentText2, FinalThearyText, FinalPracticalText, MidText2, IDText;

    private JTable resulttable;
    private JButton EditFinalButton;
    private JButton deleteButton;
    private JTextField IDTextfinal;
    private JButton deletefinalButton;
    private JTable finalmarkstable;

    public InsertMarks() throws SQLException {
        setContentPane(Panel);
        setTitle("Marks");
        setBounds(600, 200, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tecmis", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT course_id FROM `lecture` WHERE user_id='" + lecid + "'");
        while (rs.next()) {
            Subjectcombo.addItem(rs.getString("course_id"));
        }
        tableload();
        finaltableload();

        int selectedRow = resulttable.getSelectedRow();
        if (selectedRow != -1) {
            String studentId = resulttable.getValueAt(selectedRow, 1).toString();
            Float mid = Float.parseFloat(resulttable.getValueAt(selectedRow, 4).toString());
            IDText.setText(studentId);
            MidText2.setText(String.valueOf(mid));
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Addquiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        AddCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        senddata();
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        EditCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               updatedata();
           }

        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        AddFinalbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float endtheory;
                Float endpractical;
                endtheory = Float.valueOf(FinalThearyText.getText());
                endpractical = Float.valueOf(FinalPracticalText.getText());
                String query = "INSERT INTO final_marks(stuid, subid, finalMarks) VALUES ('" + endtheory + "', '" + endpractical + "')";
                DatabaseConnection dbconn = new DatabaseConnection();
                dbconn.writeData(query);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        EditFinalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        resulttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                loadtable2();

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        AddFinalbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addfinalmarks();
            }
        });
        EditFinalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
editfinalmarks();
            }
        });
        deletefinalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
deletefinalmarks();
            }
        });
        finalmarkstable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadtablefinal();
            }
        });
    }
    public void tableload() {
        try {
            DBConnector dbconn2 = new DBConnector();
            Connection conn2 = dbconn2.getConnection();
            String query = "SELECT * FROM ca_marks";
            Statement stmt2 = conn2.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Subject ID", "Student ID", "Quiz", "Assignment", "Mid"},
                    0);

            while (rs2.next()) {
                String studentId = rs2.getString("student_id");
                String subjectId = rs2.getString("subject_id");
                Float quiz = rs2.getFloat("quiz");
                Float assignment = rs2.getFloat("assignment");
                Float mid = rs2.getFloat("mid");
                // Add the data as a new row to the model
                model.addRow(new Object[]{subjectId, studentId, quiz, assignment, mid});
            }
            resulttable.setModel(model);
            rs2.close();
            stmt2.close();
            conn2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void clear() {
        IDText.setText("");
        MidText2.setText("");
        AssignmentText1.setText("");
        AssignmentText2.setText("");
        evaluatequiz.setText("");
        quiz1.setText("");
        quiz2.setText("");
        quiz3.setText("");
        quiz4.setText("");
        quiz5.setText("");
    }
    String loadtable2() {
        int selectedRow = resulttable.getSelectedRow();
        String studentId = null;
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) resulttable.getModel();
            String subjectId = model.getValueAt(selectedRow, 0).toString();
             studentId = model.getValueAt(selectedRow, 1).toString();
            Float quiz = Float.parseFloat(model.getValueAt(selectedRow, 2).toString());
            Float assignment = Float.parseFloat(model.getValueAt(selectedRow, 3).toString());
            Float mid = Float.parseFloat(model.getValueAt(selectedRow, 4).toString());
            MidText2.setText(String.valueOf(mid));
            IDText.setText(String.valueOf(studentId));
        }
        return studentId;
    }    String loadtablefinal() {
        int selectedRow = finalmarkstable.getSelectedRow();
        String studentId = null;
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) finalmarkstable.getModel();
            String stuId = model.getValueAt(selectedRow, 0).toString();
            Float finalmarks = Float.parseFloat(model.getValueAt(selectedRow, 2).toString());
            IDTextfinal.setText(String.valueOf(stuId));
            FinalPracticalText.setText(String.valueOf(finalmarks));
        }
        return studentId;
    }

    void senddata(){
        Float[] quizmarks = new Float[5];
        quizmarks[0] = Float.valueOf(quiz1.getText());
        quizmarks[1] = Float.valueOf(quiz2.getText());
        quizmarks[2] = Float.valueOf(quiz3.getText());
        quizmarks[3] = Float.valueOf(quiz4.getText());
        quizmarks[4] = Float.valueOf(quiz5.getText());
        int considerquiz = Integer.valueOf(evaluatequiz.getText());

        Arrays.sort(quizmarks);

        int sum = 0;
        for (int i = quizmarks.length - 1; i >= quizmarks.length - considerquiz; i--) {
            sum += quizmarks[i];
        }
        double average = (double) sum / considerquiz;
        String studentid = IDText.getText().toString();
        String subject = (String) Subjectcombo.getSelectedItem();
        Float ass1 = Float.valueOf(AssignmentText1.getText());
        Float ass2 = Float.valueOf(AssignmentText2.getText());
        Float averageass = ass1 + ass2 / 2;
        Float mid = Float.valueOf(MidText2.getText());
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "INSERT INTO ca_marks (subject_id, student_id, quiz, assignment, mid) VALUES ('" + subject + "', '" + studentid + "', '" + average + "',' " + averageass + "',' " + mid + "')";
        dbconn.writeData(query);
        tableload();
        clear();
    }
    void updatedata(){
        Float[] quizmarks = new Float[5];
        quizmarks[0] = Float.valueOf(quiz1.getText());
        quizmarks[1] = Float.valueOf(quiz2.getText());
        quizmarks[2] = Float.valueOf(quiz3.getText());
        quizmarks[3] = Float.valueOf(quiz4.getText());
        quizmarks[4] = Float.valueOf(quiz5.getText());
        int considerquiz = Integer.valueOf(evaluatequiz.getText());
        Arrays.sort(quizmarks);
        int sum = 0;
        for (int i = quizmarks.length - 1; i >= quizmarks.length - considerquiz; i--) {
            sum += quizmarks[i];
        }
        double average = (double) sum / considerquiz;
        String studentid = IDText.getText().toString();
        String subject = (String) Subjectcombo.getSelectedItem();
        Float ass1 = Float.valueOf(AssignmentText1.getText());
        Float ass2 = Float.valueOf(AssignmentText2.getText());
        Float averageass = ass1 + ass2 / 2;
        Float mid = Float.valueOf(MidText2.getText());
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "UPDATE ca_marks SET subject_id = '" + subject + "', quiz = '" + average + "', assignment = '" + averageass + "', mid = '" + mid + "' WHERE student_id = '" + studentid + "'";
        dbconn.writeData(query);
        tableload();
        clear();
    }
    void delete(){
        String studentid = IDText.getText().toString();
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "DELETE FROM ca_marks WHERE student_id = '" + studentid + "'";
        dbconn.writeData(query);
        tableload();
        clear();
    }
    void addfinalmarks(){
        float finalmarks=Float.valueOf(FinalPracticalText.getText());
        String Stuid=IDTextfinal.getText();
        String subject=Subjectcombo.getSelectedItem().toString();
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "INSERT INTO final_marks(stuid,subid,finalMarks) VALUES('" + Stuid + "', '" + subject + "','"+ finalmarks+"')";
        dbconn.writeData(query);
        tableload();
    }
    void editfinalmarks(){
            float finalmarks=Float.valueOf(FinalPracticalText.getText());
            String Stuid=IDTextfinal.getText();
            String subject=Subjectcombo.getSelectedItem().toString();
            DatabaseConnection dbconn = new DatabaseConnection();
        String query = "UPDATE final_marks SET subid = '" + subject + "', finalMarks = '" + finalmarks + "' WHERE stuid = '" + Stuid + "' AND subid = '" +subject+ "'";
        dbconn.writeData(query);
            tableload();
    }
    void deletefinalmarks(){
        String Stuid=IDTextfinal.getText();
        String subject=Subjectcombo.getSelectedItem().toString();
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "DELETE FROM final_marks WHERE stuid = '" + Stuid + "' AND subid = '" + subject + "'";
        dbconn.writeData(query);
        tableload();
    }
    public void finaltableload() {
        try {
            DBConnector dbconn2 = new DBConnector();
            Connection conn2 = dbconn2.getConnection();
            String query = "SELECT * FROM final_marks";
            Statement stmt2 = conn2.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"stuid","subid","finalMarks"},
                0);

            while (rs2.next()) {
                String studentId = rs2.getString("stuid");
                String subjectId = rs2.getString("subid");
                Float finalmarks = rs2.getFloat("finalMarks");

                // Add the data as a new row to the model
                model.addRow(new Object[]{studentId,subjectId,finalmarks});
            }
            finalmarkstable.setModel(model);
            rs2.close();
            stmt2.close();
            conn2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    }





