import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public int solution(String[] board) {
        int answer = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    answer = bfs(board, i, j);
                    return answer;
                }
            }
        }
        return answer;
    }
    
    static int bfs(String[] board, int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        int N = board.length;
        int M = board[0].length();
        boolean[][] visited = new boolean[N][M];
        
        q.add(new int[]{a, b, 0});
        visited[a][b] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if (board[x].charAt(y) == 'G') return dist;
            
            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                
                while ((nx >= 0 && ny >= 0 && nx < N && ny < M) && board[nx].charAt(ny) != 'D') {
                    nx += dx[i];
                    ny += dy[i];
                }
                
                nx -= dx[i];
                ny -= dy[i];
                
                if (visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dist + 1});
            }
        }
        
        return -1;
    }
}

/*
조건
- 이동 시 미끄러져서 끝으로 감
*/