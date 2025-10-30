import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int R, C, sx, sy, ex, ey;
    static HashSet<Integer> set = new HashSet<>();
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        char block = '0';
        int a = -1;
        int b = -1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != '.') continue;
                boolean left = checkRail(i, j - 1, 1);
                boolean right = checkRail(i, j + 1, 0);
                boolean down = checkRail(i + 1, j, 2);
                boolean up = checkRail(i - 1, j, 3);

                if (left && right && up && down) {
                    block = '+';
                } else if (left && down) {
                    block = '4';
                } else if (left && up) {
                    block = '3';
                } else if (right && up) {
                    block = '2';
                } else if (right && down) {
                    block = '1';
                } else if (left && right) {
                    block = '-';
                } else if (up && down) {
                    block = '|';
                }

                a = i + 1;
                b = j + 1;

                if (block != '0') break;
            }

            if (block != '0') break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(a + " " + b + " " + block);

        System.out.println(sb.toString());
    }

    static boolean checkRail(int nx, int ny, int d) {
        if (outRange(nx, ny)) return false;

        // 오른쪽 열려 있는지 확인.
        if (d == 0) {
            if (map[nx][ny] == '+') return true;
            if (map[nx][ny] == '4') return true;
            if (map[nx][ny] == '-') return true;
            if (map[nx][ny] == '3') return true;
        }

        // 왼쪽 열려 있는지 확인
        if (d == 1) {
            if (map[nx][ny] == '-') return true;
            if (map[nx][ny] == '1') return true;
            if (map[nx][ny] == '2') return true;
            if (map[nx][ny] == '+') return true;
        }

        // 아래 열려 있는지 확인
        if (d == 2) {
            if (map[nx][ny] == '|') return true;
            if (map[nx][ny] == '+') return true;
            if (map[nx][ny] == '2') return true;
            if (map[nx][ny] == '3') return true;
        }

        // 위 열려 있는지 확인
        if (d == 3) {
            if (map[nx][ny] == '|') return true;
            if (map[nx][ny] == '+') return true;
            if (map[nx][ny] == '1') return true;
            if (map[nx][ny] == '4') return true;
        }

        return false;
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C;
    }
}