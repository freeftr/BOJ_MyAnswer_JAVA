class Solution {
    static int maxDiff = -1;
    static int[] bestShot;

    public int[] solution(int n, int[] info) {
        bestShot = new int[11];
        dfs(0, 0, new int[11], n, info);
        return maxDiff <= 0 ? new int[]{-1} : bestShot;
    }

    static void dfs(int depth, int used, int[] shots, int n, int[] info){
        if (depth == 11) {
            if (used > n) return;

            int remain = n - used;
            shots[10] = remain;

            int diff = check(shots, info);
            if (diff > maxDiff) {
                maxDiff = diff;
                bestShot = shots.clone();
            } else if (diff == maxDiff) {
                for (int i = 10; i >= 0; i--) {
                    if (shots[i] > bestShot[i]) {
                        bestShot = shots.clone();
                        break;
                    } else if (shots[i] < bestShot[i]) break;
                }
            }

            shots[10] = 0;
            return;
        }

        if (used + info[depth] + 1 <= n) {
            shots[depth] = info[depth] + 1;
            dfs(depth + 1, used + shots[depth], shots, n, info);
            shots[depth] = 0;
        }

        dfs(depth + 1, used, shots, n, info);
    }

    static int check(int[] ryan, int[] apeach) {
        int r = 0, a = 0;
        for (int i = 0; i <= 10; i++) {
            if (ryan[i] == 0 && apeach[i] == 0) continue;
            if (ryan[i] > apeach[i]) r += 10 - i;
            else a += 10 - i;
        }
        return r > a ? r - a : -1;
    }
}
