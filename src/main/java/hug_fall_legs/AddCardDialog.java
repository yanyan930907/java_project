package hug_fall_legs;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;


public class AddCardDialog extends JDialog{
    private JTextField frontTextField, imagePathField, subjectField, hintField,filePathField;
    private CardManager manager;
    private AllCardWindow cardWindow;
    private String folderPath;
    private String imageName ="";
    private ArrayList<String> subjectsList = new ArrayList<>();
    private JComboBox<String> categoryComboBox;
    private JButton addButton, cancelButton,addImageButton,addsubjectButton,addrelatedButton;
    private JLabel imageNameLabel,filePath;
    private JPanel subjectPanel;
    private JList<String> fileList;
    private DefaultListModel<String> listModel;

    public AddCardDialog(AllCardWindow a) {
        setTitle("新增卡片");
        subjectsList.add("全部");
        subjectsList.add("電腦網路");
        subjectsList.add("演算法");
        subjectsList.add("Java");
        subjectsList.add("Verilog");


        setLayout(new GridLayout(8,2,10, 10));
        setSize(400,375);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel("    Front Text："));
        frontTextField = new JTextField();
        add(frontTextField);

        add(new JLabel("    Your Image："));
        addImageButton = new JButton("瀏覽");
        add(addImageButton);
        addImageButton.addActionListener(e ->addImageFile());

        add(new JLabel("    Your Image Path："));
        imageNameLabel = new JLabel("目前沒有選擇圖片");
        add(imageNameLabel);


        add(new JLabel("    Hint："));
        hintField = new JTextField();
        add(hintField);

        add(new JLabel("    Subject："));
        categoryComboBox = new JComboBox<>(subjectsList.toArray(new String[0]));
        categoryComboBox.setMaximumRowCount(4);
        subjectPanel= new JPanel();
        subjectPanel.add(categoryComboBox);
        addsubjectButton = new JButton("新增科目");
        addsubjectButton.addActionListener(e -> {
            JFrame newFrame = new JFrame("新增科目");
            newFrame.setSize(300, 200);
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setLocationRelativeTo(null);
            newFrame.setLayout(new BorderLayout());

            // 中間的 TextField
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(200, 30));
            textField.setMaximumSize(textField.getPreferredSize());

            newFrame.add(textField, BorderLayout.CENTER);

            // 下方的按鈕區
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton okButton = new JButton("確認");
            JButton cancelButton = new JButton("取消");

            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            newFrame.add(buttonPanel, BorderLayout.SOUTH);

            // 按鈕事件範例
            okButton.addActionListener(ev-> {
                String input = textField.getText();
                if (input != null && !input.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "成功新增" + input);
                    subjectsList.add(input);
                    categoryComboBox.addItem(input);
                }
                newFrame.dispose();
            });

            cancelButton.addActionListener(ev -> {
                newFrame.dispose(); // 關閉新視窗
            });

            newFrame.setVisible(true);
        });
        subjectPanel.add(addsubjectButton);
        add(subjectPanel);

        add(new JLabel("    File path："));
        addrelatedButton = new JButton("找檔案");
        add(addrelatedButton);
        add(new JLabel("    Your Related File："));
        filePath = new JLabel("尚未選擇關聯檔案");
        add(filePath);
        addrelatedButton.addActionListener(e -> {
            JFrame newFrame = new JFrame("新增關聯檔案");
            newFrame.setSize(300, 400);
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setLocationRelativeTo(null);
            newFrame.setLayout(new BorderLayout());

            listModel = new DefaultListModel<>();
            fileList = new JList<>(listModel);
            refreshFileList();

            JScrollPane scrollPane = new JScrollPane(fileList);
            scrollPane.setPreferredSize(new Dimension(300, 200));
            newFrame.add(scrollPane, BorderLayout.CENTER);

            // 下方的按鈕區
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton okButton = new JButton("確認");
            JButton cancelButton = new JButton("取消");

            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            newFrame.add(buttonPanel, BorderLayout.SOUTH);

            // 按鈕事件範例
            okButton.addActionListener(ev-> {
                String selected = fileList.getSelectedValue();
                if (selected != null) {
                    filePath.setText(selected);
                }
                newFrame.dispose();
            });

            cancelButton.addActionListener(ev -> {
                newFrame.dispose(); // 關閉新視窗
            });

            newFrame.setVisible(true);
        });


        addButton = new JButton("新增卡片");
        addButton.addActionListener(e -> {
            Card newCard = new Card(frontTextField.getText(),imageNameLabel.getText(),hintField.getText(), (String) categoryComboBox.getSelectedItem(),filePath.getText());
            CardManager c = new CardManager();
            c.addCard(newCard);
            a.readCards(0);
            dispose();
        });
        add(addButton);

        cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

    }
    private void addImageFile() {
        folderPath = "Picture";
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
                imageName = destination.getName().toLowerCase();
                if (!(imageName.endsWith(".png") || imageName.endsWith(".jpg") ||
                        imageName.endsWith(".jpeg") || imageName.endsWith(".gif"))) {

                    throw new IllegalArgumentException("只允許加入 PNG、JPG、JPEG、GIF 格式的圖片！");
                }
                Files.copy(selectedFile.toPath(), destination.toPath());
                JOptionPane.showMessageDialog(this, destination.getName() +"已新增到 Picture 資料夾" );
                imageNameLabel.setText(imageName);
            }catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "新增檔案失敗：" + ex.getMessage());
            }
        }
    }
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
    private void refreshFileList() {
        listModel.clear();
        folderPath = "alldata";
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
}
