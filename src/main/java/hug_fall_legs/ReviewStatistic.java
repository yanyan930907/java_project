package hug_fall_legs;

import java.util.*;
import  java.io.*;
import javax.swing.*;

public class ReviewStatistic {  // 讀整個.txt檔,多個ReviewSession
    JTextField[] titleFields;    // 讀書大綱
    JTextField[] durationFields; // 時長
    private List<ReviewSession> sessions; // session清單


    // 讀完所有資料(.txt每一行)
    public ReviewStatistic(String collectTime) {
        sessions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(collectTime))) {
            String line;
            while ((line = br.readLine()) != null) {
                sessions.add(new ReviewSession(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 把資料填到GUI的JTextField
    public void populateFields(JTextField[] titleFields, JTextField[] durationFields) {
        for (int i = 0; i < sessions.size() && i < titleFields.length && i < durationFields.length; i++) {
            ReviewSession session = sessions.get(i);
            titleFields[i].setText(session.getTitle());
            durationFields[i].setText(session.getDateTimeText());

        }
    }

    public List<ReviewSession> getSessions() {
        return sessions;
    }

}