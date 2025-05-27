/*package hug_fall_legs;

import java.awt.*;
import javax.security.auth.Subject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class recordPanel extends JPanel {
    JLabel totalLabel;
    JLabel thisSubject;
    JLabel thisTime;
    JLabel thisNote;
    JLabel subjectLabel;
    JLabel timeLabel;
    JLabel noteLabel;
    JLabel subjectIcon;
    JLabel timeIcon;
    JLabel noteIcon;
    JLabel subjectIcon2;
    
    public recordPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(new EmptyBorder(0, 0, 20, 0));
        ReviewStatistics array = new ReviewStatistics();
        for(ReviewSession s:array){
            thisSubject = new JLabel(s.getSubject());
            thisTime = new JLabel(s.getDurationTime());
            thisNote = new JLabel(s.getNotes());
            JPanel sessionPanel = new JPanel(new GridLayout(3, 1));
            sessionPanel.setBorder(BorderFactory.createTitledBorder("複習紀錄"));
            sessionPanel.add(subjectLabel);
            sessionPanel.add(timeLabel);
            sessionPanel.add(noteLabel);

            add(sessionPanel);
        }

    }
}
*/
