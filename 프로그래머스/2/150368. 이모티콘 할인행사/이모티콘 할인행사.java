import java.util.*;

class Solution {
    static int[] dr = {10, 20, 30, 40};
    static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        if (a[0] == b[0]) return b[1] - a[1];
        return b[0] - a[0];
    });

    public int[] solution(int[][] users, int[] emoticons) {
        dfs(0, new int[emoticons.length], emoticons, users);
        return pq.poll();
    }
    
    static void dfs(int depth, int[] ratios, int[] emoticons, int[][] users) {
        if (depth == emoticons.length) {
            int p = 0;
            int am = 0;

            for (int[] user : users) {
                int disReq = user[0];
                int plusReq = user[1];
                
                int sum = 0;
                for (int i = 0; i < ratios.length; i++) {
                    if (ratios[i] < disReq) continue;
                    sum += emoticons[i] * (100 - ratios[i]) / 100;
                }
                if (sum >= plusReq) p++;
                else am += sum;
            }
            pq.add(new int[]{p, am});
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            ratios[depth] = dr[i];
            dfs(depth + 1, ratios, emoticons, users);
        }
    }
}
