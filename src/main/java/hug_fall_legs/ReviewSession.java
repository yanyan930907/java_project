package hug_fall_legs;

import  java.io.*;
import java.util.regex.*;

public class ReviewSession {
    private String  title;
    private Time duration;  // 時長

    public ReviewSession(String collectTime) {
        try {
            // 使用正則表示式找出時間（HH:mm:ss）
            Pattern pattern = Pattern.compile("\\b(\\d{2}):(\\d{2}):(\\d{2})\\b");
            Matcher matcher = pattern.matcher(collectTime);

            if (matcher.find()) {
                int hour = Integer.parseInt(matcher.group(1));
                int minute = Integer.parseInt(matcher.group(2));
                int second = Integer.parseInt(matcher.group(3));
                this.duration = new Time(hour, minute, second);

                // 取出時間字串結束後的文字當作 title
                int endIndex = matcher.end();
                this.title = collectTime.substring(endIndex).trim();
            } else {
                throw new IllegalArgumentException("格式錯誤：未找到 HH:mm:ss");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.title = "錯誤格式";
            this.duration = new Time();
        }
    }


    public ReviewSession(String title, Time duration) {
        this.title = title;
        this.duration = duration;
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
