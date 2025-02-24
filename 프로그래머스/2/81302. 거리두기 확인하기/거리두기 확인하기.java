import java.io.*;
import java.util.*;

// 맨해튼 거리 2 이하가 안되게 배치, 사이에 파티션 있으면 인정
// P 응시자 자리
// O 빈 테이블
// X 파티션
// 대기실 5개, 5*5 크기
// 충족 시 1
// 아닐 시 0

class Solution {
    
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        int idx = 0;
        for (String[] place : places){
            boolean flag = true;
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (place[i].charAt(j)=='P'){
                        int a = bfs(place, i, j);
                        if (a == 0){
                            flag = false;
                            break;
                        }
                    }
                }
                if (!flag){
                    break;
                }
            }
            if (flag){
                answer[idx] = 1;
            }
            idx++;
        }
        
        return answer;
    }
    
    static int bfs(String[] place, int a, int b){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        
        q.add(new int[]{a,b});
        visited[a][b] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            int dist = Math.abs(a-x) + Math.abs(b-y);
            
            if (0<dist && dist<=2 && place[x].charAt(y)=='P'){
                return 0;
            }
            
            if (dist>2){
                continue;
            }
            
            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx<0 || ny<0 || nx>=5 || ny>=5) continue;
                if (visited[nx][ny]) continue;
                if (place[nx].charAt(ny)=='X') continue;
                
                visited[nx][ny] = true;
                q.add(new int[]{nx,ny});
            }
        }
        return 1;
    }
}