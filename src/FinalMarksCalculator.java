import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;

public class FinalMarksCalculator{
    DBConnector conn=new DBConnector();
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    String query="";

    public void calculateAndSaveFinalMarks(String studentID, String subjectID,Float Final_Theory,Float Final_Practical) throws SQLException{
        float total, quiz = 0, mid = 0, assignment = 0;
        int count=0;
        query = "select quiz,mid,assignment from ca_marks WHERE student_id = '" + studentID + "' AND subject_id = '" + subjectID + "'";
            Connection connection = conn.getConnection();
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);
            while(rs2.next()){
                quiz = zero( rs2.getString("quiz"));
                mid =zero( rs2.getString("mid"));
                assignment =zero( rs2.getString("assignment"));
                count = count+1;
            }

            if(count==0){
                System.out.println("fill again");
            }else{
                float Quiz_Percentage= Subject_Marks_weight("quiz",subjectID);
                float Assignment_Percentage= Subject_Marks_weight("assignment",subjectID);
                float Mid_Percentage= Subject_Marks_weight("mid",subjectID);
                float finaltheorypercentage= Subject_Marks_weight("final_theory",subjectID);
                float Final_Practical_Percentage= Subject_Marks_weight("final_practical",subjectID);

                total = (quiz * Quiz_Percentage/100) +
                        (assignment * Assignment_Percentage/100) +
                        (mid * Mid_Percentage/100) +
                        (Final_Theory * finaltheorypercentage/100) +
                        (Final_Practical * Final_Practical_Percentage/100);
                GradeCalculator Grade_Calculate=new GradeCalculator();
                String grade= Grade_Calculate.calculateGrade(total);
                String finaltheorystr=na(Final_Theory);
                String finalpracticalstr=na(Final_Practical);

        query="insert into final_marks values('"+studentID+"','"+subjectID+"','"+finalpracticalstr+"','"+finaltheorystr+"',"+decimalFormat.format(total)+",'"+grade+"'); ";
        conn.writeData(query);

            float gp= Grade_Calculate.grandpoint(grade);
            GradeCalculator gpa=new GradeCalculator();
            gpa.calculategpa(gp,subjectID,studentID);
            }
        }
    public void calculateAndupdateFinalMarks(String studentID, String subjectID,Float Final_Theory,Float Final_Practical) throws SQLException{
        float total, quiz = 0, mid = 0, assignment = 0;
        int count=0;
        query = "select quiz,mid,assignment from ca_marks WHERE student_id = '" + studentID + "' AND subject_id = '" + subjectID + "'";
        Connection connection = conn.getConnection();
        Statement stmt2 = connection.createStatement();
        ResultSet rs2 = stmt2.executeQuery(query);
        while(rs2.next()){
            quiz = zero( rs2.getString("quiz"));
            mid =zero( rs2.getString("mid"));
            assignment =zero( rs2.getString("assignment"));
            count = count+1;
        }

        if(count==0){
            System.out.println("fill again");
        }else{
            float Quiz_Percentage= Subject_Marks_weight("quiz",subjectID);
            float Assignment_Percentage= Subject_Marks_weight("assignment",subjectID);
            float Mid_Percentage= Subject_Marks_weight("mid",subjectID);
            float finaltheorypercentage= Subject_Marks_weight("final_theory",subjectID);
            float Final_Practical_Percentage= Subject_Marks_weight("final_practical",subjectID);

            total = (quiz * Quiz_Percentage/100) +
                    (assignment * Assignment_Percentage/100) +
                    (mid * Mid_Percentage/100) +
                    (Final_Theory * finaltheorypercentage/100) +
                    (Final_Practical * Final_Practical_Percentage/100);
            GradeCalculator Grade_Calculate=new GradeCalculator();
            String grade= Grade_Calculate.calculateGrade(total);
            String finaltheorystr=na(Final_Theory);
            String finalpracticalstr=na(Final_Practical);

           query="UPDATE final_marksSET subid = '"+subjectID+"',finalMarks = '"+finalpracticalstr+"', Theory = '"+finaltheorystr+"', Total = "+decimalFormat.format(total)+", Grade = '"+grade+"' WHERE stuid = '"+studentID+"'";
           conn.writeData(query);

            float gp= Grade_Calculate.grandpoint(grade);
            GradeCalculator gpa=new GradeCalculator();
            gpa.calculategpa(gp,subjectID,studentID);
        }
    }
    public float Subject_Marks_weight(String field, String subjectID)throws SQLException{
        String query = "select  " + field + " from marks_priority WHERE subject_id = '" + subjectID + "'";
        Connection connection = conn.getConnection();
        Statement stmt2 = connection.createStatement();
        ResultSet rs2 = stmt2.executeQuery(query);
        float percentage = 0;
        while (rs2.next()) {
            percentage = empty(rs2.getString(field));
        }
        return percentage;
    }
    public float zero(String value){
        if (value.equals("N/A")){
            return 0;
        }
        else{
            return Float.parseFloat(value);
        }
    }
    public String na(float value){
        if (value==0.0){
            return "N/A";
        }
        else{
            return String.valueOf(value);
        }
    }
    public float empty(String value){
        if (value.isEmpty()){
            return 0;
        }
        else{
            return Float.parseFloat(value);
        }
    }}







