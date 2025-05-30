package hug_fall_legs;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.BufferedWriter;


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
        return month + "/" + day + " "+ time.toString()+"\n";
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

    public void appendDateAndNote(Time time, String note) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        String line = month + "/" + day + " " + time.toString() + " " + note.trim() + "\n";

        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(line);
            System.out.println("寫入成功：" + line.trim());
        } catch (IOException e) {
            System.err.println("寫入檔案失敗");
            e.printStackTrace();
        }
    }


}
