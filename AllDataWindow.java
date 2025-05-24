package hug_fall_legs;

import java.awt.*;
import javax.swing.*;
import java.io.File;

// 主視窗類別，只負責組合其他元件
public class AllDataWindow extends JPanel {
    private JPanel top;
    private JButton backButton;
    private testmainMadeBy13 parent;

    public AllDataWindow(testmainMadeBy13 parent) {
        this.parent = parent;
        System.out.println("切換到所有資料頁面");

        // 設定 Nimbus Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("無法載入 Nimbus LookAndFeel");
        }

        setLayout(new BorderLayout(10, 10));

        // 使用相對路徑，指向同一目錄下的 allData 資料夾
        String folderPath = "allData";

        DefaultListModel<String> listModel = new DefaultListModel<>();
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    listModel.addElement(file.getName());
                }
            } else {
                listModel.addElement("無法讀取資料夾內容");
            }
        } else {
            listModel.addElement("找不到 allData 資料夾");
        }

        // 建立元件並加入視圖
        JLabel titleLabel = new JLabel("allData 資料夾內容：");
        JList<String> fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        backButton = new JButton("回到前頁");
        top = new JPanel(new FlowLayout());
        top.add(backButton);
        backButton.addActionListener(e -> parent.showMain());
        top.add(titleLabel);
        add(top, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
