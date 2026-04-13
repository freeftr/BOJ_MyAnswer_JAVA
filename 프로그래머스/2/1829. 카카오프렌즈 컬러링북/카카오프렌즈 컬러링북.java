import java.util.*;

class Solution {
    /*
    1 1 1 0
    1 1 1 0
    0 0 0 1
    0 0 0 1
    0 0 0 1
    0 0 0 1
    */
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || picture[i][j] == 0) continue;
                int result = bfs(i, j, picture, n, m);
                answer[0]++;
                answer[1] = Math.max(result, answer[1]);
            }
        }
        
        return answer;
    }
    
    static int bfs(int a, int b, int[][] picture, int n, int m) {
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{a, b});
        visited[a][b] = true;
        int size = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            size++;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (picture[nx][ny] != picture[a][b]) continue;
                
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        
        return size;
    }
}