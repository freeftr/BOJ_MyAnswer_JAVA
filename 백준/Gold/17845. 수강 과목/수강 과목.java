import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] tasks = new int[K + 1][2];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int im = Integer.parseInt(st.nextToken());
            int ne = Integer.parseInt(st.nextToken());

            tasks[i][0] = im;
            tasks[i][1] = ne;
        }

        long[][] dp = new long[K + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (tasks[i][1] > j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - tasks[i][1]] + tasks[i][0]);
                }
            }
        }

//        for (int i = 0; i <= N; i+=10) {
//            System.out.printf("%4d", i);
//        }
//        System.out.println();
//        for (int i = 1; i <= K; i++) {
//            for (int j = 0; j <= N; j+=10) {
//                System.out.printf("%4d", dp[i][j]);
//            }
//            System.out.println();
//        }

        System.out.println(dp[K][N]);
    }
}

/*
조건
- 학점 잘 받고 중요한 과목 듣기.
- 공부시간 한계를 초과하지 않으면서 과목의 중요도 합이 최대가 되게.

풀이
- 최대가 되는 중요도 출력.

    0   10  20  30  40  50  60  70  80
0   0   0   0   0   0   0   0   0   0
1   0   0   0   0   650 650 650 650 650
2   0   0   0   0   650 650 700 700 700
3   0   0   0   0   650 650 700 700 760
 */
