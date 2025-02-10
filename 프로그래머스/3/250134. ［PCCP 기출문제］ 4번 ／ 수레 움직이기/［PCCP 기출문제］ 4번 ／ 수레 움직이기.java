import java.io.*;
import java.util.*;

class Solution {
    
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    static int result = Integer.MAX_VALUE;
    static int rsx, rsy, bsx, bsy;
    static int rex, rey, bex, bey;
    static int n, m;
    static boolean[][] redVisited;
    static boolean[][] blueVisited;
    static int[][] board;
    
    public int solution(int[][] maze) {
        int answer = 0;
        n = maze.length;
        m = maze[0].length;
        
        board = new int[n][m];
        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = maze[i][j];
                if (maze[i][j] == 1) {
                    rsx = i;
                    rsy = j;
                    redVisited[i][j] = true;
                } else if (maze[i][j] == 2) {
                    bsx = i;
                    bsy = j;
                    blueVisited[i][j] = true;
                } else if (maze[i][j] == 3) {
                    rex = i;
                    rey = j;
                } else if (maze[i][j] == 4) {
                    bex = i;
                    bey = j;
                }
            }
        }
        
        dfs (rsx, rsy, bsx, bsy, 0);
        
        if (result == Integer.MAX_VALUE) {
            result = 0;
        }
        
        return result;
    }
    
    // 각각 돌면서 상태를 저장 
    static void dfs (int rx, int ry, int bx, int by, int depth) {
        
        boolean red = false;
        boolean blue = false;
        
        if (rx == rex && ry == rey && bx == bex && by == bey) {
            result = Math.min(result, depth);
            return;
        }
        
        if (rx == rex && ry == rey) {
            red = true;
        }
        if (bx == bex && by == bey) {
            blue = true;
        }
        
        for (int i = 0; i < 4; i++) {
            int nrx = rx + dx[i];
            int nry = ry + dy[i];
            if (red) {
                    nrx = rx;
                    nry = ry;
                }
            
            if (nrx < 0 || nry < 0 ||  nrx >= n || nry >= m) continue;
            if (redVisited[nrx][nry] && !red) continue;
            if (board[nrx][nry] == 5) continue;
            
            for (int j = 0; j < 4; j++) {
                int nbx = bx + dx[j];
                int nby = by + dy[j];
                
                if (blue) {
                    nbx = bx;
                    nby = by;
                }
                
                if (nbx < 0 || nby < 0 ||  nbx >= n || nby >= m) continue;
                if (blueVisited[nbx][nby] && !blue) continue;
                if (board[nbx][nby] == 5) continue;
                if (nrx == nbx && nry == nby) continue;
                if (nrx == bx && nry == by && nbx == rx && nby == ry) continue;
                
                redVisited[nrx][nry] = true;
                blueVisited[nbx][nby] = true;
                
                dfs (nrx, nry, nbx, nby, depth + 1);
                
                redVisited[nrx][nry] = false;
                blueVisited[nbx][nby] = false;
            }
            
        }
    }
}