package hug_fall_legs;

public class TimerManager {
    private long startTime;
    private long pausedTime;
    private boolean isRunning;
    private boolean isPaused;

    public TimerManager() {
        this.startTime = 0;
        this.pausedTime = 0;
        this.isRunning = false;
        this.isPaused = false;
    }

    public void run() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
            isPaused = false;
            System.out.println("Timer started.");
        } else if (isPaused) {
            // 從暫停狀態回復
            long resumeTime = System.currentTimeMillis();
            startTime += (resumeTime - pausedTime); // 調整 startTime
            isPaused = false;
            System.out.println("Timer resumed.");
        } else {
            System.out.println("Timer is already running.");
        }
    }

    public void pause() {
        if (isRunning && !isPaused) {
            pausedTime = System.currentTimeMillis();
            isPaused = true;
            System.out.println("Timer paused.");
        } else {
            System.out.println("Timer is not running or already paused.");
        }
    }

    public Time stop() {
        if (!isRunning) {
            System.out.println("Timer was not started.");
            return new Time(0, 0, 0);
        }

        long endTime = isPaused ? pausedTime : System.currentTimeMillis();
        long durationMillis = endTime - startTime;

        int totalSeconds = (int) (durationMillis / 1000);
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        // 重設狀態
        isRunning = false;
        isPaused = false;
        startTime = 0;
        pausedTime = 0;

        Time elapsed = new Time(hours, minutes, seconds);
        System.out.println("Timer stopped. Elapsed time: " + elapsed);
        return elapsed;
    }
    public boolean isRunning() {
        return isRunning && !isPaused;
    }

    public Time getElapsedTime() {
        long now = isPaused ? pausedTime : System.currentTimeMillis();
        long durationMillis = now - startTime;

        int totalSeconds = (int) (durationMillis / 1000);
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return new Time(hours, minutes, seconds);
    }
}