import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        int[][] J = new int[N + 1][M + 1];
        int[][] O = new int[N + 1][M + 1];
        int[][] I = new int[N + 1][M + 1];
        int[][] JSum = new int[N + 1][M + 1];
        int[][] OSum = new int[N + 1][M + 1];
        int[][] ISum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 1; j <= M; j++) {
                if (s[j - 1].equals("J")) J[i][j]++;
                if (s[j - 1].equals("O")) O[i][j]++;
                if (s[j - 1].equals("I")) I[i][j]++;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                JSum[i][j] = JSum[i - 1][j] + JSum[i][j - 1] - JSum[i - 1][j - 1] + J[i][j];
                ISum[i][j] = ISum[i - 1][j] + ISum[i][j - 1] - ISum[i - 1][j - 1] + I[i][j];
                OSum[i][j] = OSum[i - 1][j] + OSum[i][j - 1] - OSum[i - 1][j - 1] + O[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int JResult = JSum[c][d] - JSum[a - 1][d] - JSum[c][b - 1] + JSum[a - 1][b - 1];
            int OResult = OSum[c][d] - OSum[a - 1][d] - OSum[c][b - 1] + OSum[a - 1][b - 1];
            int IResult = ISum[c][d] - ISum[a - 1][d] - ISum[c][b - 1] + ISum[a - 1][b - 1];

            sb.append(JResult + " " + OResult + " " + IResult + "\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- N * M
- 정글 J, 바다 O, 얼음 I

요구
- 각 영역에 정글 바다 얼음이 몇개씩 있는지 세기
 */