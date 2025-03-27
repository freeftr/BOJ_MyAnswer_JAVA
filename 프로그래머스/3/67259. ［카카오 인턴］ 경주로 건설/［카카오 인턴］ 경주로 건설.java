import java.io.*;
import java.util.*;

class Solution {
    
    static int N;
    static int[][][] cache;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    // 1은 벽
    // 0,0에서 출발 
    // 직선은 100원
    // 코너는 500원
    public int solution(int[][] board) {
        int answer = 0;
        
        N = board.length;
        
        cache = new int[N][N][4];
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                Arrays.fill(cache[i][j], Integer.MAX_VALUE);
            }
        }
        
        bfs(board);
        
        answer = Integer.MAX_VALUE;
        
        for (int i = 0; i < 4; i++){
            answer = Math.min(answer, cache[N-1][N-1][i]);
        }
        
        return answer;
    }
    
    static void bfs(int[][] board){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[3] - b[3]);
        for (int i = 0; i < 4; i++){
            pq.add(new int[]{0,0,i,0});
            cache[0][0][i] = 100;
        }
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int cost = cur[3];
            
            if (x==N-1 && y==N-1){
                return;
            }
            
            for (int i =0 ; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int newCost = cost;
                
                if (i!=dir){
                    newCost += 600;
                } else {
                    newCost += 100;
                }
                
                if (nx < 0 || ny < 0 || nx>=N || ny>=N) continue;
                if (board[nx][ny]==1) continue;
                
                if (newCost < cache[nx][ny][i]){
                    cache[nx][ny][i] = newCost;
                    pq.add(new int[]{nx,ny,i,newCost});
                }
            }
        }
    }
}