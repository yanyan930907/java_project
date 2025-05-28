package hug_fall_legs;

import  java.io.*;
import java.util.regex.*;

public class ReviewSession {    // 讀一行資料
    private String  title;
    private Time duration;  // 時長
    
    public ReviewSession(String line) { // line為.txt檔中的一行資料
        try {
            // 抓到這樣的時間格式（00:00:00）
            Pattern pattern = Pattern.compile("\\b(\\d{2}):(\\d{2}):(\\d{2})\\b");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                int hour = Integer.parseInt(matcher.group(1));
                int minute = Integer.parseInt(matcher.group(2));
                int second = Integer.parseInt(matcher.group(3));
                this.duration = new Time(hour, minute, second);

                // 取出時間字串結束後的文字當作title
                int endIndex = matcher.end();   // 時間部分結束的位置，就像index
                this.title = line.substring(endIndex).trim();
            } else {
                throw new IllegalArgumentException("格式錯誤：未找到 hh:mm:ss");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.title = "錯誤格式";
            this.duration = new Time(); // 預設 00:00:00
        }
    }


    public String getTitle() { return title; }
    public Time getDuration() { return duration; }

    //public Time getTimestamp() { return timestamp; }
    /*
    @Override
    public String toString(){
        return "科目：" + getSubject() + "時間：" + getDurationTime() + "心得：" + getNotes() + "時間段：" + getTimestamp();
    }*/
}
