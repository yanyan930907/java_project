package hug_fall_legs;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class AddCardDialog extends JDialog{
    private JTextField frontTextField, imagePathField, subjectField, hintField, filePathField;
    private CardManager manager;
    private AllCardWindow cardWindow;
    private String folderPath = "Picture";
    private String imageName ="";
    private JButton addButton, cancelButton,addImageButton;
    private JLabel imageNameLabel;

    public AddCardDialog() {
        setTitle("新增卡片");


        setLayout(new GridLayout(7,2,10, 10));
        setSize(400,300);
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
        subjectField = new JTextField();
        add(subjectField);

        add(new JLabel("    File path："));
        filePathField = new JTextField();
        add(filePathField);


        addButton = new JButton("新增卡片");
        addButton.addActionListener(e -> {
            Card newCard = new Card(frontTextField.getText(),imagePathField.getText(),hintField.getText(),subjectField.getText(),filePathField.getText());
            manager.addCard((newCard));
            //manager.saveToJson("card.json");
            //cardWindow.refreshCardDisplay();
            dispose();
        });
        add(addButton);

        cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

    }
    private void addImageFile() {
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
}
