import java.io.*;
import java.util.*;

public class Main {
    static int N, M, G, R;
    static int[][] garden;
    static ArrayList<int[]> fertile = new ArrayList<>();
    static ArrayList<ArrayList<int[]>> fertiles = new ArrayList<>();
    static ArrayList<char[]> divided = new ArrayList<>();

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        garden = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
                if (garden[i][j] == 2) {
                    fertile.add(new int[]{i, j});
                }
            }
        }

        makeFertile(0, 0, new ArrayList<>());
        divide(0, 0, new char[G + R]);

        int answer = combine();
        System.out.println(answer);
    }

    static void makeFertile(int idx, int depth, ArrayList<int[]> cur) {
        if (depth == G + R) {
            fertiles.add(new ArrayList<>(cur));
            return;
        }

        for (int i = idx; i < fertile.size(); i++) {
            cur.add(fertile.get(i));
            makeFertile(i + 1, depth + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }

    static void divide(int idx, int gCnt, char[] cur) {
        if (gCnt > G) return;

        if (idx == G + R) {
            if (gCnt == G) {
                divided.add(cur.clone());
            }
            return;
        }

        cur[idx] = 'G';
        divide(idx + 1, gCnt + 1, cur);

        cur[idx] = 'R';
        divide(idx + 1, gCnt, cur);
    }

    static int combine() {
        int result = 0;

        for (ArrayList<int[]> f : fertiles) {
            for (char[] d : divided) {

                ArrayList<int[]> green = new ArrayList<>();
                ArrayList<int[]> red = new ArrayList<>();

                for (int i = 0; i < G + R; i++) {
                    int x = f.get(i)[0];
                    int y = f.get(i)[1];

                    if (d[i] == 'G') green.add(new int[]{x, y});
                    else red.add(new int[]{x, y});
                }

                result = Math.max(result, bfs(green, red));
            }
        }

        return result;
    }

    static int bfs(ArrayList<int[]> green, ArrayList<int[]> red) {
        int[][] time = new int[N][M];
        int[][] color = new int[N][M];
        boolean[][] flower = new boolean[N][M];

        Queue<int[]> q = new ArrayDeque<>();

        for (int[] g : green) {
            int x = g[0], y = g[1];
            q.add(new int[]{x, y, 1, 0});
            color[x][y] = 1;
        }

        for (int[] r : red) {
            int x = r[0], y = r[1];
            q.add(new int[]{x, y, 2, 0});
            color[x][y] = 2;
        }

        int cnt = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];
            int t = cur[3];

            if (flower[x][y] || color[x][y] == 3) continue;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (garden[nx][ny] == 0) continue;

                if (flower[nx][ny] || color[nx][ny] == 3) continue;

                if (color[nx][ny] == 0) {
                    color[nx][ny] = c;
                    time[nx][ny] = t + 1;
                    q.add(new int[]{nx, ny, c, t + 1});
                }
                else if (color[nx][ny] != c && time[nx][ny] == t + 1) {
                    flower[nx][ny] = true;
                    color[nx][ny] = 3;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}