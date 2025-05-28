package hug_fall_legs;

import  java.io.*;
import java.util.regex.*;

public class ReviewSession {    // 讀一行資料
    private String  title;
    private Time duration;  // 時長
    private String dateTimeText;


    public ReviewSession(String line) { // line為.txt檔中的一行資料
        try {
            // 抓到這樣的時間格式（00:00:00）
            Pattern pattern = Pattern.compile("(\\d{1,2}/\\d{1,2})\\s+(\\d{2}):(\\d{2}):(\\d{2})");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                int hour = Integer.parseInt(matcher.group(2));
                int minute = Integer.parseInt(matcher.group(3));
                int second = Integer.parseInt(matcher.group(4));
                this.duration = new Time(hour, minute, second);

                this.dateTimeText = matcher.group(1) + " " + matcher.group(2) + ":" + matcher.group(3) + ":" + matcher.group(4);

                // 取出時間字串結束後的文字當作title
                int endIndex = matcher.end();   // 時間部分結束的位置，就像index
                this.title = line.substring(endIndex).trim();
            } else {
                throw new IllegalArgumentException("格式錯誤：未找到 日期+時間");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.title = "錯誤格式";
            this.duration = new Time(); // 預設 00:00:00
        }
    }

    public String getDateTimeText() { return dateTimeText; }

    public String getTitle() { return title; }
    public Time getDuration() { return duration; }

}
