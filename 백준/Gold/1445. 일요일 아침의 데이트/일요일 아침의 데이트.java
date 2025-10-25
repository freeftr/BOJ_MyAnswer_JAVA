import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int sx = -1, sy = -1, fx = -1, fy = -1;
    static char[][] forest;
    static int[][] nearCnt;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        forest = new char[N][M];
        nearCnt = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                forest[i][j] = s.charAt(j);

                if (forest[i][j] == 'S') { sx = i; sy = j; }
                if (forest[i][j] == 'F') { fx = i; fy = j; }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (forest[i][j] == 'g') {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if (forest[nx][ny] == '.') {
                            nearCnt[nx][ny] = 1;
                        }
                    }
                }
            }
        }

        int[][] dist = new int[N][M];
        int[][] nDist = new int[N][M];

        // {x, y, gCnt, nCnt}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            return a[3] - b[3];
        });

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            Arrays.fill(nDist[i], Integer.MAX_VALUE);
        }

        dist[sx][sy] = 0;
        nDist[sx][sy] = 0;
        pq.add(new int[]{sx, sy, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], gCnt = cur[2], nCnt = cur[3];

            if (gCnt > dist[x][y] || (gCnt == dist[x][y] && nCnt > nDist[x][y])) continue;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                int addG = (forest[nx][ny] == 'g') ? 1 : 0;

                int addN = 0;
                if (forest[nx][ny] == '.' && nearCnt[nx][ny] == 1) addN = 1;

                int ng = gCnt + addG;
                int nn = nCnt + addN;

                if (ng < dist[nx][ny] || (ng == dist[nx][ny] && nn < nDist[nx][ny])) {
                    dist[nx][ny] = ng;
                    nDist[nx][ny] = nn;
                    pq.add(new int[]{nx, ny, ng, nn});
                }
            }
        }

        System.out.println(dist[fx][fy] + " " + nDist[fx][fy]);
    }
}
