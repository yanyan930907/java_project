package hug_fall_legs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// 主視窗類別，只負責組合其他元件
public class MainWindow extends JPanel {

    private ButtonPanel buttonPanel;
    private TimePanel timePanel;
    private JLabel titleLabel;
    private testmainMadeBy13 parent;

    public MainWindow(testmainMadeBy13 parent) {
        this.parent = parent;

        // 設定 Nimbus Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("無法載入 Nimbus LookAndFeel");
        }

        setLayout(new BorderLayout(10, 10));

        // 標題
        titleLabel = new JLabel("hug_fall_legs", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));
        add(titleLabel, BorderLayout.NORTH);

        // 按鈕面板
        buttonPanel = new ButtonPanel(parent);
        add(buttonPanel, BorderLayout.CENTER);

        // 時間面板
        timePanel = new TimePanel();
        add(timePanel, BorderLayout.SOUTH);



        // 將事件監聽器注入（讓事件可以反應到不同元件）
        MainEventListener handler = new MainEventListener(buttonPanel, timePanel);
        buttonPanel.setActionListener(handler);
        timePanel.setActionListener(handler);
    }
}