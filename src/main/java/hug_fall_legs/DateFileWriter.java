package hug_fall_legs;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class DateFileWriter {

    private String filename;

    // 建構子，傳入檔名
    public DateFileWriter(String filename) {
        this.filename = filename;
    }

    // 取得今天的月份和日期字串
    private String getTodayMonthDay(Time time) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        return "今天是 " + month + " 月 " + day + " 號 "+ time.toString()+"\n";
    }

    // 附加寫入日期到檔案
    public void appendDateToFile(Time time) {
        String content = getTodayMonthDay(time);

        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(content);
            System.out.println("附加寫入成功：" + content.trim());
        } catch (IOException e) {
            System.err.println("寫入檔案失敗");
            e.printStackTrace();
        }
    }
}
