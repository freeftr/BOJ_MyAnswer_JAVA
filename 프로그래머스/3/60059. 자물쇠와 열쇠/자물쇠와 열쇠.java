import java.util.*;

class Solution {
    static int M, N;
    static int[][] map;

    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = lock[i][j];
            }
        }

        for (int r = 0; r < 4; r++) {
            if (check(key)) return true;
            rotate();
        }
        return false;
    }

    static boolean check(int[][] key) {
        for (int offX = -(M - 1); offX <= N - 1; offX++) {
            for (int offY = -(M - 1); offY <= N - 1; offY++) {
                if (fits(offX, offY, key)) return true;
            }
        }
        return false;
    }

    static boolean fits(int offX, int offY, int[][] key) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                int sum = map[x][y];
                int i = x - offX, j = y - offY;
                if (0 <= i && i < M && 0 <= j && j < M) sum += key[i][j];
                if (sum != 1) return false;
            }
        }
        return true;
    }

    static void rotate() {
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[j][N - 1 - i] = map[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = rotated[i][j];
            }
        }
    }
}
