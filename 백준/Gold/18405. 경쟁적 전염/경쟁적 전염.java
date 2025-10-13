import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[][] map;
    static HashMap<Integer, ArrayList<int[]>> points = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) continue;
                points.computeIfAbsent(map[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        while (S != 0) {
            for (int i = 1; i <= K; i++) {
                if (points.get(i) == null) continue;
                bfs(points.get(i), i);
            }
            S--;
        }

        System.out.println(map[X - 1][Y - 1]);
    }

    static void bfs(ArrayList<int[]> temp, int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> next = new ArrayList<>();

        for (int[] pos : temp) {
            q.add(pos);
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] != 0) continue;

                map[nx][ny] = idx;
                next.add(new int[]{nx, ny});
            }
        }

        points.put(idx, next);
    }
}

/*
조건
- N * N
- 번호가 낮은 바이러스부터 상하좌우로 증식.
- 이미 다른 바이러스 있으면 불가.

요구
- S초 후 X, Y에 존재하는 바이러스 종류 출력.
 */