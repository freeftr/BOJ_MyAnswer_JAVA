class Solution {
    static int bestDiff = -1;
    static int[] bestShots = new int[11];

    public int[] solution(int n, int[] info) {
        int[] shots = new int[11];
        dfs(n, 0, shots, info, 0);

        if (bestDiff <= 0) return new int[]{-1};
        return bestShots;
    }

    static void dfs(int n, int depth, int[] shots, int[] info, int used) {
        if (depth == 11) {
            if (used < n) {
                shots[10] += (n - used);
                calculate(shots, info);
                shots[10] -= (n - used);
            } else {
                calculate(shots, info);
            }
            return;
        }

        dfs(n, depth + 1, shots, info, used);

        int need = info[depth] + 1;
        if (n - used >= need) {
            shots[depth] = need;
            dfs(n, depth + 1, shots, info, used + need);
            shots[depth] = 0;
        }
    }

    static void calculate(int[] shots, int[] info) {
        int ryan = 0, apeach = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            if (shots[i] == 0 && info[i] == 0) continue;

            if (shots[i] > info[i]) ryan += score;
            else apeach += score;
        }

        if (ryan <= apeach) return;

        int diff = ryan - apeach;
        if (diff > bestDiff) {
            bestDiff = diff;
            bestShots = shots.clone();
        } else if (diff == bestDiff) {
            for (int i = 10; i >= 0; i--) {
                if (shots[i] != bestShots[i]) {
                    if (shots[i] > bestShots[i]) bestShots = shots.clone();
                    break;
                }
            }
        }
    }
}
