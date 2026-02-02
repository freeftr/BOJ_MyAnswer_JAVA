import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] dist1 = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist1[i], Integer.MAX_VALUE);
            dist1[i][i] = 0;

        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist1[b][a] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (dist1[i][k] == Integer.MAX_VALUE || dist1[k][j] == Integer.MAX_VALUE) continue;

                    dist1[i][j] = Math.min(dist1[i][k] + dist1[k][j], dist1[i][j]);
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int heavier = 0;
            int lighter = 0;

            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                if (dist1[i][j] != Integer.MAX_VALUE) heavier++;
                if (dist1[j][i] != Integer.MAX_VALUE) lighter++;
            }

            if (heavier > N / 2 || lighter > N / 2) {
                answer++;
            }
        }

        System.out.println(answer);

    }
}
