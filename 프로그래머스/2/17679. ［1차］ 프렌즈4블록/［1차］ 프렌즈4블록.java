import java.io.*;
import java.util.*;

class Solution {

    static boolean[][] destroyList; 
    static char[][] map; 
    static int M; 
    static int N; 

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        destroyList = new boolean[m][n];
        map = new char[m][n];
        M = m;
        N = n;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        while (true) {
            int removedBlocks = destroy(); 
            if (removedBlocks == 0) { 
                break;
            }
            down();
            answer += removedBlocks; 
        }

        return answer;
    }

    static int destroy() {
        int cnt = 0;

        for (int i = 0; i < M - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] != '0' && 
                    map[i][j] == map[i + 1][j] &&
                    map[i][j] == map[i][j + 1] &&
                    map[i][j] == map[i + 1][j + 1]) {
                    destroyList[i][j] = true;
                    destroyList[i + 1][j] = true;
                    destroyList[i][j + 1] = true;
                    destroyList[i + 1][j + 1] = true;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (destroyList[i][j]) {
                    map[i][j] = '0'; 
                    destroyList[i][j] = false; 
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void down() {
        for (int j = 0; j < N; j++) { 
            for (int i = M - 1; i >= 0; i--) { 
                if (map[i][j] == '0') { 
                    int idx = i;
                    while (idx > 0) {
                        if (map[idx - 1][j] != '0') {
                            map[i][j] = map[idx - 1][j]; 
                            map[idx - 1][j] = '0'; 
                            break;
                        }
                        idx--;
                    }
                }
            }
        }
    }
}
