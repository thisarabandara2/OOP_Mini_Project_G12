import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gpasend{
    DatabaseConnection dbconn=new DatabaseConnection();
    void sendgpa(String subject,String student,Float gpa) throws SQLException {
        String query = "select gpastates from course where cource_name="+subject+"";
        dbconn.writeData(query);
        boolean iscgpa=false;
        String cgpa="";
       Connection conn= dbconn.getConnection();
        Statement stmt= conn.createStatement();
        ResultSet rs2 = stmt.executeQuery(query);
        while(rs2.next()){
            cgpa = rs2.getString("gpastatus");
        }
        if(cgpa=="cgpa"){
            query="insert into gpa(student_id,subject_id,sgpa,cgpa)values('"+student+"','"+subject+"',"+gpa+",)";
        }
        else{
            query="insert into gpa(student_id,subject_id,sgpa,cgpa)values('"+student+"','"+subject+"',,"+gpa+")";
        }
        dbconn.writeData(query);
    }
}
