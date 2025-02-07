import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void dfs(int x, int y, int cnt) {
        if (x == 10) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (y == 10) {
            dfs(x + 1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && check(x, y, i)) {
                    attach(x, y, i);
                    paper[i]--;
                    dfs(x, y + 1, cnt + 1);
                    paper[i]++;
                    detach(x, y, i);
                }
            }
        } else {
            dfs(x, y + 1, cnt);
        }
    }

    static boolean check(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[x + i][y + j] == 0) return false;
            }
        }
        return true;
    }

    static void attach(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x + i][y + j] = 0;
            }
        }
    }

    static void detach(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x + i][y + j] = 1;
            }
        }
    }
}
