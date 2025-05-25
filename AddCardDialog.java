package hug_fall_legs;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class AddCardDialog extends JDialog{
    private JTextField frontTextField, imagePathField, subjectField, hintField, filePathField;
    private CardManager manager;
    private AllCardWindow cardWindow;
    private JButton addButton, cancelButton;

    public AddCardDialog(JFrame parent, CardManager manager, AllCardWindow cardWindow) {
        super(parent,"新增卡片",true);
        this.manager = manager;
        this.cardWindow = cardWindow;

        setLayout(new GridLayout(6,2,10, 10));
        setSize(400,300);
        setLocationRelativeTo(parent);

        add(new JLabel("Front Text："));
        frontTextField = new JTextField();
        add(frontTextField);

        add(new JLabel("Image Path："));
        imagePathField = new JTextField();
        add(imagePathField);

        add(new JLabel("Hint："));
        hintField = new JTextField();
        add(hintField);

        add(new JLabel("Subject："));
        subjectField = new JTextField();
        add(subjectField);

        add(new JLabel("File path："));
        filePathField = new JTextField();
        add(filePathField);


        addButton = new JButton("新增卡片");
        addButton.addActionListener(e -> {
            Card newCard = new Card(frontTextField.getText(),imagePathField.getText(),hintField.getText(),subjectField.getText(),filePathField.getText());
            manager.addCard((newCard));
            manager.saveToJson("card.json");
            cardWindow.refreshCardDisplay();
            dispose();
        });
        add(addButton);

        cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

    }
}
