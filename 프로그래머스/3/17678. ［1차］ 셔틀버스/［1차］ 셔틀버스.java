import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] arr = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) arr[i] = stringToInt(timetable[i]);
        Arrays.sort(arr);

        int bus = 9 * 60;
        int idx = 0;
        int lastBoarded = -1;

        for (int i = 0; i < n; i++) {
            int seats = m;
            while (seats > 0 && idx < arr.length && arr[idx] <= bus) {
                lastBoarded = arr[idx];
                idx++;
                seats--;
            }
            if (i == n - 1) {
                if (seats > 0) return intToString(bus);
                else           return intToString(lastBoarded - 1);
            }
            bus += t;
        }
        return intToString(bus);
    }

    static int stringToInt(String s) {
        String[] p = s.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    static String intToString(int minutes) {
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }
}
