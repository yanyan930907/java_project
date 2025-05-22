package java_final_project;

public class TimerManager {
    private long startTime;
    private long pauseTime;
    private boolean running;

    public void start() { /* 初始化 startTime */ }
    public void pause() { /* 暫停，紀錄 pauseTime */ }
    public void resume() { /* 恢復，調整 startTime */ }
    public long stop() { /* 返回總耗時 */ }

    public boolean isRunning() { return running; }
}
