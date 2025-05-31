package hug_fall_legs;



import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;


public class CardDisplayPanel  extends JPanel{
    private JLabel frontTextLabel, subjectLabel, filePathLabel;
    private JPanel topPanel, imagPanel, linkPanel;
    private ScaledImageLabel imageIconLabel;




    public CardDisplayPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 200));  // 跟AllCardWindow裡Card部分設的一樣大
        setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        frontTextLabel = new JLabel("Front Text");
        frontTextLabel.setFont(new Font("DFKai-SB", Font.BOLD, 30));
        frontTextLabel.setHorizontalAlignment(JLabel.CENTER);

        subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("DFKai-SB", Font.BOLD, 14));
        subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imageIconLabel = new ScaledImageLabel();
        imageIconLabel.setPreferredSize(new Dimension(500, 300)); // 可自定初始區域
        imageIconLabel.setHorizontalAlignment(JLabel.LEFT);

        filePathLabel = new JLabel("Related File:");
        filePathLabel.setFont(new Font("DFKai-SB", Font.BOLD, 12));
        frontTextLabel.setHorizontalAlignment(JLabel.CENTER);

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(frontTextLabel,BorderLayout.WEST);
        topPanel.add(subjectLabel,BorderLayout.EAST);

        imagPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imagPanel.add(imageIconLabel);

        linkPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        linkPanel.add(filePathLabel);

        add(topPanel,BorderLayout.NORTH);
        add(imageIconLabel,BorderLayout.CENTER);
        add(linkPanel,BorderLayout.SOUTH);

    }
    public void updateCard(Card card) {
        ImageIcon icon = card.getImageIcon();
        if (icon != null) {
            imageIconLabel.setImage(icon.getImage());  // 不用 scaledInstance，因為會在 paintComponent 裡縮放
        }
        frontTextLabel.setText(card.getFrontText());
        subjectLabel.setText("Subject:" + card.getCategory());
        filePathLabel.setText("Related File:" + card.getLinkedFilePath());
    }

}
