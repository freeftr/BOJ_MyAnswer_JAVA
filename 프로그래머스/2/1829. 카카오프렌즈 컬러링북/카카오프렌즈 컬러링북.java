import java.util.*;
import java.io.*;
class Solution {
    static int M, N;
    static int max = 0;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        M = m;
        N = n;
        
        visited = new boolean[M][N];
        
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0) continue;
                if (visited[i][j]) continue;
                int cnt = bfs(i, j, picture[i][j], picture);
                idx++;
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
            }
        }
        
        numberOfArea = idx;

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    static int bfs(int a, int b, int num, int[][] picture) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b});
        visited[a][b] = true;
    
        int result = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            result++;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (picture[nx][ny] != num) continue;
                
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            } 
        }
        return result;
    }
}

/*
요구
- 몇 개의 영역이 있는지와 가장 큰 영역의 넓이는 얼마인지?

1 1 1 0
1 2 2 0
1 0 0 1
0 0 0 1
0 0 0 3
0 0 0 3

1 1 1 0
1 1 1 0
0 0 0 1
0 0 0 1
0 0 0 1
0 0 0 1

*/