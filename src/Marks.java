import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Arrays;
import java.text.DecimalFormat;

public class Marks extends JFrame {
    private JButton AddCA;
    private JButton EditCA;
    private JButton DeleteCA;
    private JButton EditFinalButton;
    private JButton AddFinalButton;
    private JButton deleteFinalButton;
    private JTextField IDText;
    private JTextField quiz1;
    private JTextField quiz2;
    private JTextField quiz3;
    private JTextField quiz4;
    private JTextField quiz5;
    private JTextField AssignmentText1;
    private JTextField AssignmentText2;
    private JTextField MidText2;
    private JLabel textggf;
    private JTextField IDTextfinal;
    private JTextField Textfinaltheory;
    private JTextField TextFinalPractical;
    private JComboBox Subjectcombo;
    private JTextField evaluatequiz;
    private JPanel Main;
    private JTable resulttable;
    private JTable finalmarkstable;
    String lecid = "Lec001";

    Marks() throws SQLException {
        setContentPane(Main);
        setTitle("Marks");
        setSize(600,600);
        setVisible(true);

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/tecmis", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT course_id FROM `lecture` WHERE user_id='" + lecid + "'");
        while (rs.next()) {
            Subjectcombo.addItem(rs.getString("course_id"));
        }
        showcatable();
        showendtable();
        AddCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              addca();
            }
        });
        EditCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editca();
            }
        });
        DeleteCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteca();
            }
        });
        AddFinalButton.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
                try {
                    addfinalmarks();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        EditFinalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editfinalmarks();
            }
        });
        deleteFinalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletefinalmarks();

            }
        });
        resulttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadcafinal();
            }
        });
        finalmarkstable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadtablefinal();
            }
        });
    }
    void addca() {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setDecimalSeparatorAlwaysShown(false);

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

        String subject = (String) Subjectcombo.getSelectedItem();
        String studentid = IDText.getText().toString();
        Float intquiz = (float) (sum / considerquiz);
        Float ass1, ass2;
        Float intassignment = null;

        String assignment = null;
        Float total=7.7f;
        try {
            ass1 = Float.valueOf(AssignmentText1.getText());
            ass2 = Float.valueOf(AssignmentText2.getText());
            intassignment = (ass1 + ass2) / 2;
            assignment = String.valueOf(intassignment);
        } catch (NumberFormatException e) {
            assignment = "N/A";
        }
        String mid = MidText2.getText();
        if (mid.isEmpty()) {
            mid = "N/A";
        }

        if (mid.equals("N/A") && assignment.equals("N/A")) {
            total = intquiz;
        } else if (mid.equals("N/A")) {
            total = (intassignment + intquiz )/ 2;
        } else if (assignment.equals("N/A")) {
            total = (intquiz + Float.valueOf(mid)) / 2;
        } else {
            total = (intquiz + Float.valueOf(mid) + intassignment) / 3;
        }
        String iselegibal;
        if (total >= 50) {
            iselegibal = "Eligible";
        } else {
            iselegibal = "Not Eligible";
        }
        String formattedTotal = decimalFormat.format(total);
        System.out.println(studentid);
        System.out.println(subject);
        System.out.println(assignment);
        System.out.println(mid);
        System.out.println(intquiz);
        System.out.println(total);
        System.out.println(iselegibal);


    DatabaseConnection dbconn = new DatabaseConnection();
    String query = "INSERT INTO ca_marks (subject_id, student_id, quiz, assignment, mid, Total, Elegibaly) VALUES ('" + subject + "', '" + studentid + "', '" + intquiz + "', '" + assignment + "', '" + mid + "', '" + total + "', '" + iselegibal + "')";
    dbconn.writeData(query);
    showcatable();
    clearca();

    }





    void editca() {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setDecimalSeparatorAlwaysShown(false);

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

        String subject = (String) Subjectcombo.getSelectedItem();
        String studentid = IDText.getText().toString();
        Float intquiz = (float) (sum / considerquiz);
        Float ass1, ass2;
        Float intassignment = null;

        String assignment = null;
        Float total=7.7f;
        try {
            ass1 = Float.valueOf(AssignmentText1.getText());
            ass2 = Float.valueOf(AssignmentText2.getText());
            intassignment = (ass1 + ass2) / 2;
            assignment = String.valueOf(intassignment);
        } catch (NumberFormatException e) {
            assignment = "N/A";
        }
        String mid = MidText2.getText();
        if (mid.isEmpty()) {
            mid = "N/A";
        }

        if (mid.equals("N/A") && assignment.equals("N/A")) {
            total = intquiz;
        } else if (mid.equals("N/A")) {
            total = (intassignment + intquiz )/ 2;
        } else if (assignment.equals("N/A")) {
            total = (intquiz + Float.valueOf(mid)) / 2;
        } else {
            total = (intquiz + Float.valueOf(mid) + intassignment) / 3;
        }
        String iselegibal;
        if (total >= 50) {
            iselegibal = "Eligible";
        } else {
            iselegibal = "Not Eligible";
        }
        String formattedTotal = decimalFormat.format(total);
        System.out.println(studentid);
        System.out.println(subject);
        System.out.println(assignment);
        System.out.println(mid);
        System.out.println(intquiz);
        System.out.println(total);
        System.out.println(iselegibal);


        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "UPDATE ca_marks SET quiz = '" + intquiz + "', assignment = '" + assignment + "', mid = '" + mid + "', Total = '" + formattedTotal + "', Elegibaly = '" + iselegibal + "' WHERE student_id = '" + studentid + "' AND subject_id = '" + subject + "'";
        dbconn.writeData(query);
        showcatable();
        clearca();

    }

    void deleteca(){
        String studentid = IDText.getText().toString();
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "DELETE FROM ca_marks WHERE student_id = '" + studentid + "'";
        dbconn.writeData(query);
        showcatable();
        clearca();
    }
    public void showcatable(){
        try {
            DBConnector dbconn2 = new DBConnector();
            Connection conn2 = dbconn2.getConnection();
            String query = "SELECT * FROM ca_marks";
            Statement stmt2 = conn2.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Subject ID", "Student ID", "Quiz", "Assignment", "Mid","Total","Elegibaly"},
                    0);

            while (rs2.next()) {
                String studentId = rs2.getString("student_id");
                String subjectId = rs2.getString("subject_id");
                String quiz = rs2.getString("quiz");
                String assignment = rs2.getString("assignment");
                String mid = rs2.getString("mid");
                String total = rs2.getString("Total");
                String elegibal = rs2.getString("Elegibaly");
                // Add the data as a new row to the model
                model.addRow(new Object[]{subjectId, studentId, quiz, assignment,mid,total,elegibal});
            }
            resulttable.setModel(model);
            rs2.close();
            stmt2.close();
            conn2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void clearca() {
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
    void loadcafinal(){
        int selectedRow = resulttable.getSelectedRow();
        String studentId = null;
        if (selectedRow != -1){
            DefaultTableModel model = (DefaultTableModel) resulttable.getModel();
            String subjectId = model.getValueAt(selectedRow, 0).toString();
            studentId = model.getValueAt(selectedRow, 1).toString();
            Float quiz = Float.parseFloat(model.getValueAt(selectedRow, 2).toString());
            Float assignment = Float.parseFloat(model.getValueAt(selectedRow, 3).toString());
            Float mid = Float.parseFloat(model.getValueAt(selectedRow, 4).toString());
            MidText2.setText(String.valueOf(mid));
            IDText.setText(String.valueOf(studentId));
        }

    }
void loadtablefinal(){
        int selectedRow = finalmarkstable.getSelectedRow();
        String studentId = null;
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) finalmarkstable.getModel();
            String stuId = model.getValueAt(selectedRow, 0).toString();
            Float finalmarks = Float.parseFloat(model.getValueAt(selectedRow, 2).toString());
            IDTextfinal.setText(String.valueOf(stuId));
            TextFinalPractical.setText(String.valueOf(finalmarks));
        }
    }
    void addfinalmarks() throws SQLException {
        String Stuid = IDTextfinal.getText();
        String subject = Subjectcombo.getSelectedItem().toString();
        float theory=0.0f;
        float practical =0.0f;
        String sub=Subjectcombo.getSelectedItem().toString();
        if (TextFinalPractical.getText().isEmpty()) {
            theory=0.0f;
        }
        else{
            theory=Float.valueOf( Textfinaltheory.getText());
        }

        if (TextFinalPractical.getText().isEmpty()) {
            theory=0.0f;
        }
        else{
            theory=Float.valueOf( TextFinalPractical.getText());
        }
        FinalMarksCalculator x=new FinalMarksCalculator();
        float y=x.calculateAndSaveFinalMarks(Stuid,subject,theory,practical);
        GradeCalculator cal=new GradeCalculator();
        String grade= cal.calculateGrade(y);
        Float gpa=cal.calculater(grade);
        gpasend gpasend2=new gpasend();
        gpasend2.sendgpa(subject,Stuid,gpa);
        System.out.println(y);
//SubjectCreditRetriever credit=new SubjectCreditRetriever();
//        DatabaseConnection dbconn = new DatabaseConnection();
//        String query = "INSERT INTO final_marks(stuid, subid, theory, finalMarks) VALUES ('" + Stuid + "', '" + subject + "', '" + theory + "', '" + practical + "')";
//        dbconn.writeData(query);
  //      showendtable();
    }
    void editfinalmarks(){
        String Stuid=IDTextfinal.getText();
        String subject=Subjectcombo.getSelectedItem().toString();
        String theory=Textfinaltheory.getText();
        String practical=TextFinalPractical.getText();
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "UPDATE final_marks SET subid = '" + subject + "', theory = '"+theory+"', finalMarks = '" + practical + "' WHERE stuid = '" + Stuid + "' AND subid = '" +subject+ "'";
        dbconn.writeData(query);
        showendtable();
    }
    void deletefinalmarks(){
        String Stuid=IDTextfinal.getText();
        String subject=Subjectcombo.getSelectedItem().toString();
        DatabaseConnection dbconn = new DatabaseConnection();
        String query = "DELETE FROM final_marks WHERE stuid = '" + Stuid + "' AND subid = '" + subject + "'";
        dbconn.writeData(query);
       showendtable();
    }
    public void showendtable(){
        try {
            DBConnector dbconn2 = new DBConnector();
            Connection conn2 = dbconn2.getConnection();
            String query = "SELECT * FROM final_marks";
            Statement stmt2 = conn2.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"stuid","subid","Theory","FinalMarks"},
                    0);

            while (rs2.next()){
                String studentId = rs2.getString("stuid");
                String subjectId = rs2.getString("subid");
                String theory = rs2.getString("Theory");
                String finalmarks = rs2.getString("finalMarks");

                // Add the data as a new row to the model
                model.addRow(new Object[]{studentId,subjectId,theory,finalmarks});
            }
            finalmarkstable.setModel(model);
            rs2.close();
            stmt2.close();
            conn2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



}}
