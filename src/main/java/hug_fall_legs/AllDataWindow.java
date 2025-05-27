package hug_fall_legs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AllDataWindow extends JPanel {
    private JPanel top, bottom, backPanel;
    private JButton backButton, addButton, editButton, deleteButton, viewButton;;
    private testmainMadeBy13 parent;
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private String folderPath = "allData";
    private JComboBox<String> subjectComboBox;
    private String[] subjects = {"全部","電腦網路","演算法","Java","Verilog"};

    public AllDataWindow(testmainMadeBy13 parent) {
        this.parent = parent;
        System.out.println("切換到所有資料頁面");

        // 設定 Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("無法載入 Nimbus LookAndFeel");
        }

        setLayout(new BorderLayout(10, 10));

        // 設定資料模型和列表元件
        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        refreshFileList(); // 讀取資料夾內容

        // 上方區塊
        JLabel titleLabel = new JLabel("All Data           ");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setForeground(new Color(30, 60, 90));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);   // 置中Label
        backButton = new JButton("回到前頁");
        backButton.addActionListener(e -> parent.showMain());
        backButton.setPreferredSize(new Dimension(100, 50));
        backPanel = new JPanel(new GridBagLayout());
        backPanel.add(backButton);



        top = new JPanel(new BorderLayout());
        top.add(backPanel,BorderLayout.WEST);
        top.add(titleLabel,BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);

        // 中間檔案清單
        JScrollPane scrollPane = new JScrollPane(fileList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        add(scrollPane, BorderLayout.CENTER);

        // 下方按鈕區
        bottom = new JPanel(new FlowLayout());

        // 下拉選單查詢功能
        subjectComboBox = new JComboBox<>(subjects);
        subjectComboBox.addActionListener(e -> filterBySubject());  // 選項改變時自動篩選

        bottom.add(new JLabel("查詢分類："));
        bottom.add(subjectComboBox);

// 其他按鈕
        addButton = new JButton("新增");
        editButton = new JButton("修改");
        deleteButton = new JButton("刪除");
        viewButton = new JButton("檢視內容");

        bottom.add(addButton);
        bottom.add(editButton);
        bottom.add(deleteButton);
        bottom.add(viewButton);

        add(bottom, BorderLayout.SOUTH);  // 把 bottom 放進視圖中

        // 功能按鈕事件處理
        addButton.addActionListener(e -> addFile());
        editButton.addActionListener(e -> editFile());
        deleteButton.addActionListener(e -> deleteFile());
        viewButton.addActionListener(e -> viewFileContent());
    }

    // 更新檔案列表
    private void refreshFileList() {
        listModel.clear();
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                Arrays.sort(files);
                for (File file : files) {
                    listModel.addElement(file.getName());
                }
            } else {
                listModel.addElement("無法讀取資料夾內容");
            }
        } else {
            listModel.addElement("找不到 allData 資料夾");
        }
    }

    // 新增檔案
    private void addFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("選擇要加入的檔案");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            String originalName = selectedFile.getName();
            File destination = new File(folderPath, originalName);

            // 如果已存在同名檔案，自動加序號
            if (destination.exists()) {
                destination = getAvailableFile(destination);
            }

            try {
                Files.copy(selectedFile.toPath(), destination.toPath());
                JOptionPane.showMessageDialog(this, "檔案已成功複製到 allData 資料夾\n名稱：" + destination.getName());
                refreshFileList();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "複製檔案失敗：" + ex.getMessage());
            }
        }
    }

    // 取得不重複的檔案名稱
    private File getAvailableFile(File file) {
        String name = file.getName();
        String baseName;
        String extension = "";

        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > 0) {
            baseName = name.substring(0, dotIndex);
            extension = name.substring(dotIndex); // 包含點號的副檔名
        } else {
            baseName = name;
        }

        int count = 1;
        File newFile;
        do {
            String newName = baseName + "(" + count + ")" + extension;
            newFile = new File(file.getParent(), newName);
            count++;
        } while (newFile.exists());

        return newFile;
    }

    // 修改檔案（重新命名）
    private void editFile() {
        String selected = fileList.getSelectedValue();
        if (selected != null) {
            String newName = JOptionPane.showInputDialog(this, "輸入新檔案名稱：", selected);
            if (newName != null && !newName.trim().isEmpty()) {
                File oldFile = new File(folderPath, selected);
                File newFile = new File(folderPath, newName);
                if (oldFile.renameTo(newFile)) {
                    JOptionPane.showMessageDialog(this, "修改成功");
                    refreshFileList();
                } else {
                    JOptionPane.showMessageDialog(this, "修改失敗");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "請先選擇要修改的檔案");
        }
    }

    // 刪除檔案
    private void deleteFile() {
        String selected = fileList.getSelectedValue();
        if (selected != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "確定要刪除 " + selected + "？", "確認刪除", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                File file = new File(folderPath, selected);
                if (file.delete()) {
                    JOptionPane.showMessageDialog(this, "刪除成功");
                    refreshFileList();
                } else {
                    JOptionPane.showMessageDialog(this, "刪除失敗");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "請先選擇要刪除的檔案");
        }
    }

    // 查詢檔案
    private void searchFile() {
        String keyword = JOptionPane.showInputDialog(this, "輸入檔案名稱關鍵字：");
        if (keyword != null) {
            listModel.clear();
            File folder = new File(folderPath);
            File[] files = folder.listFiles((dir, name) -> name.contains(keyword));
            if (files != null && files.length > 0) {
                for (File file : files) {
                    listModel.addElement(file.getName());
                }
            } else {
                listModel.addElement("沒有符合的檔案");
            }
        }
    }

    private void filterBySubject() {
        String selectedSubject = (String) subjectComboBox.getSelectedItem();
        listModel.clear();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if ("全部".equals(selectedSubject) || file.getName().contains(selectedSubject)) {
                    listModel.addElement(file.getName());
                }
            }
        } else {
            listModel.addElement("找不到 allData 資料夾");
        }
    }

    private void viewFileContent() {
        String selected = fileList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "請先選擇檔案");
            return;
        }

        File file = new File(folderPath, selected);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "檔案不存在");
            return;
        }

        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(file);
            } else {
                JOptionPane.showMessageDialog(this, "系統不支援開啟檔案");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "開啟檔案失敗：" + ex.getMessage());
        }
    }
}