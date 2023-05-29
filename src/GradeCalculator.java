public class GradeCalculator {
    public String calculateGrade(Float Total){
        String grade = "";
        if(Total <= 100 && Total >= 85){
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
        } else if (Total >= 0 && Total < 25) {
            grade = "E";
        }
        return grade;
    }
    public float calculater(String grade){
        float GPV=0.0f;
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
}

