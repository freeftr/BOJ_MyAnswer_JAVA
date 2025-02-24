import java.io.*;
import java.util.*;

class Solution {
    
    static int N, M;
    static char[][] newStorage;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        N = storage.length;
        M = storage[0].length();
        newStorage = new char[N + 2][M + 2];
        
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                if (i == 0 || i == N + 1 || j == 0 || j == M + 1) {
                    newStorage[i][j] = '0'; 
                } else {
                    newStorage[i][j] = storage[i - 1].charAt(j - 1); 
                }
            }
        }
        
        for (String s : requests) {
            // print();
            if (s.length() == 1) {
                fork(s.charAt(0)); 
            } else {
                crane(s.charAt(0));
            }
            // System.out.println();
        }
        
        answer = count();
        return answer;
    }
    
    static void fork(char target) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 2][M + 2];
        
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            if (newStorage[x][y] == target) { 
                newStorage[x][y] = '0';
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N + 2 || ny >= M + 2) continue;
                if (visited[nx][ny]) continue;
                
                if (newStorage[nx][ny] == '0' || newStorage[nx][ny] == target) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }    
    
    static void crane(char target) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (target==newStorage[i][j]) { 
                    newStorage[i][j] = '0';
                }
            }
        }
    }
    
    static int count() {
        int cnt = 0;
        for (char[] a : newStorage) {
            for (char b : a) {
                if (b != '0') { 
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    static void print() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                System.out.print(newStorage[i][j] + " ");
            }
            System.out.println();
        }
    }
}
