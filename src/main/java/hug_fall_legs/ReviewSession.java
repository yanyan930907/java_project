package hug_fall_legs;

import  java.io.*;
import java.util.regex.*;

public class ReviewSession {
    private String  title;
    private Time duration;  // 時長

    public ReviewSession(String collectTime) {
        try (BufferedReader br = new BufferedReader(new FileReader(collectTime))) {
            String line = br.readLine(); // 只讀一行
            if (line != null) {
                // 使用正則表達式找時間字串
                Pattern pattern = Pattern.compile("\\b(\\d{2}):(\\d{2}):(\\d{2})\\b");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    int hour = Integer.parseInt(matcher.group(1));
                    int minute = Integer.parseInt(matcher.group(2));
                    int second = Integer.parseInt(matcher.group(3));
                    this.duration = new Time(hour, minute, second);

                    // title 是時間後面的文字
                    int endIndex = matcher.end();
                    this.title = line.substring(endIndex).trim();
                } else {
                    throw new IllegalArgumentException("格式錯誤：未找到 HH:mm:ss");
                }
            } else {
                throw new IllegalArgumentException("檔案為空");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
