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

        // 每秒刷新一次畫面上的時間顯示
        swingTimer = new Timer(1000, e -> {
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
            System.out.println("全部卡片被按下");

        } else if (source == timePanel.setTimeButton) {
            System.out.println("設定時間被按下");
            timerManager.run(); // 啟動或繼續計時

        } else if (source == buttonPanel.statisticButton) {
            System.out.println("統計資料被按下");

        } else if (source == buttonPanel.errorButton) {
            System.out.println("錯誤整理被按下");

        } else if (source == timePanel.stopTimeButton) {
            System.out.println("暫停時間被按下");
            timerManager.pause();

        } else if (source == timePanel.recordButton) {
            System.out.println("結束時間被按下");
            Time finalTime = timerManager.stop();
            Time zeroTime = new Time(0, 0, 0);
            timePanel.updateTime(zeroTime);

        } else if (source == buttonPanel.allDataButton) {
            System.out.println("所有資料被按下");
        }
    }
   
}