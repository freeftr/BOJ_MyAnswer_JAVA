import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] office;
    static ArrayList<Cctv> cctvs = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[][][] cctvDirs = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    cctvs.add(new Cctv(i, j, office[i][j]));
                }
            }
        }

        dfs(0, new ArrayList<>());
        System.out.println(answer);
    }

    static void dfs(int depth, ArrayList<Integer> dirs) {
        if (depth == cctvs.size()) {
            answer = Math.min(answer, calculate(dirs));
            return;
        }

        Cctv cctv = cctvs.get(depth);
        for (int d = 0; d < cctvDirs[cctv.type].length; d++) {
            dirs.add(d);
            dfs(depth + 1, dirs);
            dirs.remove(dirs.size() - 1);
        }
    }

    static int calculate(ArrayList<Integer> dirs) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++)
            temp[i] = office[i].clone();

        for (int i = 0; i < cctvs.size(); i++) {
            Cctv cctv = cctvs.get(i);
            int dIndex = dirs.get(i);
            int[] directions = cctvDirs[cctv.type][dIndex];

            for (int dir : directions) {
                int nx = cctv.x;
                int ny = cctv.y;
                while (true) {
                    nx += dx[dir];
                    ny += dy[dir];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                    if (temp[nx][ny] == 6) break;
                    if (temp[nx][ny] == 0) temp[nx][ny] = -1;
                }
            }
        }

        int blind = 0;
        for (int[] row : temp) {
            for (int val : row) {
                if (val == 0) blind++;
            }
        }
        return blind;
    }

    static class Cctv {
        int x, y, type;

        public Cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
