import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int idx = 0;
        
        for (String[] place : places) {
            int result = checkPlace(place);
            answer[idx++] = result;
        }
        
        return answer;
    }
    
    static int checkPlace(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!bfs(i, j, place)) return 0;
                }
            }
        }
        
        return 1;
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
            int d = cur[2];
            
            if (d > 2) continue;
            if (!(x == a && y == b) && place[x].charAt(y) == 'P') return false;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (checkRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (place[nx].charAt(ny) == 'X') continue;
                
                q.add(new int[]{nx, ny, d + 1});
                visited[nx][ny] = true;
            }
        }
        
        return true;
    }
    
    static boolean checkRange(int x, int y) {
        return x < 0 || y < 0 || x >= 5 || y >= 5;
    }
}

/*
조건
- 5 * 5 대기실
- 맨해튼 거리가 2 이하가 되도록 앉으면 안된다.
- 파티션은 허용. => 근데 어차피 파티션 있으면 거리가 2이상일 수 밖에 없음.
- P 응시자 X 파티션

요구
- 가능한 배치인지 아닌지 확인

풀이
- bfs로 탐색
*/