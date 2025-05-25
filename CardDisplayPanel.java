package hug_fall_legs;



import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;


public class CardDisplayPanel  extends JPanel{
    private JLabel frontTextLabel, imageIconLabel, subjectLabel, filePathLabel;
    private JPanel topPanel, imagPanel, linkPanel;


    public CardDisplayPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 200));  // 跟AllCardWindow裡Card部分設的一樣大
        setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        frontTextLabel = new JLabel("Front Text");
        frontTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        frontTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subjectLabel = new JLabel("科目");
        subjectLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imageIconLabel = new JLabel();
        imageIconLabel.setPreferredSize(new Dimension(280,100));
        imageIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        filePathLabel = new JLabel("相關資料");
        filePathLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        filePathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel = new JPanel(new FlowLayout());
        topPanel.add(frontTextLabel,FlowLayout.LEFT);
        topPanel.add(subjectLabel,FlowLayout.RIGHT);

        imagPanel = new JPanel(new FlowLayout());
        imagPanel.add(imageIconLabel,FlowLayout.LEFT);

        linkPanel = new JPanel(new FlowLayout());
        linkPanel.add(filePathLabel,FlowLayout.RIGHT);

        add(topPanel);
        add(imageIconLabel);
        add(linkPanel);

    }
    public void updateCard(Card card) {
            imageIconLabel.setIcon(card.getImageIcon());
            frontTextLabel.setText(card.getFrontText());
            subjectLabel.setText("科目：" + card.getCategory());
            filePathLabel.setText("相關資料：" + card.getLinkedFilePath());
        }
}
