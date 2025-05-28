package hug_fall_legs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// 時間區面板
public class TimePanel extends JPanel {

    private TimerManager timerManager = new TimerManager();
    private Timer swingTimer;
    public JLabel hoursLabel;
    public JLabel minutesLabel;
    public JLabel secondsLabel;
    private JButton stopTimeButton;
    private JButton setTimeButton;
    private JButton recordButton;
    private Time nowTime;

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

        setTimeButton = new JButton("開始計時");
        stopTimeButton = new JButton("暫停時間");
        recordButton = new JButton("結束計時");
        stopTimeButton.addActionListener(e -> setTimeButton.setText("繼續計時"));
        recordButton.addActionListener(e -> setTimeButton.setText("開始計時"));


        Font buttonFont = new Font("Microsoft JhengHei", Font.BOLD, 20);
        Color timeBtnBgColor = new Color(100, 149, 237); // 玉米花藍
        Color btnFgColor = Color.WHITE;

        JButton[] timeButtons = { setTimeButton, stopTimeButton ,recordButton};
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
        add(recordButton);

        TimePanelListener listener = new TimePanelListener();
        setTimeButton.addActionListener(listener);
        stopTimeButton.addActionListener(listener);
        recordButton.addActionListener(listener);

        swingTimer = new Timer(100, e -> {
            if (timerManager.isRunning()) {
                nowTime = timerManager.getElapsedTime();
                updateTime(nowTime);
            }
        });
        swingTimer.start();
    }
    private class TimePanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == setTimeButton) {
                System.out.println("設定時間被按下");
                timerManager.run();
            }
            else if (source == stopTimeButton) {
                System.out.println("暫停時間被按下");
                timerManager.pause();
            }
            else if (source == recordButton) {
                System.out.println("結束時間被按下");
                timerManager.stop();
                DateFileWriter writer = new DateFileWriter("collectTime.txt");
                writer.appendDateToFile(nowTime);
                Time zeroTime = new Time(0, 0, 0);
                updateTime(zeroTime);
            }
        }
    }
    public void updateTime(Time time) {
        hoursLabel.setText(String.format("%02d", time.getHour()));
        minutesLabel.setText(String.format("%02d", time.getMinute()));
        secondsLabel.setText(String.format("%02d", time.getSecond()));
    }
}