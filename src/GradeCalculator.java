import java.sql.*;
import java.text.DecimalFormat;

public class GradeCalculator {
    public GradeCalculator() throws SQLException{
    }
    public String calculateGrade(Float Total){
        String grade = "";
        if (Total <= 100 && Total >= 85) {
            grade = "A+";
        } else if (Total >= 70) {
            grade = "A";
        } else if (Total >= 65) {
            grade = "A-";
        } else if (Total >= 60) {
            grade = "B+";
        } else if (Total >= 55) {
            grade = "B";
        } else if (Total >= 50) {
            grade = "B-";
        } else if (Total >= 45) {
            grade = "C+";
        } else if (Total >= 40) {
            grade = "C";
        } else if (Total >= 35) {
            grade = "C-";
        } else if (Total >= 30) {
            grade = "D+";
        } else if (Total >= 25) {
            grade = "D";
        } else if (Total < 25&&Total >= 0) {
            grade = "E";
        }
        return grade;
    }
    public float grandpoint(String grade){
        float GPV = 0.0f;
        if (grade.equals("A+")) {
            GPV = 4;
        } else if (grade.equals("A")) {
            GPV = 4;
        } else if (grade.equals("A-")) {
            GPV = 3.7F;
        } else if (grade.equals("B+")) {
            GPV = 3.3F;
        } else if (grade.equals("B")) {
            GPV = 3;
        } else if (grade.equals("B-")) {
            GPV = 2.7F;
        } else if (grade.equals("C+")) {
            GPV = 2.3F;
        } else if (grade.equals("C")) {
            GPV = 3;
        } else if (grade.equals("C-")) {
            GPV = 1.7F;
        } else if (grade.equals("D+")) {
            GPV = 1.3F;
        } else if (grade.equals("D")) {
            GPV = 1;
        } else if (grade.equals("E")) {
            GPV = 0;
        }
        return GPV;
    }
    DBConnector connec = new DBConnector();
    String semester;
    String level;
    int rowcount=0;
    DecimalFormat format=new DecimalFormat("#.#");
   private Connection dbconn = connec.getConnection();
    public void calculategpa(Float gp, String subid, String stuid) throws SQLException{
        int credit = 0;
        float newgpa, currentgpa=0;
        String gpastatus="";
        Statement stmt = dbconn.createStatement();
        ResultSet result = stmt.executeQuery("Select course_credit from course where course_id='" + subid + "' ");
        while(result.next()){
            credit= result.getInt("course_credit");
            System.out.println(credit);
        }

        result = stmt.executeQuery("Select gpastatus from course where course_id='" + subid + "'");
        while (result.next()){
            gpastatus = result.getString("gpastatus");
        }

        System.out.println(gpastatus);
        result = stmt.executeQuery("Select semester,level from course where course_id='" + subid + "'");
while (result.next()){
    semester = result.getString("semester");
    level= result.getString("level");
}
        System.out.println(semester+""+level);

        if (gpastatus.equals("sgpa")){

            result = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM gpa WHERE stuid='"+stuid+"' and level='"+level+"' and semester='"+semester+"'");
            while (result.next()){
                rowcount = result.getInt("rowcount");
            }
                if(rowcount==0){
                    System.out.println("im here");
                    connec.writeData("insert into gpa(stuid,sgpa, level, semester) values('"+stuid+"','"+format.format(gp)+"','"+level+"','"+semester+"')");
                }
                else{
                    result = stmt.executeQuery("SELECT sgpa FROM gpa WHERE stuid='" + stuid + "' AND semester='" + semester + "' AND level='" + level + "'");
                    while(result.next()){
                        currentgpa = result.getFloat("sgpa");
                    }
                    newgpa= gpacal(gp,currentgpa,credit);
                    connec.writeData("UPDATE gpa SET sgpa = " + format.format(newgpa) + ", level = '" + level + "', semester = '" + semester + "' WHERE stuid = '" + stuid + "'");
                }
        }
        else{
            result = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM gpa WHERE stuid='"+stuid+"' and level='"+level+"' and semester='"+semester+"'");
            while (result.next()){
                rowcount = result.getInt("rowcount");
            }
            if(rowcount==0){

                connec.writeData("insert into gpa(stuid, cgpa, level, semester) values('"+stuid+"','"+format.format(gp)+"','"+level+"','"+semester+"')");
            }
            else{
                result = stmt.executeQuery("SELECT cgpa FROM gpa WHERE stuid='" + stuid + "' AND semester='" + semester + "' AND level='" + level + "'");

                while(result.next()){
                    currentgpa = result.getFloat("cgpa");
                }
                newgpa= gpacal(gp,currentgpa,credit);
                connec.writeData("UPDATE gpa SET cgpa = " +format.format(newgpa)  + ", level = '" + level + "', semester = '" + semester + "' WHERE stuid = '" + stuid + "'");
            }
            }




        }


    float gpacal(float total,float currentgpa, int credit) {
        float gpa;
        if (currentgpa == 0) {
            gpa = (total / credit);
        } else {
            gpa = ((total / credit) + currentgpa) / 2;
        }
        return gpa;
    }
}