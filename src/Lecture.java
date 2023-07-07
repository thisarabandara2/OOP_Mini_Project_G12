import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Lecture extends JFrame{

    private static String Fullname;
    private JButton updateProfileButton;
    private JButton courseMaterealButton;
    private JButton maintainMarksButton;
    private JButton studentDetailsButton;
    private JButton studentEligibilityButton;
    private JButton MarksButton;
    private JButton studentAttendanceButton;
    private JButton noticesButton;
    private JPanel Lecture;
    private JLabel Name;
    private JLabel profilepicture;
    private JLabel fullname;
    private JLabel address;
    private JLabel conNumber;
    private JLabel email;
    private JButton studentMedicalesButton;

    public Lecture() {
        setContentPane(Lecture);
        setTitle("Lecture");
        setBounds(600, 200, 600, 300);
        setVisible(true);

        showProfileDetails();
        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        Lectureupdate update=new Lectureupdate();
            }
        });
        studentEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        courseMaterealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Addmatirial mat=new Addmatirial();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        MarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Marks marks=new Marks();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

       // studentAttendanceButton.addActionListener(new ActionListener() {
           // @Override
           // public void actionPerformed(ActionEvent e) {
              //  Attendance att = new Attendance();
           // }
        //});
        studentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ViewStudents vss =new ViewStudents();
            }
        });
        noticesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeeNotices notice = new SeeNotices();
            }
        });

        studentMedicalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Viewmedicals Viewmedicals = new Viewmedicals();
            }
        });

        studentEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEligibal el = new ViewEligibal();
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM user where user_id ='" + username + "'");


            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                Name.setText("Welcome  " + fname + " " + lname);
                fullname.setText(fname + " " + lname);
                String address1 = rs.getString("address");
                address.setText(address1);
                String contact = rs.getString("contactnumber");
                conNumber.setText(contact);
                String Email = rs.getString("email");
                email.setText(Email);
                setName(fname, lname);


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
        Fullname = fname + " " + lname;
    }
}

