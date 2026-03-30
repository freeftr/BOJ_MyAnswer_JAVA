import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static ArrayList<int[]> viruses = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        dfs(0, new ArrayList<>(), 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void dfs(int depth, ArrayList<int[]> cur, int start) {
        if (depth == M) {
            int result = bfs(cur);
            if (result == -1) return;
            answer = Math.min(result, answer);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            cur.add(viruses.get(i));
            dfs(depth + 1, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }

    static int bfs(ArrayList<int[]> starts) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[N][N];

        for (int[] s : starts) {
            q.add(new int[]{s[0], s[1], 0});
            visited[s[0]][s[1]] = true;
        }

        int max = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];

            if (board[x][y] == 0) {
                max = Math.max(max, t);
            }

            if (max >= answer) return -1;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] == 1) continue;

                if (board[nx][ny] == 2) {
                    q.add(new int[]{nx, ny, t + 1});
                    visited[nx][ny] = true;
                }

                if (board[nx][ny] == 0) {
                    q.add(new int[]{nx, ny, t + 1});
                    visited[nx][ny] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0 && !visited[i][j]) return -1;
            }
        }

        return max;
    }
}

/*
조건
- 처음 바이러스는 비활성
- 활성상태는 상하좌우 모든 칸 동시 복제
- 1 : 벽, 2 : 바이러스

요구
- 모든 빈 칸에 바이러스 퍼뜨리는 최소 시간 구하기

풀이
-
 */