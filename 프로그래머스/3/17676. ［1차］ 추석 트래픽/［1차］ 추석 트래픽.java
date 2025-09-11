import java.util.*;
class Solution {
    public int solution(String[] lines) {
        int len = lines.length;
        int[] start = new int[len];
        int[] end = new int[len];
        int idx = 0;

        for (String line : lines) {
            String[] s = line.split("\\s+");
            String timeStr = s[1];
            String durStr  = s[2].substring(0, s[2].length() - 1);

            String[] hms = timeStr.split(":");
            int hour = Integer.parseInt(hms[0]);
            int min  = Integer.parseInt(hms[1]);
            String[] secParts = hms[2].split("\\.");
            int sec = Integer.parseInt(secParts[0]);
            int milli = 0;
            if (secParts.length > 1) {
                String msStr = secParts[1];
                if (msStr.length() < 3) msStr = (msStr + "000").substring(0, 3);
                else msStr = msStr.substring(0, 3);
                milli = Integer.parseInt(msStr);
            }
            int endMs = hour * 3600 * 1000 + min * 60 * 1000 + sec * 1000 + milli;

            int durMs;
            int dot = durStr.indexOf('.');
            if (dot < 0) {
                durMs = Integer.parseInt(durStr) * 1000;
            } else {
                String secPart = durStr.substring(0, dot);
                String frac = durStr.substring(dot + 1);
                if (frac.length() < 3) frac = (frac + "000").substring(0, 3);
                else frac = frac.substring(0, 3);
                durMs = Integer.parseInt(secPart) * 1000 + Integer.parseInt(frac);
            }

            start[idx] = endMs - durMs + 1;
            end[idx]   = endMs;
            idx++;
        }

        int answer = 0;

        for (int i = 0; i < len; i++) {
            int ws = start[i], we = ws + 999;
            int cnt = 0;
            for (int j = 0; j < len; j++) {
                if (start[j] <= we && end[j] >= ws) cnt++;
            }
            answer = Math.max(answer, cnt);

            ws = end[i]; we = ws + 999;
            cnt = 0;
            for (int j = 0; j < len; j++) {
                if (start[j] <= we && end[j] >= ws) cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}
