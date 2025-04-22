import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindow {
    static JLabel timerLabel;
    static Timer countdownTimer;
    static int remainingSeconds = 0;

    public static void main(String[] args) {
        // 建立主視窗
        JFrame frame = new JFrame("複習工具");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // ====== 右上角的計時器顯示區 ======
        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(timerLabel.getFont().deriveFont(18f));

        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.X_AXIS));
        topRightPanel.add(Box.createHorizontalGlue());
        topRightPanel.add(timerLabel);
        topRightPanel.add(Box.createHorizontalStrut(10));

        frame.add(topRightPanel);

        // ====== 中間的按鈕區域 ======
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JButton statsButton = new JButton("複習時間統計");
        JButton flashcardButton = new JButton("複習小卡");
        JButton timerButton = new JButton("計時器設定");

        int buttonWidth = 400;
        int buttonHeight = 200;
        for (JButton btn : new JButton[]{statsButton, flashcardButton, timerButton}) {
            btn.setMaximumSize(new java.awt.Dimension(buttonWidth, buttonHeight));
            btn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        }

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(statsButton);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(flashcardButton);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(timerButton);
        centerPanel.add(Box.createVerticalGlue());

        frame.add(centerPanel);

        // ====== 點擊「計時器設定」時動作 ======
        timerButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

            JTextField hourField = new JTextField("0");
            JTextField minField = new JTextField("0");
            JTextField secField = new JTextField("0");

            inputPanel.add(new JLabel("小時:"));
            inputPanel.add(hourField);
            inputPanel.add(new JLabel("分鐘:"));
            inputPanel.add(minField);
            inputPanel.add(new JLabel("秒鐘:"));
            inputPanel.add(secField);

            int result = JOptionPane.showConfirmDialog(frame, inputPanel, "設定倒數時間", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int hours = Integer.parseInt(hourField.getText());
                    int minutes = Integer.parseInt(minField.getText());
                    int seconds = Integer.parseInt(secField.getText());

                    remainingSeconds = hours * 3600 + minutes * 60 + seconds;
                    if (remainingSeconds > 0) {
                        startCountdown();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "請輸入有效數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 顯示視窗
        frame.setLocationRelativeTo(null); // 視窗置中
        frame.setVisible(true);
    }

    // ====== 開始倒數計時功能 ======
    static void startCountdown() {
        if (countdownTimer != null) {
            countdownTimer.cancel(); // 清除先前的計時器
        }

        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (remainingSeconds > 0) {
                    remainingSeconds--;
                    SwingUtilities.invokeLater(() -> timerLabel.setText(formatTime(remainingSeconds)));
                } else {
                    countdownTimer.cancel();
                    SwingUtilities.invokeLater(() -> {
                        timerLabel.setText("00:00:00");
                        JOptionPane.showMessageDialog(null, "時間到囉！", "倒數完成", JOptionPane.INFORMATION_MESSAGE);
                    });
                }
            }
        }, 0, 1000);
    }

    // ====== 格式化秒數為 hh:mm:ss ======
    static String formatTime(int totalSeconds) {
        int h = totalSeconds / 3600;
        int m = (totalSeconds % 3600) / 60;
        int s = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
