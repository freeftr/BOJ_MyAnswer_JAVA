import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[] diceCheck;
    static boolean[][] visited;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static int[] dice = {0, 1, 2, 3, 4, 5, 6};
    static boolean ans = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 0; tc < 3; tc++) {
            map = new int[6][6];
            diceCheck = new boolean[7];
            visited = new boolean[6][6];
            ans = false;

            for (int i = 0; i < 6; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 6; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] == 1) {
                        dfs(i, j);
                        break;
                    }
                }
                if (ans) break;
            }

            System.out.println(ans ? "yes" : "no");
        }
    }

    public static void dfs(int x, int y) {
        if (check()) {
            ans = true;
            return;
        }

        for (int i = 1; i <= 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 6 || ny >= 6 || visited[nx][ny]) continue;
            if (map[nx][ny] == 0) continue;
            visited[nx][ny] = true;
            roll(i);
            diceCheck[dice[6]] = true;
            dfs(nx, ny);
            if (i == 1) {
                roll(2);
            }
            if (i == 2) {
                roll(1);
            }
            if (i == 3) {
                roll(4);
            }
            if (i == 4) {
                roll(3);
            }
        }
    }

    public static boolean check() {
        for (int i = 1; i <= 6; i++) {
            if (!diceCheck[i]) return false;
        }
        return true;
    }

    public static void roll(int dir) {
        int temp;
        if (dir == 1) {
            temp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        }

        if (dir == 2) {
            temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        }

        if (dir == 3) {
            temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }

        if (dir == 4) {
            temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }
    }
}