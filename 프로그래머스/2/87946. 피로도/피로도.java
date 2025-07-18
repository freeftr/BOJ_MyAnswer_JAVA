class Solution {
    
    static boolean[] visited;
    static int result = 0;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, 0, dungeons);
        return result;
    }
    
    static void dfs(int health, int cnt, int[][] dungeons) {
        result = Math.max(result, cnt);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && health >= dungeons[i][0]) {
                visited[i] = true;
                dfs(health - dungeons[i][1], cnt + 1, dungeons);
                visited[i] = false;
            }
        }
    }
}
