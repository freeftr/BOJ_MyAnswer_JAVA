import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int N = 8;
    static int sx = 7, sy = 0;

    static int[] dx = {0, 0, 1, -1, -1, 1, 1, -1, 0};
    static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1, 0};

    static ArrayList<char[][]> boards = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boards.add(copy(board));

        // 최대 8번만 만들면 충분 (8초 후엔 벽 전부 사라짐)
        for (int t = 0; t < 8; t++) {
            char[][] next = wallDown(boards.get(t));
            boards.add(next);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[boards.size()][N][N];

        q.add(new int[]{sx, sy, 0});
        visited[0][sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], time = cur[2];

            char[][] state = boards.get(time);
            if (state[x][y] == '#') continue;

            if (x == 0 && y == 7) return 1;

            int nextTime = Math.min(time + 1, boards.size() - 1);
            char[][] nextState = boards.get(nextTime);

            for (int i = 0; i < 9; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nextTime][nx][ny]) continue;

                // 현재 시점에서 벽이면 못 감
                if (state[nx][ny] == '#') continue;
                // 다음 시점(벽 내려온 뒤)에 그 칸이 벽이 되면 죽는 칸 → 못 감
                if (nextState[nx][ny] == '#') continue;

                visited[nextTime][nx][ny] = true;
                q.add(new int[]{nx, ny, nextTime});
            }
        }

        return 0;
    }

    static char[][] wallDown(char[][] prev) {
        char[][] next = new char[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(next[i], '.');

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (prev[i][j] == '#') {
                    if (i + 1 < N) next[i + 1][j] = '#';
                }
            }
        }
        return next;
    }

    static char[][] copy(char[][] src) {
        char[][] dst = new char[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, N);
        }
        return dst;
    }
}
