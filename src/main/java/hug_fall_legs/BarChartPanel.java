package hug_fall_legs;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JPanel;

public class BarChartPanel {
    public static JPanel createBarChartPanel(String timeRange) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // 根據時間範圍模擬不同資料
        switch (timeRange) {
            case "一天":
                dataset.addValue(5, "時間", "5/27");
                break;
            case "一週":
                dataset.addValue(3, "時間", "5/21");
                dataset.addValue(6, "時間", "5/22");
                dataset.addValue(4, "時間", "5/23");
                break;
            case "一月":
                dataset.addValue(15, "時間", "5月上旬");
                dataset.addValue(20, "時間", "5月中旬");
                dataset.addValue(25, "時間", "5月下旬");
                break;
            case "一季":
                dataset.addValue(60, "時間", "3月");
                dataset.addValue(70, "時間", "4月");
                dataset.addValue(50, "時間", "5月");
                break;
            case "全部":
            default:
                dataset.addValue(10, "時間", "1");
                dataset.addValue(5, "時間", "5/28");
                dataset.addValue(5, "時間", "5/27");
                dataset.addValue(5, "時間", "5/26");
                dataset.addValue(5, "時間", "5/25");
                dataset.addValue(5, "時間", "5/24");
                dataset.addValue(5, "時間", "5/17");
                break;
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "時間統計長條圖",    // 圖表標題
                "日期",         // X 軸標籤
                "時間",         // Y 軸標籤
                dataset         // 資料集
        );

        return new ChartPanel(chart);  // 回傳 ChartPanel 而不是 JFrame
    }
}