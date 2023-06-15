import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Arrays;
import java.text.DecimalFormat;
import java.util.Objects;
public class Marks extends  Elegibility{
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
    private JTextField AssignmentText5;
    private JTextField MidText2;

    private JTextField ID_Text_Final;
    private JTextField Textfinaltheory;
    private JTextField TextFinalPractical;
    private JComboBox Subjectcombo;
    private JTextField evaluatequiz;
    private JPanel Main;
    private JTable resulttable;
    private JTable finalmarkstable;
    private JTextField AssignmentText2;
    private JTextField AssignmentText4;
    private JTextField AssignmentText3;
    JFrame frame = new JFrame();
    private JLabel Quiz2;
    private JTable gpa;
    private JTextField considerassignment;
    private JLabel lblas1;
    private JLabel lblas2;
    private JLabel lblas3;
    private JLabel lblas4;
    private JLabel lblas5;
    private JLabel lblconas;
    private JLabel lblmid;
    private JLabel lblth;
    private JLabel lblpra;
    String lecid = "lec01";
    DBConnector dbconn = new DBConnector();
    Connection conn = dbconn.getConnection();
    String Query="";
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    Marks() throws SQLException {
        frame.setContentPane(Main);
        frame.setTitle("Marks");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT subject FROM `lecsubject` WHERE lecid='" + lecid + "'");
        while (rs.next()) {
            Subjectcombo.addItem(rs.getString("subject"));
        }
        showcatable();
        showendtable();
        AddCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(quiz1.getText().trim().isEmpty() &&
                         quiz2.getText().trim().isEmpty() &&
                         quiz3.getText().trim().isEmpty() &&
                         quiz4.getText().trim().isEmpty() &&
                         quiz5.getText().trim().isEmpty() &&
                         evaluatequiz.getText().trim().isEmpty() &&
                        AssignmentText1.getText().trim().isEmpty() &&
                        AssignmentText2.getText().trim().isEmpty() &&
                        AssignmentText3.getText().trim().isEmpty() &&
                        AssignmentText4.getText().trim().isEmpty() &&
                        AssignmentText5.getText().trim().isEmpty() &&
                        considerassignment.getText().trim().isEmpty() &&
                        MidText2.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
               else if (IDText.getText().trim().isEmpty()) {
                   JOptionPane.showMessageDialog(null, "Please enter the ID field.", "Error", JOptionPane.ERROR_MESSAGE);
               }else if(quiz1.getText().isEmpty() && quiz2.getText().isEmpty() &&
                       quiz3.getText().isEmpty() && quiz4.getText().isEmpty() &&
                       quiz5.getText().isEmpty()){
                   JOptionPane.showMessageDialog(null, "Please Add Quiz field.", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else if (quiz1.getText().trim().isEmpty() && quiz2.getText().trim().isEmpty() &&
                        quiz3.getText().trim().isEmpty() && quiz4.getText().trim().isEmpty() &&
                        quiz5.getText().trim().isEmpty() &&!evaluatequiz.getText().trim().isEmpty()) {
                   JOptionPane.showMessageDialog(null, "Please fill quiz field", "Error", JOptionPane.ERROR_MESSAGE);
               }else if ((!quiz1.getText().trim().isEmpty() || !quiz2.getText().trim().isEmpty() ||
                        !quiz3.getText().trim().isEmpty() || !quiz4.getText().trim().isEmpty() ||
                        !quiz5.getText().trim().isEmpty()) &&evaluatequiz.getText().trim().isEmpty()) {
                   JOptionPane.showMessageDialog(null, "Please fill ewaluated quiz field field", "Error", JOptionPane.ERROR_MESSAGE);
               } else if (AssignmentText1.getText().isEmpty() && AssignmentText2.getText().isEmpty() && AssignmentText3.getText().isEmpty() && AssignmentText4.getText().isEmpty() && AssignmentText5.getText().isEmpty() && !considerassignment.getText().isEmpty()) {
                   JOptionPane.showMessageDialog(null, "Please fill the Assignment fields.", "Error", JOptionPane.ERROR_MESSAGE);
               } else if ((!AssignmentText1.getText().isEmpty() || !AssignmentText2.getText().isEmpty() || !AssignmentText3.getText().isEmpty() || !AssignmentText4.getText().isEmpty() || !AssignmentText5.getText().isEmpty()) && considerassignment.getText().isEmpty()) {
                   JOptionPane.showMessageDialog(null, "Please fill the Evaluate Assignment field.", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else if(AssignmentText1.getText().isEmpty()&&AssignmentText2.getText().isEmpty()&&AssignmentText3.getText().isEmpty()&&AssignmentText4.getText().isEmpty()&&AssignmentText5.getText().isEmpty()&&MidText2.getText().isEmpty()){
                   JOptionPane.showMessageDialog(null, "Please fill either  Assignment or mid field.", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else{
                   AddCA();


               }
                }
        });
        EditCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCA();
            }
        });
        DeleteCA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCA();
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
                Deletefinalmarks();

            }
        });
        resulttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FinalCALoad();
            }
        });
        finalmarkstable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadtablefinal();
            }
        });
        Subjectcombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignmentText1.setVisible(true);
                AssignmentText2.setVisible(true);
                AssignmentText3.setVisible(true);
                AssignmentText4.setVisible(true);
                AssignmentText5.setVisible(true);
                considerassignment.setVisible(true);
                MidText2.setVisible(true);
                lblas1.setVisible(true);
                lblas2.setVisible(true);
                lblas3.setVisible(true);
                lblas4.setVisible(true);
                lblas5.setVisible(true);
                lblconas.setVisible(true);
                lblmid.setVisible(true);

                Statement stmt;
                String mid="";
                String assignment="";
                String theory="";
                String practical="";
                try {
                    stmt = conn.createStatement();
                    ResultSet result= stmt.executeQuery("select assignment,mid,final_theory,final_practical from marks_priority where Subject_id='"+Subjectcombo.getSelectedItem()+"'");
                    while (result.next()){
                        mid=result.getString("mid");
                        assignment=result.getString("assignment");
                        theory=result.getString("final_theory");
                        practical=result.getString("final_practical");
                    }
                    System.out.println(mid);
                    if(mid=="0"){
                        System.out.println("ho gana pokune");
                    }
                    if(assignment=="0"){
                        AssignmentText1.setVisible(false);
                        AssignmentText2.setVisible(false);
                        AssignmentText3.setVisible(false);
                        AssignmentText4.setVisible(false);
                        AssignmentText5.setVisible(false);
                        considerassignment.setVisible(false);
                        lblas1.setVisible(false);
                        lblas2.setVisible(false);
                        lblas3.setVisible(false);
                        lblas4.setVisible(false);
                        lblas5.setVisible(false);
                        lblconas.setVisible(false);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    Float[] marksarray = new Float[5];
    String stringassignment="";
    float quiz;

    String studentid;
    int considerquiz;
    String mid = "";
    float total;
    float assignment=0;
   private void AddCA(){
       int sum = 0;
       marksarray[0] = zero(quiz1.getText());
       marksarray[1] = zero(quiz2.getText());
       marksarray[2] = zero(quiz3.getText());
       marksarray[3] = zero(quiz4.getText());
       marksarray[4] = zero(quiz5.getText());
       Arrays.sort(marksarray);

       considerquiz = (int) zero(evaluatequiz.getText());
       for (int i = marksarray.length - 1; i >= marksarray.length - considerquiz; i--){
           sum += marksarray[i];
       }

       quiz = (float) (sum / considerquiz);
       if (AssignmentText1.getText().isEmpty() && AssignmentText2.getText().isEmpty()&& AssignmentText3.getText().isEmpty() && AssignmentText4.getText() .isEmpty() && AssignmentText5.getText() .isEmpty()) {
           stringassignment = "N/A";
       }
       else{
           marksarray[0] = zero(AssignmentText1.getText());
           marksarray[1] = zero(AssignmentText2.getText());
           marksarray[2] = zero(AssignmentText3.getText());
           marksarray[3] = zero(AssignmentText4.getText());
           marksarray[4] = zero(AssignmentText5.getText());
           Arrays.sort(marksarray);
          int considerquiz = Integer.parseInt(considerassignment.getText());
           sum = 0;
           for (int i = marksarray.length - 1; i >= marksarray.length - considerquiz; i--) {
               sum += marksarray[i];
           }
           assignment = (float) (sum / considerquiz);
           stringassignment = String.valueOf(assignment);
       }

       if (MidText2.getText().isEmpty()) {
           mid = "N/A";
       }else{
       mid = MidText2.getText();
       }
        if( stringassignment.equals("N/A")&&mid.equals("N/A")) {
            total = quiz;
        }else if (mid.equals("N/A")) {
            total = (assignment + quiz) / 2;
        } else if (stringassignment.equals("N/A")) {
            total = (quiz + Float.parseFloat(mid)) / 2;
        } else {
            total = (quiz + Float.parseFloat(mid) + assignment) / 3;
        }

        String subject = (String) Subjectcombo.getSelectedItem();



       studentid = IDText.getText();
        String query = "INSERT INTO ca_marks (subject_id, student_id, quiz, assignment, mid, Total, Elegibaly) VALUES ('" + subject + "', '" + studentid + "', '" +decimalFormat.format(quiz) + "', '" + stringassignment + "', '" + mid + "', '" +decimalFormat.format(total) + "', '" + eligibal(total) + "')";
        dbconn.writeData(query);
        showcatable();
        ClearCA();
    }

   private void EditCA(){
       int sum = 0;
       marksarray[0] = zero(quiz1.getText());
       marksarray[1] = zero(quiz2.getText());
       marksarray[2] = zero(quiz3.getText());
       marksarray[3] = zero(quiz4.getText());
       marksarray[4] = zero(quiz5.getText());
       Arrays.sort(marksarray);

       considerquiz = (int) zero(evaluatequiz.getText());
       for (int i = marksarray.length - 1; i >= marksarray.length - considerquiz; i--){
           sum += marksarray[i];
       }

       quiz = (float) (sum / considerquiz);
       if (AssignmentText1.getText().isEmpty() && AssignmentText2.getText().isEmpty()&& AssignmentText3.getText().isEmpty() && AssignmentText4.getText() .isEmpty() && AssignmentText5.getText() .isEmpty()) {
           stringassignment = "N/A";
       }
       else{
           marksarray[0] = zero(AssignmentText1.getText());
           marksarray[1] = zero(AssignmentText2.getText());
           marksarray[2] = zero(AssignmentText3.getText());
           marksarray[3] = zero(AssignmentText4.getText());
           marksarray[4] = zero(AssignmentText5.getText());
           Arrays.sort(marksarray);
           int considerquiz = Integer.parseInt(considerassignment.getText());
           sum = 0;
           for (int i = marksarray.length - 1; i >= marksarray.length - considerquiz; i--) {
               sum += marksarray[i];
           }
           assignment = (float) (sum / considerquiz);
           stringassignment = String.valueOf(assignment);
       }

       if (MidText2.getText().isEmpty()) {
           mid = "N/A";
       }else{
           mid = MidText2.getText();
       }
       if( stringassignment.equals("N/A")&&mid.equals("N/A")) {
           total = quiz;
       }else if (mid.equals("N/A")) {
           total = (assignment + quiz) / 2;
       } else if (stringassignment.equals("N/A")) {
           total = (quiz + Float.parseFloat(mid)) / 2;
       } else {
           total = (quiz + Float.parseFloat(mid) + assignment) / 3;
       }
       String subject = (String) Subjectcombo.getSelectedItem();

       studentid = IDText.getText();
       String query = "UPDATE ca_marks SET quiz = '" + quiz + "', assignment = '" + assignment + "', mid = '" + mid + "', Total = '" + total + "', Elegibaly = '" + eligibal(total)+ "' WHERE student_id = '" + studentid + "' AND subject_id = '" + subject + "'";
       dbconn.writeData(query);
       showcatable();
       ClearCA();
    }

   private void DeleteCA(){
        String studentid = IDText.getText();
        String query = "DELETE FROM ca_marks WHERE student_id = '" + studentid + "'";
        dbconn.writeData(query);
        showcatable();
        ClearCA();
    }
    private void showcatable(){
        try{
           Query = "SELECT * FROM `ca_marks` WHERE subject_id='"+returnsubject()+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Query);
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Student ID", "Quiz", "Assignment", "Mid","Total","Elegibaly"},
                    0);

            while (rs.next()){
                String studentId = rs.getString("student_id");
                String quiz = rs.getString("quiz");
                String assignment = rs.getString("assignment");
                String mid = rs.getString("mid");
                String total = rs.getString("Total");
                String elegibal = rs.getString("Elegibaly");

                model.addRow(new Object[]{ studentId, quiz, assignment,mid,total,elegibal});
            }
            resulttable.setModel(model);
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("database connection lose"+e);
        }
    }
   private void ClearCA() {
        IDText.setText("");
        MidText2.setText("");
        AssignmentText1.setText("");
        AssignmentText5.setText("");
        evaluatequiz.setText("");
        quiz1.setText("");
        quiz2.setText("");
        quiz3.setText("");
        quiz4.setText("");
        quiz5.setText("");
    }
  private void FinalCALoad(){
        int selectedRow = resulttable.getSelectedRow();
        String studentId;
        if (selectedRow != -1){
            DefaultTableModel model = (DefaultTableModel) resulttable.getModel();
            studentId = model.getValueAt(selectedRow, 1).toString();
            String mid = (model.getValueAt(selectedRow, 3).toString());
            MidText2.setText(String.valueOf(mid));
            IDText.setText(String.valueOf(studentId));
        }
    }
private void loadtablefinal(){
        int selectedRow = finalmarkstable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) finalmarkstable.getModel();
            String stuId = model.getValueAt(selectedRow, 0).toString();
            Float finalmarks = Float.parseFloat(model.getValueAt(selectedRow, 2).toString());
            ID_Text_Final.setText(String.valueOf(stuId));
            TextFinalPractical.setText(String.valueOf(finalmarks));
        }
    }
    void addfinalmarks() throws SQLException{
        String finaltheorystring="0";
        String Stuid = ID_Text_Final.getText();
        String subject = Objects.requireNonNull(Subjectcombo.getSelectedItem()).toString();
        float theory;
        float practical;
        if(ID_Text_Final.getText().isEmpty()&&Textfinaltheory.getText().isEmpty()&&TextFinalPractical.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all field ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(ID_Text_Final.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill id field ", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (Textfinaltheory.getText().isEmpty()&&TextFinalPractical.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Add marks field ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(Textfinaltheory.getText().isEmpty()) {
                theory=0.0f;
            }
            else{
                theory=Float.parseFloat( Textfinaltheory.getText());
            }
            if(TextFinalPractical.getText().isEmpty()){
                practical=0.0f;
            }
            else{
                practical=Float.parseFloat( TextFinalPractical.getText());
            }
            FinalMarksCalculator x=new FinalMarksCalculator();
            x.calculateAndSaveFinalMarks(Stuid,subject,theory,practical);

            showendtable();
        }

    }
    void editfinalmarks(){
          String finaltheorystring="0";
        String Stuid = ID_Text_Final.getText();
        String subject = Objects.requireNonNull(Subjectcombo.getSelectedItem()).toString();
        float theory;
        float practical;
        if(ID_Text_Final.getText().isEmpty()&&Textfinaltheory.getText().isEmpty()&&TextFinalPractical.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all field ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(ID_Text_Final.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill id field ", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (Textfinaltheory.getText().isEmpty()&&TextFinalPractical.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Add marks field ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(Textfinaltheory.getText().isEmpty()) {
                theory=0.0f;
            }
            else{
                theory=Float.parseFloat( Textfinaltheory.getText());
            }
            if(TextFinalPractical.getText().isEmpty()){
                practical=0.0f;
            }
            else{
                practical=Float.parseFloat( TextFinalPractical.getText());
            }
            FinalMarksCalculator y=new FinalMarksCalculator();
            try {
                y.calculateAndupdateFinalMarks(Stuid,subject,theory,practical);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            showendtable();
        }

    }
    void Deletefinalmarks(){
        String Stuid= ID_Text_Final.getText();
        String subject= Objects.requireNonNull(Subjectcombo.getSelectedItem()).toString();
        String query = "DELETE FROM final_marks WHERE stuid = '" + Stuid + "' AND subid = '" + subject + "'";
        dbconn.writeData(query);
       showendtable();
    }
    public void showendtable(){
        try {
            String query = "SELECT * FROM `final_marks` WHERE subid='"+returnsubject()+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"stuid","Theory","FinalMarks","Total","Grade"},
                    0);
            while (rs.next()){
                String studentId = rs.getString("stuid");
                String theory = rs.getString("Theory");
                String finalmarks = rs.getString("finalMarks");
                String total = rs.getString("Total");
                String grade = rs.getString("Grade");
                // Add the data as a new row to the model
                model.addRow(new Object[]{studentId,theory,finalmarks,total,grade});
            }
            finalmarkstable.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error Occur in CA Table"+e);
        }
}
private String returnsubject(){
       return (String) Subjectcombo.getSelectedItem();

}
    public float zero(String value) {
        if (value.isEmpty()) {
            return 0;
        } else {
            return Float.parseFloat(value);
        }

    }
private String eligibal(float total){
    String Is_Elegi;
        if (total >= 50) {
        Is_Elegi = "Eligible";
    } else {
        Is_Elegi = "Not Eligible";
    }
        return Is_Elegi;
}}