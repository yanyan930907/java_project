
package hug_fall_legs;

import javax.swing.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AllCardWindow extends JPanel {
    private JPanel topPanel, titlePanel, categoryPanel, midPanel, leftPanel, rightPanel , hintPanel, forgotPanel;
    private JLabel titleLabel , hintLabel;
    private JButton backButton, addButton, leftButton, rightButton, hintButton;
    private testmainMadeBy13 parent;
    private String[] subjects = {"全部","電腦網路","演算法","Java","Verilog"};
    private JComboBox<String> categoryComboBox;
    private JTextField hintField;
    private CardManager cardManager = new CardManager();
    private CardDisplayPanel cardPanel;
    private int currentCardIndex = 0; // 現在顯示哪張Card
    private JRadioButton forgotButton, rememberButton;
    private ButtonGroup forgotOptions;
    private JPanel backPanel, addPanel;
    private ArrayList<Card> allcard;
    private boolean[] hideornot = {true};
    private boolean selectedall = true;
    private String subjectNow = "";
    private int confirm;


    public AllCardWindow(testmainMadeBy13 parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        //  top
        addTopPanel();

        //  Card
        addCardDisplay();

        //  hint
        addHintPanel();

        // 加panel
        add(topPanel,BorderLayout.NORTH);
        add(midPanel,BorderLayout.CENTER);
        add(hintPanel,BorderLayout.SOUTH);
        //add(new JLabel(Arrays.toString(duration), JLabel.CENTER));
        // 返回首頁
        backButton.addActionListener(e -> parent.showMain());
        // 新增卡片
        addButton.addActionListener(e -> {
            AddCardDialog addCardDialog = new AddCardDialog(this);
            addCardDialog.setVisible(true);
        });
        
        // 顯示不同科目的卡片
        categoryComboBox.addActionListener(e -> {
            subjectNow = (String) categoryComboBox.getSelectedItem();
            if(Objects.equals(subjectNow, "全部")){
                readCards(0);
                selectedall=true;
                currentCardIndex = 0;
                System.out.println(" Select All becomes true.");
            }
            else{
                allcard=cardManager.readAllCards();
                confirm = 0;
                for(Card a :allcard){
                    if(a.getCategory().equals(subjectNow))  confirm++;
                }
                if (confirm == 0) {
                    JOptionPane.showMessageDialog(this,"沒有這科目的卡片");
                }
                else{

                        readCards(0,subjectNow,1);
                        selectedall=false;
                        System.out.println(" You choose a specific subject.");

                }
            }



        });
        // 顯示提示
        hintButton.addActionListener(e -> {
            System.out.println("提示");
            if (hideornot[0]) {
                FontMetrics ht = hintField.getFontMetrics(hintField.getFont());
                hintField.setPreferredSize(new Dimension(ht.stringWidth(hintField.getText()),hintField.getHeight()));
                hintField.setText(allcard.get(currentCardIndex).getBackHint());
                hintButton.setText("隱藏");
            } else {
                hintField.setText("* * * * * * * * * * * * * *");
                hintButton.setText("提示");
            }
            hideornot[0] = !hideornot[0];   // 按一次變一次

        });
        readCards(currentCardIndex);


    }
    /* 更新卡片
    public void refreshCardDisplay() {
        if (!cardManager.getCardList().isEmpty()) {
            currentCardIndex = cardManager.getCardList().size() - 1;
            cardPanel.updateCard(cardManager.getCardList().get(currentCardIndex));
        }
    }*/
    public void readCards(int dir){
        allcard = cardManager.readAllCards();
        for (Card card : allcard) {
            System.out.println(card);
        }
        cardPanel.updateCard(allcard.get(dir));
    }

    public void readCards(int dir,String sub,int gowhere){
        //gowhere 1 是往後，0是往左
        allcard = cardManager.readAllCards();
        for (Card card : allcard) {
            System.out.println(card);
        }
        while(!Objects.equals(allcard.get(dir).getCategory(), sub)&&!Objects.equals(allcard.get(dir).getCategory(),"全部")){
            if(gowhere==1)  dir++;
            else dir--;
            if (dir==-1){
                dir= allcard.size()-1;
            }
            if (dir==allcard.size()){
                dir= 0;
            }
        }
        currentCardIndex=dir;
        cardPanel.updateCard(allcard.get(dir));
    }

    public void addTitlePanel() {
        backButton = new JButton("回到前頁");
        titleLabel = new JLabel("Your Cards ");
        addButton = new JButton("新增卡片");

        // 按鍵美編
        backButton.setPreferredSize(new Dimension(100, 50));
        backPanel = new JPanel(new GridBagLayout());
        backPanel.add(backButton);
        addButton.setPreferredSize(new Dimension(100, 50));
        addPanel = new JPanel(new GridBagLayout());
        addPanel.add(addButton);

        //  標題美編
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);   // 將"Your Card"置中Label


        titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(backPanel,BorderLayout.WEST);
        titlePanel.add(titleLabel,BorderLayout.CENTER);
        titlePanel.add(addPanel,BorderLayout.EAST);
        //Button.setPreferredSize(new Dimension(150, 30)); // 寬200，高30
    }

    public void addCategoryPanel() {
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
    }

    public void addTopPanel() {
        addTitlePanel();
        addCategoryPanel();

        topPanel = new JPanel(new GridLayout(2,1));
        topPanel.add(titlePanel);
        topPanel.add(categoryPanel);
    }

    public void addControlButton() {
        leftButton = new JButton("👈");
        leftButton.addActionListener(e -> {
            if (currentCardIndex==0){
                currentCardIndex= allcard.size();
            }
            if(selectedall){
                readCards(--currentCardIndex);
            }
            else{
                readCards(--currentCardIndex,subjectNow,0);
            }

            System.out.printf("size=%d , index=%d\n",allcard.size(),currentCardIndex);
            hideornot[0]=true;
            hintButton.setText("提示");
            hintField.setText("* * * * * * * * * * * * * *");
        });
        leftButton.setPreferredSize(new Dimension(40, 70));
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.add(leftButton);
        rightButton = new JButton("👉");
        rightButton.addActionListener(e -> {
            if (currentCardIndex==allcard.size()-1){
                currentCardIndex= -1;
            }
            if(selectedall){
                readCards(++currentCardIndex);
            }
            else{
                readCards(++currentCardIndex,subjectNow,1);
            }
            System.out.printf("size=%d , index=%d\n",allcard.size(),currentCardIndex);
            hideornot[0]=true;
            hintButton.setText("提示");
            hintField.setText("* * * * * * * * * * * * * *");
        });
        rightButton.setPreferredSize(new Dimension(40, 70));
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.add(rightButton);
    }

    public void addCardDisplay() {
        addControlButton();
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
    }

    public void addHintPanel() {
        //hintLabel = new JLabel("***");
        hintButton = new JButton("提示");
        hintField = new JTextField("* * * * * * * * * * * * * *");
        hintField.setEditable(false);
        //hintField.setVisible(false);



        hintPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hintPanel.add(hintField);
        hintPanel.add(hintButton);
    }
}
