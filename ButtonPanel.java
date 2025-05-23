package hug_fall_legs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// 按鈕區域面板
public class ButtonPanel extends JPanel {

    JButton allCardsButton;
    JButton statisticButton;
    JButton allDataButton;
    JButton errorButton;
    private testmainMadeBy13 parent;

    public ButtonPanel(testmainMadeBy13 parent) {
        this.parent = parent;
        setLayout(new GridLayout(1, 4, 30, 10));
        setBorder(new EmptyBorder(20, 30, 20, 30));

        allCardsButton = new JButton("全部卡片");
        statisticButton = new JButton("統計資料");
        allDataButton = new JButton("所有資料");
        errorButton = new JButton("錯誤整理");

        JButton[] buttons = { allCardsButton, statisticButton, allDataButton, errorButton };

        Font buttonFont = new Font("Microsoft JhengHei", Font.BOLD, 20);
        Color btnBgColor = new Color(70, 130, 180); // 鋼藍色
        Color btnFgColor = Color.WHITE;

        for (JButton btn : buttons) {
            btn.setFont(buttonFont);
            btn.setBackground(btnBgColor);
            btn.setForeground(btnFgColor);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(140, 50));
            add(btn);
        }
        ButtonPanelListener listener = new ButtonPanelListener();
        allCardsButton.addActionListener(listener);
        statisticButton.addActionListener(listener);
        allDataButton.addActionListener(listener);
        errorButton.addActionListener(listener);

    }
    private class ButtonPanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == allCardsButton) {
                System.out.println("全部卡片被按下");
            }
            else if (source == statisticButton) {
                System.out.println("統計資料被按下");
                parent.showStatistic();
            }
            else if (source == errorButton) {
                System.out.println("錯誤整理被按下");
            }
            else if (source == allDataButton) {
                System.out.println("所有資料被按下");
                parent.showAllDataWindow();
            }
        }
    }
}