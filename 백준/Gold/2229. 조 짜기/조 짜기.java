import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            int curMax = arr[i];
            int curMin = arr[i];
            int best = 0;

            for (int j = i; j >= 0; j--) {
                if (arr[j] > curMax) curMax = arr[j];
                if (arr[j] < curMin) curMin = arr[j];
                int prev = (j > 0) ? dp[j - 1] : 0;
                int val = prev + (curMax - curMin);
                if (val > best) best = val;
            }
            dp[i] = best;
        }

        System.out.println(dp[N - 1]);
    }
}

/*
조건
- 조별 수업 함.
- 나이이차이많이나면 별로.
- 나이순 정렬 후 조짬.
- 잘짜여진 정도 = 가장 높은 학생 점수 - 낮은 학생 점수

풀이
- 잘 짜여진 정도의 최댓값 구하기.

0   1   2   3   4   5   6   7   8   9
2   5   7   1   3   4   8   6   9   3
0   3
 */