import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int m = cores.length;
        if (n <= m) return n;

        long left = 0;
        long right = 10000L * n;

        while (left < right) {
            long mid = (left + right) / 2;
            if (check(cores, mid, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long time = left;
        long doneBefore = 0;
        for (int i = 0; i < m; i++) {
            doneBefore += (time - 1) / cores[i] + 1;
        }

        long remain = n - doneBefore;

        for (int i = 0; i < m; i++) {
            if (time % cores[i] == 0) {
                remain--;
                if (remain == 0) return i + 1;
            }
        }

        return -1;
    }

    static boolean check(int[] cores, long mid, int n) {
        long cnt = 0;
        for (int c : cores) {
            cnt += mid / c + 1;
            if (cnt >= n) return true;
        }
        return cnt >= n;
    }
}
