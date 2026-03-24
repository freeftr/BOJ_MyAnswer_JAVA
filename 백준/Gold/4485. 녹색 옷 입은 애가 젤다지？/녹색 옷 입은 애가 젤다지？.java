import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            int[][] board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            dist[0][0] = board[0][0];

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            pq.add(new int[]{0, 0, board[0][0]});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int x = cur[0];
                int y = cur[1];
                int d = cur[2];

                if (dist[x][y] < d) continue;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    if (dist[nx][ny] > dist[x][y] + board[nx][ny]) {
                        dist[nx][ny] = dist[x][y] + board[nx][ny];
                        pq.add(new int[]{nx, ny, dist[nx][ny]});
                    }
                }
            }

            sb.append("Problem " + idx + ": " + dist[N - 1][N - 1]).append("\n");
            idx++;
        }

        System.out.println(sb);
    }
}

/*
조건
- N * N
- 도둑푸피의 크기만큼 소지금을 잃음

요구
- 최소 손해 구하ㅣ기

풀이
- 다익스트라
 */