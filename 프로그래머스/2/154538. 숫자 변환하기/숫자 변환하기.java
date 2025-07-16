import java.io.*;
import java.util.*;

class Solution {
    
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        int[] dx = {2, 3, n};
        boolean[] visited = new boolean[1000001];
        
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{x, 0});
        visited[x] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cnt = cur[1];
            
            if (cx == y) {
                answer = cnt;
                break;
            }
            
            for (int i = 0; i < 3; i++) {
                int nx = 0;
                if (i==0 || i==1) {
                    nx = cx * dx[i];
                } else {
                    nx = cx + dx[i];
                }
                
                if (nx < 1 || nx > 1000000) continue;
                if (visited[nx]) continue;
                
                q.add(new int[]{nx, cnt+1});
                visited[nx] = true;
            }
            
        }
        if (answer == 0) {
            answer = -1;
        }
        if (x == y) {
            answer = 0;
        }
        
        return answer;
    }
}