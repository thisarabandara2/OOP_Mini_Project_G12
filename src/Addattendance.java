import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Addattendance extends JFrame{
    private JTextField Stuidtext;
    private JTextField subidtext;
    private JTextField starttimetext;
    private JTextField datetext;
    private JTextField endtimetext;
    private JButton addButton;
    private JButton clearButton;
    private JButton deleteButton;
    private JLabel date;
    private JLabel gjf;
    private JPanel add;
    private String tecofficerID="saman";
    DBConnector database=new DBConnector();
    String query="";
    public Addattendance() {

        setVisible(true);
        setSize(450,350);
        add(add);
        setContentPane(add);
    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve input values from text fields
                String studentId = Stuidtext.getText();
                String subjectId = subidtext.getText();
                String startTime = starttimetext.getText();
                String date = datetext.getText();
                String endTime = endtimetext.getText();

                // Check if any of the input values are null or empty
                if (studentId.isEmpty() || subjectId.isEmpty() || startTime.isEmpty() || date.isEmpty() || endTime.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                else{
                    query="insert into attendance (date,time,technical_officer_id,course_id,student_id,end_time) values ('"+date+"','"+startTime+"','"+User.getUserin()+"','"+subjectId+"','"+studentId+"','"+endTime+"');";
                    database.writeData(query);
                }
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Stuidtext.setText("");subidtext.setText("");starttimetext.setText("");datetext.setText("");endtimetext.setText("");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                query="delete attandance where stuid=studentid subid=subjectId";
                database.writeData(query);
            }
        });
    }
}
