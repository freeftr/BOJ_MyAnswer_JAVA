import java.io.*;
import java.util.*;

class Solution {
    
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        answer = bfs(maps);
        
        return answer;
    }
    
    static int bfs(int[][] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        q.add(new int[]{0,0,0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if (x==n-1 && y==m-1) {
                return dist + 1;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx][ny] == 0) continue;
                
                q.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}