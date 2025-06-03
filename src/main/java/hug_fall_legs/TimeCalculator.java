package hug_fall_legs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TimeCalculator {

    public static String calculateTotalStudyTime(String filePath) {
        int totalSeconds = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length >= 2) {
                    String timeStr = parts[1];
                    String[] hms = timeStr.split(":");

                    if (hms.length == 3) {
                        int hours = Integer.parseInt(hms[0]);
                        int minutes = Integer.parseInt(hms[1]);
                        int seconds = Integer.parseInt(hms[2]);

                        totalSeconds += hours * 3600 + minutes * 60 + seconds;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
