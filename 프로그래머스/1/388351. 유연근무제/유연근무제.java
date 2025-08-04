import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;

        for (int i = 0; i < n; i++) {
            int target = toMinutes(schedules[i]);
            boolean isLate = false;

            for (int d = 0; d < 7; d++) {
                int day = (startday - 1 + d) % 7;

                if (day == 5 || day == 6) continue;

                int actual = toMinutes(timelogs[i][d]);
                if (actual > target + 10) {
                    isLate = true;
                    break;
                }
            }

            if (!isLate) {
                answer++;
            }
        }

        return answer;
    }

    private int toMinutes(int time) {
        int hour = time / 100;
        int minute = time % 100;
        return hour * 60 + minute;
    }
}
