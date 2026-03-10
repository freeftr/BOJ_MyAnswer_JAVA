import java.util.*;

class Solution {
    static char[][] board;
    static int n, m;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();
        
        board = new char[n + 2][m + 2];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                board[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
        
        for (String req : requests) {
            if (req.length() == 1) {
                char target = req.charAt(0);
                Queue<int[]> q = new ArrayDeque<>();
                boolean[][] visited = new boolean[n + 2][m + 2];
                
                for (int i = 0; i <= n + 1; i++) {
                    q.add(new int[]{i, 0});
                    q.add(new int[]{i, m + 1});
                    visited[i][0] = true;
                    visited[i][m + 1] = true;
                }
                
                for (int j = 0; j <= m + 1; j++) {
                    q.add(new int[]{0, j});
                    q.add(new int[]{n + 1, j});
                    visited[0][j] = true;
                    visited[n + 1][j] = true;
                }
                
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        
                        if (nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2) continue;
                        if (visited[nx][ny]) continue;
                        
                        if (board[nx][ny] == '\0') {
                            q.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                        
                        if (board[nx][ny] == target) {
                            board[nx][ny] = '\0';
                            visited[nx][ny] = true;
                        }
                    }
                }
            } else {
                char target = req.charAt(0);
                
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (board[i][j] == target) board[i][j] = '\0';
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] != '\0') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}