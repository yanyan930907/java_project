package hug_fall_legs;
import java.awt.*;
import javax.swing.*;
public class ReadingRoom {
    public ReadingRoom() {
        // Create the main frame
        JFrame frame = new JFrame("Reading Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Top panel: Date and total reading time
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder(""));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JLabel dateLabel = new JLabel("Date: xxxx/xx/xx");
        JLabel totalTimeLabel = new JLabel("Countdown Time: xx:xx:xx");

        topPanel.add(dateLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(totalTimeLabel);

        // Middle panel: Subject, Duration, Summary
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBorder(BorderFactory.createTitledBorder("Your Card"));


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        JLabel hintLabel = new JLabel("Your hint: xxxxxxx");
        hintLabel.setPreferredSize(new java.awt.Dimension(0, 30));
        JLabel durationLabel = new JLabel("   Duration: xxxxxx");
        JLabel summaryLabel = new JLabel("   Summary: XXXXXXX");



        ImageIcon imageIcon = new ImageIcon("C:\\Users\\eric\\Desktop\\test\\JAVA_FINAL_PROJECT\\java_project\\Picture\\toki.png");
        Image originalImage = imageIcon.getImage();
        double scalePercent = 0.05;
        int newWidth = (int)(originalImage.getWidth(null) * scalePercent);
        int newHeight = (int)(originalImage.getHeight(null) * scalePercent);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)); // Resize the image
        
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        

        infoPanel.add(hintLabel);
        infoPanel.add(durationLabel);
        infoPanel.add(summaryLabel);
        JButton lastButton = new JButton("上一個");
        lastButton.setPreferredSize(new java.awt.Dimension(0, 30));
        lastButton.setMaximumSize(new java.awt.Dimension(100, 30));
        middlePanel.add(infoPanel);
        middlePanel.add(imageLabel);

        JButton nextButton = new JButton("下一個");
        nextButton.setPreferredSize(new java.awt.Dimension(0, 30));
        nextButton.setMaximumSize(new java.awt.Dimension(100, 30));
        JButton hintButton = new JButton("我要提示");
        hintButton.setPreferredSize(new java.awt.Dimension(0, 30));
        hintButton.setMaximumSize(new java.awt.Dimension(100, 30));
        JButton backtomainButton = new JButton("回主頁");
        backtomainButton.setPreferredSize(new java.awt.Dimension(0, 30));
        backtomainButton.setMaximumSize(new java.awt.Dimension(100, 30));
        backtomainButton.addActionListener(e -> {
            // Close the current window
            frame.dispose();
            // Open the main window
            MainWindow.main(null);
        });

        middlePanel.add(lastButton);
        middlePanel.add(nextButton);
        middlePanel.add(hintButton);
        middlePanel.add(backtomainButton);
        // Add panels to the main frame
        frame.add(topPanel);
        frame.add(middlePanel);

        // Set the frame to be visible
        frame.setVisible(true);
    }    
}
