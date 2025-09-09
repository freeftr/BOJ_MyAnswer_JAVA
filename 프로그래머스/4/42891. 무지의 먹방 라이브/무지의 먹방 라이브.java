import java.util.*;

class Solution {
    static class Food {
        int time, idx;
        Food(int t, int i) { time = t; idx = i; }
    }

    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        long sum = 0;
        for (int t : food_times) sum += t;
        if (sum <= k) return -1;

        Food[] a = new Food[n];
        for (int i = 0; i < n; i++) a[i] = new Food(food_times[i], i + 1);
        Arrays.sort(a, Comparator.comparingInt(x -> x.time));

        long prev = 0;
        int i = 0;
        while (i < n) {
            long cur = a[i].time;
            long diff = cur - prev;
            if (diff > 0) {
                long spend = diff * (n - i);
                if (k >= spend) {
                    k -= spend;
                    prev = cur;
                } else {
                    int r = (int)(k % (n - i));
                    Food[] rest = Arrays.copyOfRange(a, i, n);
                    Arrays.sort(rest, Comparator.comparingInt(x -> x.idx));
                    return rest[r].idx;
                }
            }
            int j = i;
            while (j < n && a[j].time == cur) j++;
            i = j;
        }
        return -1;
    }
}
