import java.sql.*;

public class FinalMarksCalculator{
    DBConnector conn=new DBConnector();
    public float calculateAndSaveFinalMarks(String studentID, String subjectID,Float finaltheory,Float finalpractical) {
        float total = 0;
        try {
            String query = "select  * from ca_marks WHERE student_id = '" + studentID + "' AND subject_id = '" + subjectID + "'";
            Connection connection = conn.getConnection();
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);
            while (rs2.next()) {
                float quiz = zero(rs2.getString("quiz"));
                float assignment = zero(rs2.getString("assignment"));
                float mid = zero(rs2.getString("mid"));
                float quizpercentage = getpersentage("quiz", subjectID);
                float midpercentage = getpersentage("mid", subjectID);
                float assignmentpercentage = getpersentage("assignment", subjectID);
                float Finaltheorypercentage = getpersentage("Final_theory", subjectID);
                float Finalpracticalpercentage = getpersentage("Final_practical", subjectID);
                total = (quiz * quizpercentage) +
                        (assignment * assignmentpercentage) +
                        (mid * midpercentage) +
                        (finaltheory * Finaltheorypercentage) +
                        (finalpractical * Finalpracticalpercentage);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
    public float getpersentage(String field,String subjectID) throws SQLException {
        String query = "select  " + field + " from marks_priority WHERE subject_id = '" + subjectID + "'";
        Connection connection = conn.getConnection();
        Statement stmt2 = connection.createStatement();
        ResultSet rs2 = stmt2.executeQuery(query);
        float fields = 0;
        while (rs2.next()) {
            fields = zero(rs2.getString(field));
        }
        return fields;
    }
    public float zero(String value){
        if (value=="N/A"){
            return 0;
        }
        else{
            return Float.valueOf(value);
        }
    }}




