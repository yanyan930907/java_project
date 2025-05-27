package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class statisticWindow extends JPanel {
    private JPanel upPanel, downPanel, topPanel, contentPanel, rowPanel;   // upPanel:上半, downPanel:下半, topPanel(在upPanel裡), contentPanel:顯示結果的表(在scrollPane裡), row(在contentPanel裡)
    private JLabel titleLabel, pictureLabel;  // titleLabel:標題, pictureLabel:圖表(在upPanel裡)
    private JButton backButton;
    private String[] timeRange = {"全部","一天","一週","一月","一季"};
    private JComboBox<String> timeComboBox; // 下拉時間範圍選單
    private testmainMadeBy13 parent;    // 主頁面
    private JScrollPane scrollPane; // 下拉表(在downPanel裡)

    public statisticWindow(testmainMadeBy13 parent) {
        System.out.println("切換到統計頁面");
        this.parent = parent;
        setLayout(new GridLayout(2, 1, 10, 10));
        
        // upPanel-topPanel,有backButton、titleLabel、timeComboBox
        backButton = new JButton("回到前頁");
        titleLabel = new JLabel("statistic");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));
        timeComboBox = new JComboBox<String>(timeRange);
        timeComboBox.setMaximumRowCount(5); // 一次顯示幾個
        timeComboBox.setPreferredSize(new Dimension(100, 30));  // 設大小

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton,BorderLayout.WEST);
        topPanel.add(titleLabel,BorderLayout.CENTER);
        topPanel.add(timeComboBox,BorderLayout.EAST);

        // upPanel-pictureLabel
        pictureLabel = new JLabel(new ImageIcon());

        // 加入upPanel元件
        upPanel = new JPanel(new BorderLayout());
        upPanel.add(topPanel,BorderLayout.NORTH);
        upPanel.add(pictureLabel,BorderLayout.CENTER);
        
        // scrollPane、contentPanel、rowPanel, 統計表單
        contentPanel = new JPanel(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        // 模擬加 10 筆資料，每筆是三欄
        for (int i = 0; i < 10; i++) {
            JPanel row = new JPanel(new GridLayout(1, 3, 10, 0)); // 三欄橫排
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // 高度60，寬度撐滿

            row.add(new JTextField("類別 " + (i + 1)));
            row.add(new JTextField("心得內容 " + (i + 1)));
            row.add(new JTextField("補充或圖片 " + (i + 1)));

            contentPanel.add(row);
            contentPanel.add(Box.createVerticalStrut(10)); // 每筆上下間距
        }
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(780, 300));   // 畫面大小

        // 加入downPanel元件
        downPanel = new JPanel(new BorderLayout());
        downPanel.add(scrollPane, BorderLayout.CENTER);


        // 加入全部元件
        add(upPanel);
        add(downPanel);


        backButton.addActionListener(e -> parent.showMain());
    }
}
