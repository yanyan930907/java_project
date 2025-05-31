package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ErrorCollectWindow extends JPanel {
    private JPanel topPanel, backPanel, titlePanel, forgotPanel, categoryPanel, hintPanel;
    private JLabel titleLabel;
    private JButton backButton, leftButton, rightButton, hintButton;
    private testmainMadeBy13 parent;
    private SubjectManager subjectManager = new SubjectManager();
    private ArrayList<String> subjectsList = new ArrayList<>();
    private String[] subjects = {"全部","電腦網路","演算法","Java","Verilog"};
    private JComboBox<String> categoryComboBox;
    private JRadioButton forgotButton, rememberButton;
    private ButtonGroup forgotOptions;
    private JPanel leftPanel, rightPanel, midPanel;
    private JTextField hintField;
    private CardDisplayPanel cardPanel;
    private CardManager cardManager = new CardManager();
    private int currentCardIndex = 0;
    private ArrayList<Card> allcard;
    private boolean[] hideornot = {true};
    private boolean selectedall = true;
    private String subjectNow = "";
    private int confirm;
    private int ff = 0;
    private int countingError;
    private writeANewOne writeNewOne = new writeANewOne();

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
        rememberButton.addActionListener(e -> {
            if(rememberButton.isSelected()){
                forgotButton.setSelected(false);
                Card card = allcard.get(currentCardIndex);
                card.setRemember(true);
                allcard.set(currentCardIndex, card);
                System.out.println(card);
                System.out.println("remember");
            }
            writeNewOne.update(allcard);

        });
        forgotButton.addActionListener(e -> {
            if(forgotButton.isSelected()){
                rememberButton.setSelected(false);
                Card card = allcard.get(currentCardIndex);
                card.setRemember(false);
                allcard.set(currentCardIndex, card);
                System.out.println("forgot");
            }
            writeNewOne.update(allcard);
        });
        categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subjectsList=subjectManager.readSubject();
        subjects=subjectsList.toArray(new String[0]);
        categoryComboBox.removeAllItems();
        for(String s:subjects){
            if(countingError!=0) categoryComboBox.addItem(s);
            else {
                countingError=1;
                categoryComboBox.addItem("選擇你要的科目");
            }
        }
        countingError=0;
        categoryPanel.add(categoryComboBox);
        categoryPanel.add(forgotPanel);

        topPanel = new JPanel(new GridLayout(2,1));
        topPanel.add(titlePanel);
        topPanel.add(categoryPanel);
        //  Card
        leftButton = new JButton("👈");
        leftButton.addActionListener(e -> {
            if (currentCardIndex==0){
                currentCardIndex= allcard.size();
            }
            if(selectedall){
                readCards(--currentCardIndex,0);
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
                readCards(++currentCardIndex,1);
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
        cardPanel = new CardDisplayPanel();

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
        allcard = cardManager.readAllCards();
        readCards(currentCardIndex,1);


        // 顯示不同科目的卡片
        categoryComboBox.addActionListener(e -> {
            subjectNow = (String) categoryComboBox.getSelectedItem();
            if(Objects.equals(subjectNow, "全部")){
                readCards(0,1);
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
                    if(ff==0)   JOptionPane.showMessageDialog(this,"沒有這科目的卡片");
                    else ff=0;
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
    }

    public void readCards(int dir,int gowhere){
        countingError=0;
        for (Card card : allcard) {
            if(!card.getRemember()){
                countingError++;
                System.out.println(card);
            }
        }
        System.out.println("countingError="+countingError);
        if(countingError==0) {
            JOptionPane.showMessageDialog(this,"你沒什麼好讀的");
        }
    }

    public void readCards(int dir,String sub,int gowhere){
        //gowhere 1 是往後，0是往左
        countingError=0;
        for (Card card : allcard) {
            if(!card.getRemember()&& Objects.equals(card.getCategory(), sub)) {
                countingError++;
                System.out.println(card);
            }
        }
        if(countingError==0) {
            JOptionPane.showMessageDialog(this,"別再找了，怎麼找都磨用");
        }
        else {
            while (!allcard.get(dir).getCategory().equals(sub) || allcard.get(dir).getRemember()) {
                System.out.println("dir=" + dir);
                if (gowhere == 1) dir++;
                else dir--;
                if (dir == -1) {
                    dir = allcard.size() - 1;
                }
                if (dir == allcard.size()) {
                    dir = 0;
                }
            }
            currentCardIndex = dir;
            if (allcard.get(dir).getRemember()) {
                rememberButton.setSelected(true);
                forgotButton.setSelected(false);
            } else {
                rememberButton.setSelected(false);
                forgotButton.setSelected(true);
            }
            cardPanel.updateCard(allcard.get(dir));
        }
    }
}