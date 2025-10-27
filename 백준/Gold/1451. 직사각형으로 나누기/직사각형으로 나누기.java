import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] preSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        preSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(s[j - 1]);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                preSum[i][j] = map[i][j]
                        + preSum[i][j - 1]
                        + preSum[i - 1][j]
                        - preSum[i - 1][j - 1];
            }
        }

        long answer = 0L;

        for (int a = 1; a <= M - 2; a++) {
            for (int b = a + 1; b <= M - 1; b++) {
                long s1 = sum(1, 1, N, a);
                long s2 = sum(1, a + 1, N, b);
                long s3 = sum(1, b + 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);
            }
        }

        for (int a = 1; a <= N - 2; a++) {
            for (int b = a + 1; b <= N - 1; b++) {
                long s1 = sum(1, 1, a, M);
                long s2 = sum(a + 1, 1, b, M);
                long s3 = sum(b + 1, 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);
            }
        }

        for (int v = 1; v <= M - 1; v++) {
            for (int h = 1; h <= N - 1; h++) {
                long s1 = sum(1, 1, N, v);
                long s2 = sum(1, v + 1, h, M);
                long s3 = sum(h + 1, v + 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);
            }
        }

        for (int v = 1; v <= M - 1; v++) {
            for (int h = 1; h <= N - 1; h++) {
                long s1 = sum(1, 1, h, v);
                long s2 = sum(h + 1, 1, N, v);
                long s3 = sum(1, v + 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);
            }
        }

        for (int h = 1; h <= N - 1; h++) {
            for (int v = 1; v <= M - 1; v++) {
                long s1 = sum(1, 1, h, M);
                long s2 = sum(h + 1, 1, N, v);
                long s3 = sum(h + 1, v + 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);
            }
        }

        for (int h = 1; h <= N - 1; h++) {
            for (int v = 1; v <= M - 1; v++) {
                long s1 = sum(1, 1, h, v);
                long s2 = sum(1, v + 1, h, M);
                long s3 = sum(h + 1, 1, N, M);
                answer = Math.max(answer, s1 * s2 * s3);
            }
        }

        System.out.println(answer);
    }

    static long sum(int x1, int y1, int x2, int y2) {
        return preSum[x2][y2]
                - preSum[x1 - 1][y2]
                - preSum[x2][y1 - 1]
                + preSum[x1 - 1][y1 - 1];
    }
}
