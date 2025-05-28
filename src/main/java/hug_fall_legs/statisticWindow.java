package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class statisticWindow extends JPanel {
    private JPanel upPanel, downPanel, topPanel, contentPanel, rowPanel, chartPanel;   // upPanel:上半, downPanel:下半, topPanel(在upPanel裡), contentPanel:顯示結果的表(在scrollPane裡), row(在contentPanel裡)
    private JLabel titleLabel;  // titleLabel:標題, pictureLabel:圖表(在upPanel裡)
    private JButton backButton;
    private String[] timeRange = {"全部","一天","一週","一月","一季"};
    private JComboBox<String> timeComboBox; // 下拉時間範圍選單
    private JPanel timeRangePanel, backPanel;
    private testmainMadeBy13 parent;    // 主頁面
    private JScrollPane scrollPane; // 下拉表(在downPanel裡)
    private JTextField[] titleFields;
    private JTextField[] durationFields;


    public statisticWindow(testmainMadeBy13 parent) {
        System.out.println("切換到統計頁面");
        this.parent = parent;
        setLayout(new GridLayout(2, 1, 10, 10));

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("無法載入 Nimbus LookAndFeel");
        }
        // upPanel-topPanel,有backButton、titleLabel
        backButton = new JButton("回到前頁");
        titleLabel = new JLabel("statistic  ");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);   // 置中Label

        backButton.setPreferredSize(new Dimension(100, 50));
        backPanel = new JPanel(new GridBagLayout());
        backPanel.add(backButton);

        // timeRangePanel、timeComboBox
        timeComboBox = new JComboBox<String>(timeRange);
        timeComboBox.setMaximumRowCount(4); // 一次顯示幾個
        timeComboBox.setPreferredSize(new Dimension(100, 30));  // 設大小
        timeRangePanel = new JPanel(new GridBagLayout());
        timeRangePanel.add(timeComboBox);

        //

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(backPanel,BorderLayout.WEST);
        topPanel.add(titleLabel,BorderLayout.CENTER);
        topPanel.add(timeRangePanel,BorderLayout.EAST);

        // upPanel-pictureLabel
        BarChartPanel bcp = new BarChartPanel();
        chartPanel=bcp.createBarChartPanel("全部");

        // 加入upPanel元件
        upPanel = new JPanel(new BorderLayout());
        upPanel.add(topPanel,BorderLayout.NORTH);
        upPanel.add(chartPanel,BorderLayout.CENTER);
        
        // scrollPane、contentPanel、rowPanel, 統計表單
        contentPanel = new JPanel(); // 先創建 panel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // 模擬加 10 筆資料，每筆是三欄
        int maxEntries = 80;
        titleFields = new JTextField[maxEntries];
        durationFields = new JTextField[maxEntries];

        for (int i = 0; i < maxEntries; i++) {
            rowPanel = new JPanel(new GridLayout(1, 2, 5, 0));
            rowPanel.setPreferredSize(new Dimension(100, 50));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

            JTextField notes = new JTextField();
            notes.setEditable(false);
            titleFields[i] = notes;  // 存進陣列
            rowPanel.add(notes);

            JTextField duration = new JTextField();
            duration.setEditable(false);
            durationFields[i] = duration;  // 存進陣列
            rowPanel.add(duration);

            contentPanel.add(rowPanel);
            contentPanel.add(Box.createVerticalStrut(10));
        }

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200, 300));   // 畫面大小

        // 加入downPanel元件
        downPanel = new JPanel(new BorderLayout());
        downPanel.add(scrollPane, BorderLayout.CENTER);


        // 加入全部元件
        add(upPanel);
        add(downPanel);

        // 讀取.txt的統計資料然後填入JTextField
        ReviewStatistic stat = new ReviewStatistic("collectTime.txt");
        stat.populateFields(titleFields, durationFields);

        
        backButton.addActionListener(e -> parent.showMain());
    }
}
