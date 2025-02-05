import java.util.*;

class Solution {
    
    static ArrayList<int[]> dolgis = new ArrayList<>();
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        int N = key.length;
        int M = lock.length;
        
        int[][] temp = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = lock[i][j];
            }
        }
        
        boolean flag = false;
        for (int i = -N + 1; i < M; i++) { 
            for (int j = -N + 1; j < M; j++) { 
                for (int x = 0; x < 4; x++) { 
                    if (canUnlock(lock, key, i, j, N, M)) {
                        flag = true;
                        break;
                    }
                    rotate(key); 
                }
            }
            if(flag) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    static boolean canUnlock(int[][] lock, int[][] key, int i, int j, int N, int M) {
        int[][] temp = new int[M][M];

        for (int a = 0; a < M; a++) {
            for (int b = 0; b < M; b++) {
                temp[a][b] = lock[a][b]; 
            }
        }

        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                int x = i + a;
                int y = j + b;
                if (x >= 0 && x < M && y >= 0 && y < M) { 
                    temp[x][y] += key[a][b];
                }
            }
        }

        return check(temp, M);
    }

    static boolean check(int[][] map, int M) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void rotate(int[][] map) {
        int N = map.length;
        int[][] rotated = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[j][N - i - 1] = map[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            System.arraycopy(rotated[i], 0, map[i], 0, N);
        }
    }
}
