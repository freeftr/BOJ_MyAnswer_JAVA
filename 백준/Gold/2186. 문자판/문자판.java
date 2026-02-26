import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static char[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][][] dp;
    static int len;
    static char[] target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        target = br.readLine().toCharArray();
        len = target.length;

        dp = new int[N][M][len];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == target[0]) {
                    answer += dfs(i, j, 0);
                }
            }
        }

        System.out.println(answer);
    }

    static int dfs(int x, int y, int idx) {
        if (idx == len - 1) return 1;

        if (dp[x][y][idx] != -1) {
            return dp[x][y][idx];
        }

        int cnt = 0;

        for (int d = 1; d <= K; d++) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i] * d;
                int ny = y + dy[i] * d;

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (board[nx][ny] == target[idx + 1]) {
                    cnt += dfs(nx, ny, idx + 1);
                }
            }
        }

        return dp[x][y][idx] = cnt;
    }
}

/*
조건
- 대문자 적혀있는 N * M 문자판
- 상하좌우 K칸 이동 가능
- 재방문 가능

요구
- 주어진 영단어를 만들 수 있는 경로 몇 개인지?

풀이
- dp
- dp인데
BREAK면 BR은 B까지의 경우의 수 + B에서 R 가는 경우의 수 이런식으로 이어나감
 */