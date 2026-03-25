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

        int[][] arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] preSum = new long[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] + arr[i][j] - preSum[i - 1][j - 1];
            }
        }

//        for (int i = 1; i <= N; i++) {
//            System.out.println();
//            for (int j = 1; j <= M; j++) {
//                System.out.printf("%4d", preSum[i][j]);
//            }
//        }
//        System.out.println();

        long answer = -10000;

        for (int x1 = 1; x1 <= N; x1++) {
            for (int y1 = 1; y1 <= M; y1++) {
                for (int x2 = x1; x2 <= N; x2++) {
                    for (int y2 = y1; y2 <= M; y2++) {
                        long sum = preSum[x2][y2] - preSum[x1 - 1][y2] - preSum[x2][y1 - 1] + preSum[x1 - 1][y1 - 1];
                        answer = Math.max(answer, sum);
                        if (answer == 21) {
                            System.out.println(x1 + " " + y1 + " " + " " + x2 + " " + y2);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- N * M 행렬에 -10000 이상 10000 이하 정수를 쓴다.
- 그안 부분 행렬로 정수의 합을 구함

요구
- 최대가 되는 부분행렬 구하기

 */