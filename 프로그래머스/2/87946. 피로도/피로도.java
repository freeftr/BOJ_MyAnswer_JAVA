class Solution {
    static int result = 0;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        dfs(0, k, dungeons, new boolean[dungeons.length]);
        return result;
    }
    
    static void dfs (int depth, int k, int[][] dungeons, boolean[] visited) {
        result = Math.max(result, depth);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            if (dungeons[i][0] > k) continue;
            k -= dungeons[i][1];
            visited[i] = true;
            dfs(depth + 1, k, dungeons, visited);
            k += dungeons[i][1];
            visited[i] = false;
            
        }
    }
}

/*
최소 필요 피로도 -> 탐험을 위해 필요한 피로도
소모 피로도 -> 소모되는 피로도
*/