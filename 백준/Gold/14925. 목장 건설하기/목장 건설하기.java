import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] preSum = new int[N + 1][M + 1];
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] != 0) continue;
//                preSum[i][j] = Math.min(preSum[i][j] + 1, preSum[i][j - 1] + 1);
//                preSum[i][j] = Math.min(preSum[i][j] + 1, preSum[i - 1][j] + 1);
//                preSum[i][j] = Math.min(preSum[i][j] + 1, preSum[i - 1][j - 1] + 1);

                preSum[i][j] = Math.min(preSum[i - 1][j], preSum[i][j - 1]);
                preSum[i][j] = Math.min(preSum[i][j], preSum[i - 1][j - 1]);

                preSum[i][j]++;

                answer = Math.max(answer, preSum[i][j]);
            }
        }

//        System.out.println();
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= M; j++) {
//                System.out.print(preSum[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(answer);
    }
}

/*
조건
- 랜드 씨는 땅사서 목장 지음.
- 못치우는 나무, 바위가 있음.

요구
- 랜드 씨의 땅에서 지을 수 있는 가장 큰 정사각형 목장 한변의 크기 구해라.

풀이
- 누적합으로 해야할 듯.

0   0   0   9   0   0
0   1   0   9   9   0
0   0   9   0   0   0
0   9   0   0   0   0
9   0   0   0   0   0
0   0   0   0   0   0

 */