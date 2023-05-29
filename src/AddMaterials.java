import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMaterials extends JFrame implements ActionListener {
    private JTextField textCourseID;
    private JTextArea textMateria;
    private JButton addButton;
    private JButton editButton;
    private JPanel panel;


    private static final String DB_URL = "jdbc:mysql://localhost:3306/teclms";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public AddMaterials() {
        setTitle("Details Form");

        setSize(750, 450);
        setVisible(true);
        panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Create the form components
        JLabel courseIDLabel = new JLabel("Course ID:");
        JLabel materialLabel = new JLabel("Material:");

        textCourseID = new JTextField(20);
        textMateria = new JTextArea(20, 5);

        addButton = new JButton("Add");
        editButton = new JButton("Modify");

        // Set action listeners for buttons
        addButton.addActionListener(this);
        editButton.addActionListener(this);


        panel.add(courseIDLabel);
        panel.add(textCourseID);
        panel.add(materialLabel);
        panel.add(new JScrollPane(textMateria));
        panel.add(addButton);
        panel.add(editButton);

        getContentPane().add(panel);

        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String courseID = textCourseID.getText();
            String material = textMateria.getText();

            addDetails(courseID, material);
        } else if (e.getSource() == editButton) {
            String courseID = textCourseID.getText();
            String material = textMateria.getText();

            modifyDetails(courseID, material);
        }
    }

    private void addDetails(String courseID, String material) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Create the SQL statement
            String sql = "INSERT INTO course (course_id, material_name) VALUES (?, ?)";

            // Create the prepared statement
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameter values
            statement.setString(1, courseID);
            statement.setString(2, material);

            // Execute the statement
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Details added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding details to the database!");
        }
    }

    private void modifyDetails(String courseID, String material) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Create the SQL statement
            String sql = "UPDATE course SET material_name = ? WHERE course_id = ?";

            // Create the prepared statement
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameter values
            statement.setString(1, material);
            statement.setString(2, courseID);

            // Execute the statement
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Details modified successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error modifying details in the database!");
        }
    }

}
