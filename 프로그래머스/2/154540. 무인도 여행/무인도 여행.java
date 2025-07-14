import java.io.*;
import java.util.*;

class Solution {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static ArrayList<Integer> result = new ArrayList<>();
    static int row, col;
    
    public int[] solution(String[] maps) {
        row = maps.length;
        col = maps[0].length();
        
        visited = new boolean[row][col];
        result.clear();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    int count = bfs(i, j, maps);
                    result.add(count);
                }
            }
        }
        
        if (result.isEmpty()) {
            return new int[]{-1};
        }
        
        Collections.sort(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    static int bfs(int a, int b, String[] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b});
        visited[a][b] = true;
        
        int sum = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            sum += maps[x].charAt(y) - '0';
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx].charAt(ny) == 'X') continue;
                
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        
        return sum;
    }
}
