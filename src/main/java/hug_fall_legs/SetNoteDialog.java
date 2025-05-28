package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SetNoteDialog extends JDialog{
    private JLabel noteLabel;
    private JTextField noteField;
    private JButton sureButton;
    private JPanel surPanel;

    public SetNoteDialog() {
        System.out.println("總結");
        setTitle("總結");
        setLayout(new GridLayout(3,1));
        setSize(400,150);
        setLocationRelativeTo(null);

        noteLabel = new JLabel("總結:");
        noteField = new JTextField();

        sureButton = new JButton("確認");
        surPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        surPanel.add(sureButton);

        add(noteLabel);
        add(noteField);
        add(surPanel);

        sureButton.addActionListener(e -> {
            dispose();
        });
    }

    public String getNoteText() {
        return noteField.getText().trim(); // trim是去掉前後空白
    }

}
