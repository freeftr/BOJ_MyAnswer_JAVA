import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][2];

        String left = br.readLine();
        String right = br.readLine();

        for (int i = 0; i < N; i++) {
            board[i][0] = left.charAt(i) - '0';
            board[i][1] = right.charAt(i) - '0';
        }

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[2][N];

        // (줄, 위치, 시간)
        q.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int line = cur[0];
            int idx = cur[1];
            int time = cur[2];

            int[] nextIdx = {idx + 1, idx - 1, idx + K};
            int[] nextLine = {line, line, 1 - line};

            for (int i = 0; i < 3; i++) {
                int ni = nextIdx[i];
                int nl = nextLine[i];

                if (ni >= N) return true;

                if (ni < 0 || ni <= time) continue;

                if (board[ni][nl] == 0) continue;

                if (visited[nl][ni]) continue;

                visited[nl][ni] = true;
                q.add(new int[]{nl, ni, time + 1});
            }
        }

        return false;
    }
}