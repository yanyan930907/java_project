package java_final_project;

import java.util.ArrayList;

public class ReviewStatistics {
    private ArrayList<ReviewSession> statistic; // session統計
    

    public void addSession(ReviewSession session) {
        // 加總到對應的 subject 與 date
        statistic.add(session);
    }

    public String getLastReviewedSubject(){ //回傳最新複習科目
        if (statistic.isEmpty()) return "尚無複習紀錄";
        return statistic.get(statistic.size()-1).getSubject();
    }

    public long getTotalSecond() {  // 回傳總秒數
        long total=0;
        for (ReviewSession s:statistic) {
            total += s.getDurationSeconds();
        }
        return total;
    }

    public String getTotalTime() {  // 時 : 分 : 秒
        long tmp = getTotalSecond();
        long hour = tmp/3600;
        tmp = tmp%3600;
        long minute = tmp/60;
        tmp = tmp%60;
        long second = tmp;
        return hour + " : " + minute + " : " + second;
    }

    public void printAllSessions() {    //列出所有複習紀錄
        for (ReviewSession s:statistic) {
            System.out.println("科目：" + s.getSubject());
            System.out.println("時間：" + s.getDurationTime());
            System.out.println("心得：" + s.getNotes());
            System.out.println("時間段：" + s.getTimestamp());
            System.out.println("------");
        }
    }

    public void printBarChart() { /* 可用 ASCII 顯示柱狀圖 */ }
}
