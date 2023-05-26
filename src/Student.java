import javax.swing.*;
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
    private JButton EXAMINATIONSButton;
    private JPanel profile;
    private JLabel profilepicture;
    private JPanel accountDetails;
    private JLabel address;
    private JLabel conNumber;
    private JLabel email;
    private JButton editProfileButton;
    private JLabel fullname;

    public Student() {
        setVisible(true);
        setSize(750, 650);
        setTitle("Student");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(Student);

        showProfileDetails();


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logging log2 = new Logging();

            }

        });

        NORTICEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Notice notice1 = new Notice();
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EditProfile edit = new EditProfile();
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
                String contact = rs.getString("contact_number");
                conNumber.setText(contact);
                String Email = rs.getString("email");
                email.setText(Email);
                setName(fname,lname);

            }

            con.close();
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
