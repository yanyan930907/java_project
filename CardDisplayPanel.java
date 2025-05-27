package hug_fall_legs;



import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;


public class CardDisplayPanel  extends JPanel{
    private JLabel frontTextLabel, imageIconLabel, subjectLabel, filePathLabel;
    private JPanel topPanel, imagPanel, linkPanel;


    public CardDisplayPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 200));  // 跟AllCardWindow裡Card部分設的一樣大
        setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        frontTextLabel = new JLabel("Front Text");
        frontTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        frontTextLabel.setHorizontalAlignment(JLabel.CENTER);

        subjectLabel = new JLabel("科目");
        subjectLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imageIconLabel = new JLabel();
        imageIconLabel.setPreferredSize(new Dimension(280,100));
        imageIconLabel.setHorizontalAlignment(JLabel.CENTER);

        filePathLabel = new JLabel("相關資料");
        filePathLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        frontTextLabel.setHorizontalAlignment(JLabel.CENTER);

        topPanel = new JPanel(new FlowLayout());
        topPanel.add(frontTextLabel);
        topPanel.add(subjectLabel);

        imagPanel = new JPanel(new FlowLayout());
        imagPanel.add(imageIconLabel);

        linkPanel = new JPanel(new FlowLayout());
        linkPanel.add(filePathLabel);

        add(topPanel,BorderLayout.NORTH);
        add(imageIconLabel,BorderLayout.CENTER);
        add(linkPanel,BorderLayout.SOUTH);

    }
    public void updateCard(Card card) {
            imageIconLabel.setIcon(card.getImageIcon());
            frontTextLabel.setText(card.getFrontText());
            subjectLabel.setText("科目：" + card.getCategory());
            filePathLabel.setText("相關資料：" + card.getLinkedFilePath());
        }
}
