package hug_fall_legs;

import java.util.*;
import  java.io.*;
import javax.swing.*;

public class ReviewStatistic {
    JTextField[] titleFields;    // 讀書大綱
    JTextField[] durationFields; // 時長
    private List<ReviewSession> sessions; // session統計

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