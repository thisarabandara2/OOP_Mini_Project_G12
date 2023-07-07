import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Elegibility{
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
        int totaldays;
        int presentdays;
        int approvemedical;
        int total;
        String status=null;
        switch (sub){
            case "theory":
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and type='theory'";
                totaldays=  totaldays(query);
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and student_id='"+studentid+"' and type='theory'";
              presentdays=presentdays(query);
                double theoryAttendance=(presentdays*100/totaldays);
                query = "SELECT COUNT(*) AS total FROM medical WHERE subjects = '"+subject+"' and tg='"+studentid+"' and type='theory'";
                approvemedical=presentdays(query);
                total=(presentdays+approvemedical)*100/totaldays;
                if(total>=80){
                    status="Eligibal";
                }
                else{
                    status="Not Eligibal";
                }
               query="INSERT INTO attendancepercentage VALUES ('"+subject+"', '"+studentid+"', '"+theoryAttendance+"', NULL, '"+theoryAttendance+"', '"+total+"', NULL, '"+total+"', '"+status+"')";
                db.writeData(query);

                break;
            case "practical":
                 query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and type='practical'";

                totaldays=  totaldays(query);
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'and student_id='"+studentid+"' and type='practical'";

                presentdays=presentdays(query);
               int practicalAttendance=(presentdays*100)/totaldays;
                query = "SELECT COUNT(*) AS total FROM medical WHERE subjects = '"+subject+"' and tg='"+studentid+"' and type='practical'";
                approvemedical=presentdays(query);
                total=(presentdays+approvemedical)*100/totaldays;
                if(total>=80){
                    status="Eligibal";
                }
                else{
                    status="Not Eligibal";
                }


                query="INSERT INTO attendancepercentage VALUES ('"+subject+"', '"+studentid+"',NULL,'"+practicalAttendance +"' , '"+practicalAttendance+"',NULL,'"+total+"' , '"+total+"', '"+status+"')";
                db.writeData(query);
                break;
            case "both":
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and type='theory'";
                int  totaltheorydays=  totaldays(query);
                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'and student_id='"+studentid+"' and type='theory'";
                int   presenttheorydays=presentdays(query);
                int theorypersentage=(presenttheorydays/totaltheorydays)*100;

                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"' and type='practical'";
                int  totalpracticaldays=  totaldays(query);

                query = "SELECT COUNT(*) AS total FROM attendance WHERE course_id = '"+subject+"'and student_id='"+studentid+"' and type='practical'";
                int   presentpracticaldays=presentdays(query);
                int practicalpersentage=(presentpracticaldays*100)/totalpracticaldays;

                int totalattendance=(theorypersentage+practicalpersentage)/2;
                query = "SELECT COUNT(*) AS total FROM medical WHERE subjects = '"+subject+"' and tg='"+studentid+"'and type='theory'";
                approvemedical=presentdays(query);
                int  totaltheory=(presenttheorydays+approvemedical)*100/totaltheorydays;


                query = "SELECT COUNT(*) AS total FROM medical WHERE subjects = '"+subject+"' and tg='"+studentid+"'and type='practical'";
                approvemedical=presentdays(query);
                      int  totalprac=(presentpracticaldays+approvemedical)*100/totalpracticaldays;
                int totalmedi=(totaltheory+totalprac)/2;
                if(totalmedi>=80){
                    status="Eligibal";
                }
                else{
                    status="Not Eligibal";
                }
                query="INSERT INTO attendancepercentage VALUES ('"+subject+"', '"+studentid+"','"+theorypersentage +"' ,'"+practicalpersentage +"' , '"+totalattendance +"', '"+totaltheory+"','"+totalprac +"' , '"+totalmedi+"', '"+status+"')";
                db.writeData(query);
                break;

        }
        query="select Elegibaly from ca_marks where student_id='"+studentid+"' and subject_id='"+subject+"'";
        String caeligibal=null;
         re = stmt.executeQuery(query);
        if (re.next()){
            caeligibal = re.getString("Elegibaly");
        }
        if(caeligibal.equals("Eligibal")&& status.equals("Eligibal")){
            query="insert into final_eligibal values('"+subject+"','"+studentid+"','"+caeligibal+"','"+status+"','Eligibal')";
        } else if (caeligibal.equals("Not Eligibal")&& status.equals("Eligibal")) {
            query="insert into final_eligibal values('"+subject+"','"+studentid+"','Not Eligibal','"+status+"','Not Eligibal')";

        }
        else if (caeligibal.equals("Eligibal")&& status.equals("Not Eligibal")) {
            query="insert into final_eligibal values('"+subject+"','"+studentid+"','Eligibal','"+status+"','Not Eligibal')";

        }
        else{
            query="INSERT INTO final_eligibal VALUES ('"+subject+"','"+studentid+"','Not Eligibal','"+status+"','Not Eligibal')";
        }
        db.writeData(query);
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
