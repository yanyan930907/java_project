package hug_fall_legs;

public class TimeParser {
    public static Time parseTime(String timeStr) {
        // 移除「今天是」並分割字串
        String[] parts = timeStr.replace("今天是", "").trim().split(" ");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1].replace("月", ""));
        int hour = Integer.parseInt(parts[2].split(":")[0]);
        int minute = Integer.parseInt(parts[2].split(":")[1]);
        int second = Integer.parseInt(parts[2].split(":")[2].replace("號", ""));

        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
        System.out.println("Second: " + second);
        // 回傳 Time 物件
        return new Time(hour, minute, second);
    }
}
