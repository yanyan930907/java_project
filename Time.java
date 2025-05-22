package hug_fall_legs;

public class Time{
    private int hour;
    private int minute;
    private int second;

    // 建構子
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // 空建構子 (可以不傳參數，預設0點0分0秒)
    public Time() {
        this(0, 0, 0);
    }

    // Getter 與 Setter
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        // 格式化輸出時間，例如 09:05:03
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
