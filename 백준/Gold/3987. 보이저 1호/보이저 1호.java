import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, sx, sy;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        int bestDir = 0;
        int bestTime = -1;
        boolean madeInfinite = false;

        for (int dir = 0; dir < 4; dir++) {
            Result r = simulate(sx, sy, dir);
            if (r.infinite) {
                bestDir = dir;
                madeInfinite = true;
                break;
            } else {
                if (r.time > bestTime) {
                    bestTime = r.time;
                    bestDir = dir;
                }
            }
        }

        System.out.println(dirChar(bestDir));
        if (madeInfinite) {
            System.out.println("Voyager");
        } else {
            System.out.println(bestTime);
        }
    }

    static class Result {
        boolean infinite;
        int time;
        Result(boolean infinite, int time) {
            this.infinite = infinite;
            this.time = time;
        }
    }

    static Result simulate(int x, int y, int dir) {
        boolean[][][] visited = new boolean[N][M][4];
        int time = 0;
        int cx = x, cy = y, cd = dir;

        while (true) {
            if (map[cx][cy] == 'C') {
                return new Result(false, time);
            }

            if (map[cx][cy] == '/') {
                if (cd == 0) cd = 1;
                else if (cd == 1) cd = 0;
                else if (cd == 2) cd = 3;
                else cd = 2;
            } else if (map[cx][cy] == '\\') {
                if (cd == 0) cd = 3;
                else if (cd == 3) cd = 0;
                else if (cd == 2) cd = 1;
                else cd = 2;
            }

            if (visited[cx][cy][cd]) {
                return new Result(true, time);
            }
            visited[cx][cy][cd] = true;

            int nx = cx + dx[cd];
            int ny = cy + dy[cd];

            if (outOfRange(nx, ny)) {
                return new Result(false, time + 1);
            }

            cx = nx;
            cy = ny;
            time++;
        }
    }

    static boolean outOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    static char dirChar(int d) {
        if (d == 0) return 'U';
        if (d == 1) return 'R';
        if (d == 2) return 'D';
        return 'L';
    }
}
