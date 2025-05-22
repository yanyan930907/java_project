package hug_fall_legs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// 時間區面板
public class TimePanel extends JPanel {

    public JLabel hoursLabel;
    public JLabel minutesLabel;
    public JLabel secondsLabel;
    JButton stopTimeButton;
    JButton setTimeButton;

    public TimePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(new EmptyBorder(0, 0, 20, 0));

        hoursLabel = new JLabel("00");
        minutesLabel = new JLabel("00");
        secondsLabel = new JLabel("00");

        hoursLabel.setToolTipText("hour");
        minutesLabel.setToolTipText("minute");
        secondsLabel.setToolTipText("second");

        Font timeFont = new Font("Segoe UI", Font.BOLD, 20);
        hoursLabel.setFont(timeFont);
        minutesLabel.setFont(timeFont);
        secondsLabel.setFont(timeFont);

        setTimeButton = new JButton("設定時間");
        stopTimeButton = new JButton("暫停時間");

        Font buttonFont = new Font("Microsoft JhengHei", Font.BOLD, 20);
        Color timeBtnBgColor = new Color(100, 149, 237); // 玉米花藍
        Color btnFgColor = Color.WHITE;

        JButton[] timeButtons = { setTimeButton, stopTimeButton };
        for (JButton btn : timeButtons) {
            btn.setFont(buttonFont);
            btn.setBackground(timeBtnBgColor);
            btn.setForeground(btnFgColor);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(120, 45));
        }

        add(hoursLabel);
        add(new JLabel(":"));
        add(minutesLabel);
        add(new JLabel(":"));
        add(secondsLabel);
        add(setTimeButton);
        add(stopTimeButton);
    }

    public void setActionListener(ActionListener listener) {
        setTimeButton.addActionListener(listener);
        stopTimeButton.addActionListener(listener);
    }
    public void updateTime(Time time) {
        hoursLabel.setText(String.format("%02d", time.getHour()));
        minutesLabel.setText(String.format("%02d", time.getMinute()));
        secondsLabel.setText(String.format("%02d", time.getSecond()));
    }
}