import java.util.*;

class Solution {
    static int N, M;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        ArrayList<Integer> result = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();
        visited = new boolean[N][M];
        
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (Character.isDigit(maps[i].charAt(j))) {
                    int size = bfs(i, j, maps);
                    flag = true;
                    result.add(size);
                }
            }
        }
        
        if (!flag) return new int[]{-1};
        
        Collections.sort(result);
        answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    static int bfs(int a, int b, String[] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{a, b});
        visited[a][b] = true;
        
        int result = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int c = maps[x].charAt(y) - '0';
            result += c;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (maps[nx].charAt(ny) == 'X') continue;
                if (visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        
        return result;
    }
}

/*
조건
- 숫자는 무인도
- 숫자는 머물 수 있는 날짜를 의미합

요구
- 각 섬에서 최대 며칠씨 머물 수 있는지 알아봄
*/