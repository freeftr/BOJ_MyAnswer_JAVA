import java.util.*;
class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public int solution(int[][] maps) {
        int answer = 0;
        
        int r = maps.length;
        int c = maps[0].length;
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[r][c];
        
        q.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            
            if (x == r - 1 && y == c - 1) return d;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx][ny] == 0) continue;
                
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, d + 1});
            }
        }
        
        return -1;
    }
}