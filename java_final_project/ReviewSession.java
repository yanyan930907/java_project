package java_project.java_final_project;

public class ReviewSession {
    private String subject;       // 標題（科目）
    private long durationMillis;  // 時長（毫秒）
    private String notes;         // 小總結或心得
    private Date timestamp;       // 複習發生的時間

    public ReviewSession(String subject, long durationMillis, String notes) {
        this.subject = subject;
        this.durationMillis = durationMillis;
        this.notes = notes;
        this.timestamp = new Date();
    }

    // getters & toString()
}

