import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Student extends JFrame {
    private static String Fullname;
    private JLabel logoimage;
    private JButton logOutButton;
    private JPanel Student;
    private JLabel Name;
    private JButton NORTICEButton;
    private JButton MYCOURSESButton;
    private JButton TIMETABLESButton;
    private JPanel profile;
    private JLabel profilepicture;
    private JPanel accountDetails;
    private JLabel address;
    private JLabel conNumber;
    private JLabel email;
    private JButton editProfileButton;
    private JLabel fullname;
    private JButton MYATTENDANCEButton;
    private JPanel proPannel;
    private JButton MYMEDICLESButton;
    private JButton MYGPAButton;

    public Student() {
        setVisible(true);
        setSize(750, 700);
        setTitle("Student");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(Student);

        showProfileDetails();







        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }

        });

        NORTICEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SeeNotices notice1 = new SeeNotices();
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Updatestudent update = new Updatestudent();
            }
        });
        MYATTENDANCEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attendance attendance = new Attendance();
            }
        });
        MYCOURSESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courceDetails obj = new courceDetails();
            }
        });
        TIMETABLESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeeTimeTables table = new SeeTimeTables();
            }
        });
        MYMEDICLESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Viewstmedical medi = new Viewstmedical();
            }
        });
        MYGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentGPA gpa = new StudentGPA();
                dispose();
            }
        });
    }
    private void showProfileDetails() {

        String username = User.getUserin();

        String url = "jdbc:mysql://localhost:3306/tecmis";
        String user = "root";
        String password = "";



        try {
            Connection con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the MySQL database");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user where user_id ='"+username+"'");




            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                Name.setText("Welcome  " + fname +" " + lname);
                fullname.setText(fname +" " + lname);
                String address1 = rs.getString("address");
                address.setText(address1);
                String contact = rs.getString("contactnumber");
                conNumber.setText(contact);
                String Email = rs.getString("email");
                email.setText(Email);
                setName(fname,lname);


                String filePath = rs.getString("profile_picture");
                ImageIcon imageIcon = new ImageIcon(filePath);
                Image originalImage = imageIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(200, 220, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                profilepicture.setIcon(resizedIcon);


            }


            //System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("error pass here");
            System.err.println("Error connecting to the database: " + e.getMessage());
        }

    }

    public void setName(String fname, String lname) {
        Fullname = fname +" "+ lname;
    }
    public static String getFullName(){
        return Fullname;
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoimage = new JLabel(new ImageIcon("UOR.png"));
        profilepicture = new JLabel(new ImageIcon("PP.jpg"));
    }
}
