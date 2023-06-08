import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Elegibility {
    DBConnector db=new DBConnector();
    Connection conn= db.getConnection();
    Statement stmt = conn.createStatement();
String query="";
    public Elegibility() throws SQLException {
    }
    public void studentattenpresent(String studentid,String subject) throws SQLException {
        query = "select course_type from course where course_id='" + subject + "' ";
        Statement stmt = conn.createStatement();
        ResultSet re = stmt.executeQuery(query);
        String sub = null;
        while (re.next()){
            sub = re.getString("course_type");
        }
        int totaldays=0;
        int presentdays=0;
        int approvemedical=0;
        int total=0;
        switch (sub){
            case "practical":
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'";
                totaldays=  totaldays(query);
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and student_id='"+studentid+"'";
              presentdays=presentdays(query);
                double theoryAttendance=(presentdays*100/totaldays);
                query = "SELECT COUNT(*) AS total FROM medical WHERE course_id = '"+subject+"' and student_id='"+studentid+"'";
                approvemedical=presentdays(query);
                total=(presentdays+approvemedical)*100/totaldays;

                query = "INSERT INTO attendancepercentage (subject_id, student_id, practical, total,with_medicalpractical,with medical total) VALUES ('" + subject + "', '" + studentid + "', " + theoryAttendance + ", " + theoryAttendance +"', " + total + ", " + total + ")";
                db.writeData(query);

                break;
            case "theory":
                 query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'";

                totaldays=  totaldays(query);
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'and student_id='"+studentid+"'";

                presentdays=presentdays(query);
               int practicalAttendance=(presentdays*100)/totaldays;
                query = "SELECT COUNT(*) AS total FROM medical WHERE course_id = '"+subject+"' and student_id='"+studentid+"'";
                approvemedical=presentdays(query);
                total=(presentdays+approvemedical)*100/totaldays;

                query = "INSERT INTO attendancepercentage (subject_id, student_id, theory, total,with_medicaltheory,with medical total) VALUES ('" + subject + "', '" + studentid + "', " + practicalAttendance + ", " + practicalAttendance + "', " +total + ", " + total + ")";
                db.writeData(query);
                break;
            case "both":
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and type='theory'";
                int  totaltheorydays=  totaldays(query);
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'and student_id='"+studentid+"' and type='theory'";
                int   presenttheorydays=presentdays(query);
                int theorypersentage=(presenttheorydays/totaltheorydays)*100;

                query = "SELECT COUNT(*) AS total FROM medical WHERE course_id = '"+subject+"' and student_id='"+studentid+"and type='theory'";
                approvemedical=presentdays(query);
              int  totaltheory=(presentdays+approvemedical)*100/totaldays;

                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and type='practical'";
                 int  totalpracticaldays=  totaldays(query);
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'and student_id='"+studentid+"' and type='practical'";
                int   presentpracticaldays=presentdays(query);
                int practicalpersentage=presentpracticaldays/totalpracticaldays*100;
                int totalattendance=(theorypersentage+practicalpersentage)/2;
                query = "SELECT COUNT(*) AS total FROM medical WHERE course_id = '"+subject+"' and student_id='"+studentid+"'";
                approvemedical=presentdays(query);
              int  totalprac=(presentdays+approvemedical)*100/totaldays;
                int totalmedi=(totaltheory+totalprac)/2;
                query ="INSERT INTO attendancepercentage (subject_id, student_id, theory,practical, total,with_medicaltheory,with_medicalpractical,with medical total) VALUES ('" + subject + "', '" + studentid + "', " + theorypersentage + "," + practicalpersentage + "," + totalattendance +"', " + totaltheory + "," + totalprac + "," + totalmedi + ")";
                db.writeData(query);
                break;
            default:
                // Code for handling other cases
                break;
        }
    }
    private int totaldays(String query) throws SQLException {
        int count = 0;
          ResultSet re = stmt.executeQuery(query);
        if (re.next()) {
            count = re.getInt("total");
        }
        return count;
    }
    private int presentdays(String query) throws SQLException {
        int count = 0;
          ResultSet re = stmt.executeQuery(query);
        if (re.next()) {
            count = re.getInt("total");
        }
        return count;
    }

}
