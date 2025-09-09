import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] cnt = new int[N + 2];
        int[] preSum = new int[N + 2];

        for (int stage : stages) {
            if (stage >= 1 && stage <= N + 1) cnt[stage]++;
        }

        preSum[N + 1] = cnt[N + 1];
        for (int i = N; i >= 1; i--) {
            preSum[i] = preSum[i + 1] + cnt[i];
        }

        double[] fail = new double[N + 2];
        for (int i = 1; i <= N; i++) {
            fail[i] = preSum[i] == 0 ? 0.0 : (double) cnt[i] / preSum[i];
        }

        Integer[] order = new Integer[N];
        for (int i = 0; i < N; i++) order[i] = i + 1;

        Arrays.sort(order, (a, b) -> {
            if (fail[b] == fail[a]) return Integer.compare(a, b);
            return Double.compare(fail[b], fail[a]);
        });

        for (int i = 0; i < N; i++) answer[i] = order[i];
        return answer;
    }
}
