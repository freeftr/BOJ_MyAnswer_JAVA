import java.util.*;

class Solution {
    
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        int idx = 0;
        for (String[] place : places) {
            boolean flag = true;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        if (!bfs(i, j, place)) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (!flag) break;
            }
            
            answer[idx++] = flag ? 1 : 0;
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
            
            // 거리 2까지만.
            if (dist > 2) continue;
            
            // 맨해튼 거리 2이하일때 만나면 미준수.
            // 파티션 끼는 경우는 어차피 2 초과.
            if (place[x].charAt(y) == 'P' && dist != 0) {
                return false;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                if (place[nx].charAt(ny) == 'X') continue;
                
                q.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }
        
        return true;
    }
}

/*
조건
- 5 * 5 맵
- 맨해튼 거리 2 이하 금지
- 사이 파티션 있을 경우 오케이.
- o 빈테이블
- x 파티션
- P 응시자

요구
- 지키면 1
- 아니면 0
*/