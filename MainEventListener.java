package hug_fall_legs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 事件監聽器分離成獨立類別
public class MainEventListener implements ActionListener {

    private ButtonPanel buttonPanel;
    private TimePanel timePanel;
    private TimerManager timerManager = new TimerManager();
    private Timer swingTimer;

    public MainEventListener(ButtonPanel buttonPanel, TimePanel timePanel) {
        this.buttonPanel = buttonPanel;
        this.timePanel = timePanel;

        swingTimer = new javax.swing.Timer(1000, e -> {
            if (timerManager.isRunning()) {
                Time time = timerManager.getElapsedTime();
                timePanel.updateTime(time);
            }
        });
        swingTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == buttonPanel.allCardsButton) {
            // 全部卡片邏輯
            System.out.println("全部卡片被按下");
           
        } else if (source == timePanel.setTimeButton) {
            // 設定時間邏輯
            System.out.println("設定時間被按下");
            timerManager.run();
        } else if (source == buttonPanel.statisticButton) {
            // 統計資料邏輯
            System.out.println("統計資料被按下");
           
        } else if (source == buttonPanel.errorButton) {
            // 錯誤整理邏輯
            System.out.println("錯誤整理被按下");
           
        } else if (source == timePanel.stopTimeButton) {
            // 暫停時間邏輯
            System.out.println("暫停時間被按下");
            timerManager.pause();
        }
        else if (source == timePanel.recordButton) {
            // 暫停時間邏輯
            System.out.println("結束時間被按下");
            timerManager.pause();
            Time time = new Time();
            timePanel.updateTime(time);
        }
        else if (source == buttonPanel.allDataButton) {
            // 所有資料邏輯
            System.out.println("所有資料被按下");
        
        }
    }
   
}