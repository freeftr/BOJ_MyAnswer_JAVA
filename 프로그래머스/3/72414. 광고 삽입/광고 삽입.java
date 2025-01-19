import java.io.*;
import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int end = timeToInt(play_time);
        int[] total = new int[360000];

        for (String log : logs) {
            String[] s = log.split("-");
            int start = timeToInt(s[0]);
            int finish = timeToInt(s[1]);
            for (int i = start; i < finish; i++) {
                total[i]++;
            }
        }

        int length = timeToInt(adv_time);
        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += total[i];
        }

        long max = sum;
        int res = 0;

        for (int i = length; i < end; i++) {
            sum += total[i] - total[i - length];
            if (sum > max) {
                max = sum;
                res = i - length + 1;
            }
        }

        return intToTime(res);
    }

    static int timeToInt(String s) {
        String[] times = s.split(":");
        int hours = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        int seconds = Integer.parseInt(times[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }
    
    static String intToTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
