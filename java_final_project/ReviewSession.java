package hug_fall_legs;
import java.util.Date;

public class ReviewSession {
    private String subject;       // 標題（科目）
    private long durationSeconds;  // 時長（秒）
    private String notes;         // 小總結或心得
    private Date timestamp;       // 複習發生的時間

    public ReviewSession(String subject, long durationSeconds, String notes) {
        this.subject = subject;
        this.durationSeconds = durationSeconds;
        this.notes = notes;
        this.timestamp = new Date();
    }

    public String getSubject() { return subject; }
    public long getDurationSeconds() { return durationSeconds; }
    public String getDurationTime() {
        long tmp = durationSeconds;
        long hour = tmp/3600;
        long minute = tmp/60;
        tmp = tmp%60;
        long second = tmp;
        return hour + " : " + minute + " : " + second;
    }
    public String getNotes() { return notes; }
    public Date getTimestamp() {return timestamp; }

    @Override
    public String toString(){
        return "科目：" + getSubject() + "時間：" + getDurationTime() + "心得：" + getNotes() + "時間段：" + getTimestamp();
    }
}

