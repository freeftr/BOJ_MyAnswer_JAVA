import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        Arrays.fill(dp, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        if (arr[0] == 0) {
            System.out.println(-1);
            return;
        }

        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            for (int j = 1; j <= arr[i]; j++) {
                int next = i + j;

                if (next >= N) next = N - 1;

                dp[next] = Math.min(dp[next], dp[i] + 1);
            }
        }

        System.out.println(dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]);
    }
}

/*
조건
- Ai칸에 적혀 있는 숫자 이하만큼 오른쪽으로 떨어진 칸으로 한 번에 점프 가능.

요구
- 맨 왼쪽에서 오른쪽 끝까지 최소 몇번 점프를 해야하는지.

풀이
- dp
 */