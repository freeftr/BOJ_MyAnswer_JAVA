import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int[] total = new int[100 * 60 * 60];

        if (play_time.equals(adv_time)) return "00:00:00";

        for (String log : logs) {
            int s = strToInt(log.split("-")[0]);
            int e = strToInt(log.split("-")[1]);
            for (int i = s; i < e; i++) {
                total[i]++;
            }
        }

        long sum = 0;
        int len = strToInt(adv_time);
        int end = strToInt(play_time);

        for (int i = 0; i < len; i++) {
            sum += total[i];
        }
        long max = sum;
        answer = "00:00:00";

        for (int i = len; i < end; i++) {
            sum += total[i];
            sum -= total[i - len];

            if (sum > max) {
                max = sum;
                answer = intToStr(i - len + 1);
            }
        }

        return answer;
    }

    static int strToInt(String s) {
        int hour = Integer.parseInt(s.split(":")[0]);
        int min  = Integer.parseInt(s.split(":")[1]);
        int sec  = Integer.parseInt(s.split(":")[2]);
        return hour * 60 * 60 + min * 60 + sec;
    }

    static String intToStr(int s) {
        int hour = s / 3600;
        s %= 3600;
        int min  = s / 60;
        int sec  = s % 60;

        String h  = (hour < 10 ? "0" : "") + hour;
        String m  = (min  < 10 ? "0" : "") + min;
        String se = (sec  < 10 ? "0" : "") + sec;

        return h + ":" + m + ":" + se;
    }
}
