import java.io.*;
import java.util.*;
class Solution {
    
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] visited;
    
    static int N, M;
    
    static int sx, sy;
    
    static int ans = 0;
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i].charAt(j)=='S'){
                    sx = i;
                    sy = j;
                    break;
                }
            }
        }
        bfs('L', maps);
        if(ans==-1){
            return -1;
        }
        bfs('E', maps);
        
        return ans;
    }
    
    static void bfs(char target, String[] maps){
        Queue<int[]> q = new ArrayDeque<>();
        visited = new boolean[N][M];
        
        q.add(new int[]{sx,sy,0});
        visited[sx][sy] = true;
        
        while(!q.isEmpty()){
            
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];
            int dist = arr[2];
            
            if(maps[x].charAt(y) == target){
                ans+=dist;
                sx = x;
                sy = y;
                return;
            }
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(visited[nx][ny]) continue;
                if(maps[nx].charAt(ny)=='X') continue;
                
                q.add(new int[]{nx,ny,dist+1});
                visited[nx][ny] = true;
            }
        }
        ans = -1;
        return;
    }
}