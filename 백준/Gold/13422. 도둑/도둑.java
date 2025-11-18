import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] houses = new int[N + 1];
            long[] preSum = new long[2 * N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                houses[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N * 2; i++) {
                preSum[i] = preSum[i - 1] + (i % N == 0 ? houses[N] : houses[i % N]);
            }
            
            if (N == M) {
                int answer = preSum[M] < K ? 1 : 0;
                sb.append(answer + "\n");
                continue;
            }

            // i~j 까지 합 == preSum[j] - preSum[i - 1]

            int left = 1;
            int right = M;
            int answer = 0;

            while (left <= N && right < N * 2) {
                long sum = preSum[right] - preSum[left - 1];
                if (sum < K) {
                    answer++;
//                    System.out.println("M:" + M + " sum:" + sum + " left:" + left+ " right:" + right + " pL:" + preSum[left - 1]+ " pR:" + preSum[right]);

                    left++;
                    right++;
                } else {
                    left++;
                    right++;
                }
            }

            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- 원형으로 N개의 집이 있음.
- 각자 자신의 집에 돈을 보관.
- 도둑이 M개의 연속된 집에서 돈을 훔친다.
- K원 이상 훔치면 안댐.

요구
- K원 밑으로 M개의 연속된 집에서 훔칠 수 잇는 경우의 수.

풀이
- 2*N 길이의 누적합을 구함. => 원형 계산 편하려고.
- 슬라이딩 윈도우로 계산때려.

3   4   7   5   6   4   2   9   3   4   7   5   6   4   2   9
3   7   14  19  25  29  31  40  43  47  54  59  65  69  71  80
 */