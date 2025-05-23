
package hug_fall_legs;

import javax.swing.*;


import java.awt.*;
import java.util.Arrays;
import java_final_project.*;

public class AllCardWindow extends JPanel {
    private JPanel topPanel, titlePanel, categoryPanel, midPanel, leftPanel, rightPanel , hintPanel;
    private JLabel titleLabel , hintLabel;
    private JButton backButton, addButton, leftButton, rightButton, hintButton;
    private testmainMadeBy13 parent;
    private String[] subjects = {"全部","電腦網路","演算法","Java","Verilog"};
    private JComboBox<String> categoryComboBox;
    private JTextField hintField;
    private CardManager cardManager = new CardManager();
    private CardDisplayPanel cardPanel;
    private int currentCardIndex = 0; // 現在顯示哪張Card

    //private int[] duration = {1, 3, 4, 5};

    public AllCardWindow(testmainMadeBy13 parent) {
        System.out.println("切換到所有卡片頁面");
        this.parent = parent;
        setLayout(new BorderLayout());

        //  top
        backButton = new JButton("回到前頁");
        titleLabel = new JLabel("Your Cards ");
        addButton = new JButton("新增卡片");

            //  標題美編
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);   // 將"Your Card"置中Label

        titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(backButton,BorderLayout.WEST);
        titlePanel.add(titleLabel,BorderLayout.CENTER);
        titlePanel.add(addButton,BorderLayout.EAST);
        //Button.setPreferredSize(new Dimension(150, 30)); // 寬200，高30

        //  category
        categoryComboBox = new JComboBox<String>(subjects);
        categoryComboBox.setMaximumRowCount(4); // 一次顯示幾個
        categoryComboBox.setPreferredSize(new Dimension(150, 30));  // 設大小


        categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.add(categoryComboBox);

        topPanel = new JPanel(new GridLayout(2,1));
        topPanel.add(titlePanel);
        topPanel.add(categoryPanel);
        //  Card
        leftButton = new JButton("👈");
        leftButton.setPreferredSize(new Dimension(40, 70));
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.add(leftButton);
        rightButton = new JButton("👉");
        rightButton.setPreferredSize(new Dimension(40, 70));
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.add(rightButton);
        /*
        cardLabel = new JLabel("card");
        cardLabel.setPreferredSize(new Dimension(300, 200)); // 控制Card大小
        */
        cardManager.loadFromJson("card.json");
        cardPanel = new CardDisplayPanel();
        
        if (!cardManager.getCardList().isEmpty()){  // CardList 中有存卡片
            cardPanel.updateCard(cardManager.getCardList().get(currentCardIndex));
        }


        midPanel = new JPanel(new BorderLayout());
        midPanel.add(leftPanel,BorderLayout.WEST);
        midPanel.add(cardPanel,BorderLayout.CENTER);
        midPanel.add(rightPanel,BorderLayout.EAST);
        
        

        //  hint
        //hintLabel = new JLabel("***");
        hintButton = new JButton("提示");
        hintField = new JTextField("* * * * * * * * * * * * * *");
        hintField.setEditable(false);
        //hintField.setVisible(false);

        hintPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hintPanel.add(hintField);
        hintPanel.add(hintButton);

        // 加panel
        add(topPanel,BorderLayout.NORTH);
        add(midPanel,BorderLayout.CENTER);
        add(hintPanel,BorderLayout.SOUTH);
        //add(new JLabel(Arrays.toString(duration), JLabel.CENTER));
        // 返回首頁
        backButton.addActionListener(e -> parent.showMain());
        // 新增卡片
        addButton.addActionListener(e -> {
            A
        });

        // 顯示不同科目的卡片
        categoryComboBox.addActionListener(e -> {
            String selected = (String) categoryComboBox.getSelectedItem();
            // 根據 selected 進行卡片顯示更新
        });

    }
}
