import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) continue;
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < M; l++) {
                        if (i == k && j == l) continue;
                        if (board[k][l] != 0) continue;
                        board[i][j] = 1;
                        board[k][l] = 1;
                        answer = Math.max(answer, bfs());
                        board[i][j] = 0;
                        board[k][l] = 0;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static int bfs() {
        boolean[][] visited = new boolean[N][M];
        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 2) {
                    if (visited[i][j]) continue;
                    Queue<int[]> q = new ArrayDeque<>();

                    q.add(new int[]{i, j});
                    visited[i][j] = true;

                    int size = 0;
                    boolean empty = false;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];

                        size++;

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                            if (visited[nx][ny]) continue;

                            if (board[nx][ny] == 0) {
                                empty = true;
                            }

                            if (board[nx][ny] == 2) {
                                q.add(new int[]{nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }

                    if (empty) continue;

                    total += size;
                }
            }
        }

        return total;
    }
}

/*
조건
- 양 선수가 돌을 2개씩 둔다.
- 에워싸임 == 빈 칸과 인접해 있는 돌이 하나도 없다.
- 빈 칸이 있어도 두면 반대로 죽일 수 있음

요구
- 현재 판에서 돌 두개를 두어 죽일 수 있는 최대 갯수

풀이
- 상대 그룹에서 2개 인접하는 거 중 제일 큰거 찾으면 댐

 */