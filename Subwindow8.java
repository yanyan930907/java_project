package hug_fall_legs;
import javax.swing.*;

import javax.swing.*;

public class SubWindow8 extends JFrame {

    public SubWindow8() {
        setTitle("讀書記錄");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // 上方：日期與總時長
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder("基本資訊"));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JLabel dateLabel = new JLabel("日期: xxxx/xx/xx");
        JLabel totalTimeLabel = new JLabel("    今日讀書總時長：xx:xx:xx");

        topPanel.add(dateLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(totalTimeLabel);

        // 中間：科目、時長、總結
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBorder(BorderFactory.createTitledBorder("讀書內容"));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        JLabel subjectLabel = new JLabel("(科目)");
        JLabel durationLabel = new JLabel("   時長: xxxxxx");
        JLabel summaryLabel = new JLabel("   總結: XXXXXXX");

        infoPanel.add(subjectLabel);
        infoPanel.add(durationLabel);
        infoPanel.add(summaryLabel);
        middlePanel.add(infoPanel);

        // 三個輸入欄位
        for (int i = 0; i < 3; i++) {
            JTextField textField = new JTextField();
            textField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));
            middlePanel.add(textField);
        }

        // 加入到主視窗
        add(topPanel);
        add(middlePanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SubWindow8::new);
    }
}
