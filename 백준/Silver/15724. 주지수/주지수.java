import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] preSum = new long[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] - preSum[i - 1][j - 1] + map[i - 1][j - 1];
            }
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            long result = preSum[x2][y2] - preSum[x2][y1 - 1] - preSum[x1 - 1][y2] + preSum[x1 - 1][y1 - 1];
            sb.append(result + "\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- 1 x 1 왕국 여러개 묶어서 하나의 주지수를 만듬.
- 1 based
 */