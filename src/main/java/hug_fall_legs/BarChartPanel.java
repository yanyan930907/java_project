package hug_fall_legs;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BarChartPanel {
    public static JPanel createBarChartPanel(String timeRange) {
        DefaultCategoryDataset dataset;
        switch (timeRange) {
            case "一天":
                dataset = createDailyDataset();
                break;
            case "一週":
                dataset = createWeeklyDataset();
                break;
            case "一月":
                dataset = createMonthlyDataset();
                break;
            case "一季":
                dataset = createQuarterlyDataset();
                break;
            case "全部":

            default:
                dataset = createAllTimeDataset();
                break;
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "BarChat",    // 圖表標題
                "Date",         // X 軸標籤
                "Time",         // Y 軸標籤
                dataset         // 資料集
        );

        return new ChartPanel(chart);  // 回傳 ChartPanel
    }
    private static DefaultCategoryDataset createDailyDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5, "time", "5/27");
        return dataset;
    }

    private static DefaultCategoryDataset createWeeklyDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(3, "time", "5/21");
        dataset.addValue(6, "time", "5/22");
        dataset.addValue(4, "time", "5/23");
        return dataset;
    }

    private static DefaultCategoryDataset createMonthlyDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15, "time", "5月上旬");
        dataset.addValue(20, "time", "5月中旬");
        dataset.addValue(25, "time", "5月下旬");
        return dataset;
    }

    private static DefaultCategoryDataset createQuarterlyDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d HH:mm:ss");
        try (BufferedReader br = new BufferedReader(new FileReader("collectTime.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String dateStr = parts[0]; // 取得日期字串"4/28"
                    String timeStr = parts[1]; // 取得時間字串"00:00:03"
                    try {
                        // 將日期和時間合併後解析成 Date 物件
                        Date date = dateFormat.parse(dateStr + " " + timeStr);
                        if (date != null) {
                            // 計算當天午夜的時間戳記(毫秒)
                            // 方法：用目前時間毫秒數減去時間部分的毫秒數
                            long midnight = date.getTime() - (date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds()) * 1000;
                            long timestamp = date.getTime();// 取得當前時間(毫秒)
                            double value = (timestamp - midnight) / 1000.0;
                            dataset.addValue(value, "time", getSeason(dateStr));
                        }
                    } catch (ParseException e) {
                        System.err.println("日期解析錯誤: " + dateStr + " " + timeStr);
                    }
                }
            }
        } catch (IOException e) {
            // 若讀取檔案發生錯誤，印出例外訊息
            e.printStackTrace();
        }

        // 回傳填好資料的資料集物件
        return dataset;
    }

    private static DefaultCategoryDataset createAllTimeDataset() {
        // 建立一個 DefaultCategoryDataset 物件用來存放資料
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // 設定日期格式，符合資料中日期與時間的格式，例如 "4/28 00:00:03"
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d HH:mm:ss");

        // 使用 try-with-resources 來開啟並自動關閉檔案讀取器
        try (BufferedReader br = new BufferedReader(new FileReader("collectTime.txt"))) {
            String line;
            // 逐行讀取檔案內容
            while ((line = br.readLine()) != null) {
                // 假設每行格式為 "4/28 00:00:03"
                // 使用空白拆分成日期和時間兩部分
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String dateStr = parts[0]; // 取得日期字串，例如 "4/28"
                    String timeStr = parts[1]; // 取得時間字串，例如 "00:00:03"
                    try {
                        // 將日期和時間合併後解析成 Date 物件
                        Date date = dateFormat.parse(dateStr + " " + timeStr);
                        if (date != null) {
                            // 計算當天午夜的時間戳記(毫秒)
                            // 方法：用目前時間毫秒數減去時間部分的毫秒數
                            long midnight = date.getTime() - (date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds()) * 1000;

                            // 取得當前時間的時間戳記(毫秒)
                            long timestamp = date.getTime();

                            // 計算自午夜以來的秒數，並轉成 double
                            double value = (timestamp - midnight) / 1000.0;

                            // 將秒數 value 與日期字串 dateStr 加入資料集中
                            // "time" 為數據系列名稱，dateStr 為類別鍵
                            dataset.addValue(value, "time", dateStr);
                        }
                    } catch (ParseException e) {
                        // 若日期格式解析錯誤，輸出錯誤訊息
                        System.err.println("日期解析錯誤: " + dateStr + " " + timeStr);
                    }
                }
            }
        } catch (IOException e) {
            // 若讀取檔案發生錯誤，印出例外訊息
            e.printStackTrace();
        }

        // 回傳填好資料的資料集物件
        return dataset;
    }
    private static String getSeason(String dateStr) {
        // 解析字串 "5/28" -> 月份是 5
        String[] parts = dateStr.split("/");
        int month = Integer.parseInt(parts[0]);

        // 判斷季節
        if (month >= 3 && month <= 5) {
            return "3-5";
        } else if (month >= 6 && month <= 8) {
            return "6-8";
        } else if (month >= 9 && month <= 11) {
            return "9-11";
        } else {
            return "12-2"; // 12, 1, 2
        }
    }
}
