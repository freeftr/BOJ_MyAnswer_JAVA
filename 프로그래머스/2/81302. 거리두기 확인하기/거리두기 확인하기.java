import java.util.*;

class Solution {
    
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int idx = 0;
        for (String[] place : places) {
            boolean flag = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        if (!bfs(i, j, place)) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
            
            if (!flag) {
                answer[idx] = 1;
            }
            
            idx++;
        }
        return answer;
    }
    
    static boolean bfs(int a, int b, String[] place) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        
        q.add(new int[]{a, b, 0});
        visited[a][b] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if (dist == 2) break;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (place[nx].charAt(ny) == 'X') continue;
                if (visited[nx][ny]) continue;
                
                if (place[nx].charAt(ny) == 'P' && (dist == 0 || dist == 1)) {
                    return false;
                }
                
                q.add(new int[]{nx, ny, dist + 1});
            }
        }
        
        return true;
    }
}