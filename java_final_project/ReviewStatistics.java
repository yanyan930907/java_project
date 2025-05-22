package java_final_project;

import java.util.*;
import java.time.*;

public class ReviewStatistics {
    private Map<String, Long> timePerSubject;
    private Map<LocalDate, Long> timePerDay;
    
    public ReviewStatistics(){
        
    }

    public void addSession(ReviewSession session) {
        // 加總到對應的 subject 與 date
    }

    public void printBarChart() { /* 可用 ASCII 顯示柱狀圖 */ }
    public String getLastReviewedSubject() { /* 回傳最新複習的科目 */ }
}
