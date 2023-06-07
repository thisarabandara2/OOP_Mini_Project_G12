import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TechnicaOfficer extends JFrame {
    private JButton UPDATEPROFILEButton;
    private JButton ADDANDMAINTAINMEDICALButton;
    private JButton NOTICEButton;
    private JButton ADDANDMAINTAINATTENDANCEButton;
    private JButton TIMETABLESButton;
    private JPanel panel;

    TechnicaOfficer(){

        setVisible(true);
        setTitle("Technical Officer");
        setSize(1000,800);
        add(panel);

        NOTICEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeeNotices note = new SeeNotices();
            }
        });
        TIMETABLESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeeTimeTables time = new SeeTimeTables();
            }
        });
        ADDANDMAINTAINATTENDANCEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add attendance part
            }
        });
        UPDATEPROFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProfile edit = new editProfile();
            }
        });
        ADDANDMAINTAINMEDICALButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMedical med = new AddMedical();
            }
        });
    }
}
