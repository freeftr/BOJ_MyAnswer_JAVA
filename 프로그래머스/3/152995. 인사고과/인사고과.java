import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        int wanhoA = wanho[0];
        int wanhoB = wanho[1];

        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int maxPeer = 0;
        List<int[]> candidates = new ArrayList<>();

        for (int[] s : scores) {
            if (s[1] < maxPeer) {
                if (s[0] == wanhoA && s[1] == wanhoB) return -1;
                continue;
            } else {
                maxPeer = Math.max(maxPeer, s[1]);
                candidates.add(s);
            }
        }

        candidates.sort((a, b) -> (b[0] + b[1]) - (a[0] + a[1]));

        int rank = 1;
        int prevScore = -1;
        int sameRankCount = 0;

        for (int i = 0; i < candidates.size(); i++) {
            int sum = candidates.get(i)[0] + candidates.get(i)[1];

            if (sum != prevScore) {
                rank += sameRankCount;
                sameRankCount = 1;
                prevScore = sum;
            } else {
                sameRankCount++;
            }

            if (candidates.get(i)[0] == wanhoA && candidates.get(i)[1] == wanhoB) {
                return rank;
            }
        }

        return -1;
    }
}
