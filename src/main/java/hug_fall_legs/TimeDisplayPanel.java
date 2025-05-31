package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;
import java.util.TimerTask;

public class TimeDisplayPanel extends JPanel {
    private JLabel label;
    private String filePath;

    public TimeDisplayPanel(String filePath) {
        this.filePath = filePath;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 初始時間顯示
        label = new JLabel(TimeCalculator.calculateTotalStudyTime(filePath), SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(getScaledFont(label, getWidth(), getHeight()));
        add(label, BorderLayout.CENTER);

        // 視窗調整時重新調整字體大小
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                label.setFont(getScaledFont(label, getWidth(), getHeight()));
            }
        });

        // 啟動定時更新任務（每 5 秒刷新）
        startAutoRefresh();
    }

    private void startAutoRefresh() {
        Timer timer = new Timer(true); // 設為守護執行緒，視窗關掉會自動結束
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    String updatedTime = TimeCalculator.calculateTotalStudyTime(filePath);
                    label.setText(updatedTime);
                    label.setFont(getScaledFont(label, getWidth(), getHeight())); // 重算字型大小
                });
            }
        }, 0, 5000); // 每 5 秒更新一次
    }

    private Font getScaledFont(JLabel label, int panelWidth, int panelHeight) {
        String text = label.getText();
        int size = 10;
        Font font;
        while (true) {
            font = new Font("SansSerif", Font.BOLD, size);
            FontMetrics fm = label.getFontMetrics(font);
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();

            if (textWidth > panelWidth * 0.9 || textHeight > panelHeight * 0.9) {
                size--;
                break;
            }
            size++;
        }
        return new Font("SansSerif", Font.BOLD, size);
    }
}
