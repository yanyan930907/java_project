package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ErrorCollectWindow extends JPanel {
    private JPanel topPanel, backPanel, titlePanel, forgotPanel, categoryPanel, hintPanel;
    private JLabel titleLabel;
    private JButton backButton, leftButton, rightButton, hintButton;
    private testmainMadeBy13 parent;
    private String[] subjects = {"全部","電腦網路","演算法","Java","Verilog"};
    private JComboBox<String> categoryComboBox;
    private JRadioButton forgotButton, rememberButton;
    private ButtonGroup forgotOptions;
    private JPanel leftPanel, rightPanel, cardPanel, midPanel;
    private JTextField hintField;

    public ErrorCollectWindow(testmainMadeBy13 parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        //  top
        backButton = new JButton("回到前頁");
        titleLabel = new JLabel("Don't forget me !!!         ");

        // 按鍵美編
        backButton.setPreferredSize(new Dimension(100, 50));
        backPanel = new JPanel(new GridBagLayout());
        backPanel.add(backButton);

        //  標題美編
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);   // 將"Your Card"置中Label


        titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(backPanel,BorderLayout.WEST);
        titlePanel.add(titleLabel,BorderLayout.CENTER);
        //Button.setPreferredSize(new Dimension(150, 30)); // 寬200，高30

        //  category
        categoryComboBox = new JComboBox<String>(subjects);
        categoryComboBox.setMaximumRowCount(4); // 一次顯示幾個
        categoryComboBox.setPreferredSize(new Dimension(150, 30));  // 設大小

        // forgot 單選
        forgotButton = new JRadioButton("這啥??",false);
        rememberButton = new JRadioButton("老熟了!!",true);
        forgotOptions = new ButtonGroup();
        forgotOptions.add(rememberButton);
        forgotOptions.add(forgotButton);
        forgotPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forgotPanel.add(rememberButton);
        forgotPanel.add(forgotButton);

        categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.add(categoryComboBox);
        categoryPanel.add(forgotPanel);

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
        //cardManager.loadFromJson("cards.json");
        cardPanel = new CardDisplayPanel();
        /*
        if (!cardManager.getCardList().isEmpty()){  // CardList 中有存卡片
            cardPanel.updateCard(cardManager.getCardList().get(currentCardIndex));
        }*/


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




        // 顯示不同科目的卡片
        categoryComboBox.addActionListener(e -> {
            String selected = (String) categoryComboBox.getSelectedItem();
            // 根據 selected 進行卡片顯示更新
        });

        // 顯示提示
        final boolean[] isOrignal = {true};
        hintButton.addActionListener(e -> {
            System.out.println("提示");
            if (isOrignal[0]) {
                FontMetrics ht = hintField.getFontMetrics(hintField.getFont());
                hintField.setPreferredSize(new Dimension(ht.stringWidth(hintField.getText()),hintField.getHeight()));
                hintField.setText("哈囉你好嗎?");
                hintButton.setText("隱藏");
            } else {
                hintField.setText("* * * * * * * * * * * * * *");
                hintButton.setText("提示");
            }
            isOrignal[0] = !isOrignal[0];   // 按一次變一次

        });



    }

    private JFrame parentFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }



    /* 更新卡片
    public void refreshCardDisplay() {
        if (!cardManager.getCardList().isEmpty()) {
            currentCardIndex = cardManager.getCardList().size() - 1;
            cardPanel.updateCard(cardManager.getCardList().get(currentCardIndex));
        }
    }*/

}