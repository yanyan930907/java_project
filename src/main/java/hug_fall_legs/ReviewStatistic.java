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
            durationFields[i].setText(session.getDuration().toString());
        }
    }
/*
    public void printAllSessions() {    //列出所有複習紀錄
        for (ReviewSession s:statistic) {
            System.out.println("科目：" + s.getSubject());
            System.out.println("時間：" + s.getDurationTime());
            System.out.println("心得：" + s.getNotes());
            System.out.println("時間段：" + s.getTimestamp());
            System.out.println("------");
        }
    }

    public void printBarChart() {  可用 ASCII 顯示柱狀圖  }
*/

}