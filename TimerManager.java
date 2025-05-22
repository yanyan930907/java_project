package hug_fall_legs;

public class TimerManager {
    private long startMillis;
    private long pauseMillis;
    private boolean running = false;

    public void run() {
        if (!running) {
            startMillis = System.currentTimeMillis();
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
}