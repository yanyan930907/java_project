package hug_fall_legs;
public class TimerManager {
    private long startMillis;
    private long pauseMillis;
    private boolean running = false;
    private boolean isRun = false;

    public void run() {
        if (!running) {
            if (!isRun) {
                // 第一次開始
                startMillis = System.currentTimeMillis();
            } else {
                // 暫停後繼續：補上暫停時間
                long pausedDuration = System.currentTimeMillis() - pauseMillis;
                startMillis += pausedDuration;
            }
            isRun = true;
            running = true;
        }
    }

    public void pause() {
        if (running) {
            pauseMillis = System.currentTimeMillis();
            running = false;
        }
    }

    public Time stop() {
        if (running) {
            pauseMillis = System.currentTimeMillis();
            running = false;
        }
        isRun = false;
        return convertMillisToTime(pauseMillis - startMillis);
    }

    public boolean isRunning() {
        return running;
    }

    public Time getElapsedTime() {
        long current = running ? System.currentTimeMillis() : pauseMillis;
        return convertMillisToTime(current - startMillis);
    }

    private Time convertMillisToTime(long millis) {
        int totalSeconds = (int) (millis / 1000);
        int hour = totalSeconds / 3600;
        int minute = (totalSeconds % 3600) / 60;
        int second = totalSeconds % 60;
        return new Time(hour, minute, second);
    }

    public void reset() {
        startMillis = 0;
        pauseMillis = 0;
        running = false;
        isRun = false;
    }
}