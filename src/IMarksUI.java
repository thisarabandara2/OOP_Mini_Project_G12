import javax.swing.*;

import java.awt.event.ActionListener;
import java.sql.*;


public interface IMarksUI {
    void setContentPane(JPanel contentPane);
    void setTitle(String title);
    void setBounds(int x, int y, int width, int height);
    void setVisible(boolean visible);
    void clearButtonActionListener(ActionListener listener);
    void AddButtonActionListener(ActionListener listener);
    void addQuizButtonActionListener(ActionListener listener);
    void EditButtonActionListener(ActionListener listener);
    void displayTable(String query) throws SQLException;
}