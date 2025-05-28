package hug_fall_legs;

import java.util.ArrayList;
import  java.io.*;
import java.util.regex.*;

public class ReviewStatistic {
    private ArrayList<ReviewSession> statistic; // session統計

    public void loadSessionsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 解析每一行的時間與標題
                Pattern pattern = Pattern.compile("\\b(\\d{2}):(\\d{2}):(\\d{2})\\b");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    int hour = Integer.parseInt(matcher.group(1));
                    int minute = Integer.parseInt(matcher.group(2));
                    int second = Integer.parseInt(matcher.group(3));
                    Time duration = new Time(hour, minute, second);
                    String title = line.substring(matcher.end()).trim();
                    statistic.add(new ReviewSession(title, duration));
                } else {
                    System.err.println("格式錯誤（忽略該行）：" + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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