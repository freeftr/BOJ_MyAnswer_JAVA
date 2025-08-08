import java.io.*;
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int crewCnt = timetable.length;
        int[] intTimeTable = new int[crewCnt];

        for (int i = 0; i < crewCnt; i++) {
            intTimeTable[i] = makeIntTime(timetable[i]);
        }
        Arrays.sort(intTimeTable);

        int crewIdx = 0;
        int answerIntTime = 0;

        for (int i = 0; i < n; i++) {
            int capacity = m;
            int curBusTime = 9 * 60 + i * t;

            while (capacity > 0 && crewIdx < crewCnt && intTimeTable[crewIdx] <= curBusTime) {
                if (i == n - 1 && capacity == 1) {
                    answerIntTime = intTimeTable[crewIdx] - 1;
                }
                crewIdx++;
                capacity--;
            }

            if (i == n - 1 && capacity > 0) {
                answerIntTime = curBusTime;
            }
        }

        int hour  = answerIntTime / 60;
        int minute = answerIntTime % 60;
        return String.format("%02d:%02d", hour, minute);
    }

    static int makeIntTime(String time) {
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int min = Integer.parseInt(s[1]);
        return hour * 60 + min;
    }
}
