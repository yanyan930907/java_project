package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Collections;

public class statisticWindow extends JPanel {
    private JPanel upPanel, downPanel, topPanel, contentPanel, rowPanel, chartPanel, refreshPanel;   // upPanel:上半, downPanel:下半, topPanel(在upPanel裡), contentPanel:顯示結果的表(在scrollPane裡), row(在contentPanel裡)
    private JLabel titleLabel;  // titleLabel:標題, pictureLabel:圖表(在upPanel裡)
    private JButton backButton, refreshButton;
    private String[] timeRange = {"全部","一天","一週","一月","一季"};
    private JComboBox<String> timeComboBox; // 下拉時間範圍選單
    private JPanel timeRangePanel, backPanel;
    private testmainMadeBy13 parent;    // 主頁面
    private JScrollPane scrollPane; // 下拉表(在downPanel裡)
    private JTextField[] titleFields;
    private JTextField[] durationFields;


    public statisticWindow(testmainMadeBy13 parent) {

        this.parent = parent;
        setLayout(new GridLayout(2, 1, 10, 10));

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("無法載入 Nimbus LookAndFeel");
        }
        // upPanel-topPanel,有backButton、titleLabel
        addTopPanel();

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

        addDownPanel();
        // 加入全部元件
        add(upPanel);
        add(downPanel);

        updateStatistic();

        timeComboBox.addActionListener(e-> {
            upPanel.remove(chartPanel);
            chartPanel=bcp.createBarChartPanel((String)timeComboBox.getSelectedItem());
            upPanel.add(chartPanel,BorderLayout.CENTER);
            upPanel.revalidate();
            upPanel.repaint();
            System.out.println("Chart顯示為: " + (String)timeComboBox.getSelectedItem());
        });
        backButton.addActionListener(e -> parent.showMain());
    }

    public void addTopPanel() {
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
    }

    public void addDownPanel() {
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200, 300));   // 畫面大小

        refreshButton = new JButton("更新統計");
        // 更新統計
        refreshButton.addActionListener(e -> {
            updateStatistic();
        });
        refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        refreshPanel.add(refreshButton);

        // 加入downPanel元件
        downPanel = new JPanel(new BorderLayout());
        downPanel.add(refreshPanel, BorderLayout.NORTH);
        downPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public void updateStatistic(){
        // 讀取.txt的統計資料然後填入JTextField
        ReviewStatistic stat = new ReviewStatistic("collectTime.txt");
        List<ReviewSession> sessions = stat.getSessions();

        Collections.reverse(sessions);  // 把List倒過來

        contentPanel.removeAll();  // 清空舊的欄位

        titleFields = new JTextField[sessions.size()];
        durationFields = new JTextField[sessions.size()];

        for (int i = 0; i < sessions.size(); i++) {
            rowPanel = new JPanel(new GridLayout(1, 2, 5, 0));
            rowPanel.setPreferredSize(new Dimension(100, 50));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

            JTextField notes = new JTextField();
            notes.setEditable(false);
            titleFields[i] = notes;

            JTextField duration = new JTextField();
            duration.setEditable(false);
            durationFields[i] = duration;

            rowPanel.add(notes);
            rowPanel.add(duration);

            contentPanel.add(rowPanel);
            contentPanel.add(Box.createVerticalStrut(10));
        }

        // 重新填入內容
        stat.populateFields(titleFields, durationFields);

        contentPanel.revalidate();  // 更新畫面
        contentPanel.repaint();
    }
}
