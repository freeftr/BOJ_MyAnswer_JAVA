import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int cover = 2*w + 1;
        int answer = 0;

        int leftLen = Math.max(0, stations[0] - w - 1);
        answer += (leftLen + cover - 1) / cover;

        for (int i = 0; i < stations.length - 1; i++) {
            int gapLen = stations[i + 1] - stations[i] - 2*w - 1;
            if (gapLen > 0) {
                answer += (gapLen + cover - 1) / cover;
            }
        }

        int rightLen = Math.max(0, n - (stations[stations.length - 1] + w));
        answer += (rightLen + cover - 1) / cover;

        return answer;
    }
}
