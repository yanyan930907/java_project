package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ErrorCollectWindow extends JPanel {
    private JPanel top;
    private JLabel titleLabel;
    private JButton backButton;
    private testmainMadeBy13 parent;

    private int[] duration = {1, 2, 3, 4, 5};

    public ErrorCollectWindow(testmainMadeBy13 parent) {
        System.out.println("切換到錯誤統計頁面");
        this.parent = parent;
        setLayout(new GridLayout(2, 1, 10, 10));

        backButton = new JButton("回到前頁");
        titleLabel = new JLabel("Error Data");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));

        top = new JPanel(new FlowLayout());
        top.add(backButton);
        top.add(titleLabel);

        add(top);
        add(new JLabel(Arrays.toString(duration), JLabel.CENTER));

        backButton.addActionListener(e -> parent.showMain());
    }
}
