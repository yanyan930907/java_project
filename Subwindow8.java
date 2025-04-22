package hug_fall_legs;

import javax.swing.*;

public class SubWindow8 extends JFrame {

    public SubWindow8() {
        setTitle("讀書記錄");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // 視窗置中

        // ====== 主容器設定成 BoxLayout.Y_AXIS 垂直排列 ======
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // ====== 上方：日期與總時長 ======
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder("基本資訊"));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JLabel dateLabel = new JLabel("日期: xxxx/xx/xx");
        JLabel totalTimeLabel = new JLabel("    今日讀書總時長：xx:xx:xx");

        topPanel.add(dateLabel);
        topPanel.add(Box.createHorizontalGlue()); // 讓右邊的文字靠右
        topPanel.add(totalTimeLabel);

        // ====== 中間：科目、時長、總結 ======
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

        // 輸入欄位
        for (int i = 0; i < 3; i++) {
            JTextField textField = new JTextField();
            textField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));
            middlePanel.add(textField);
        }

        // ====== 左下角：返回主介面按鈕 ======
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        JButton mainButton = new JButton("返回主介面");
        mainButton.addActionListener(e -> {
            dispose(); // 關閉本視窗
            MainWindow.main(null); // 開啟主介面
        });

        bottomPanel.add(mainButton);
        bottomPanel.add(Box.createHorizontalGlue()); // 把按鈕靠左

        // ====== 加入所有元件到主容器 ======
        contentPanel.add(topPanel);
        contentPanel.add(middlePanel);
        contentPanel.add(Box.createVerticalGlue()); // 撐開
        contentPanel.add(bottomPanel);

        // 將主容器加入 JFrame
        setContentPane(contentPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SubWindow8::new);
    }
}
