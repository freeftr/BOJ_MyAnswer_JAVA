import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[1001];
        dp[1] = false;
        dp[2] = true;
        dp[3] = false;
        dp[4] = true;
        
        if (N <= 4) {
            System.out.println(dp[N] ? "SK" : "CY");
            return;
        }

        for (int i = 5; i <= N; i++) {
            dp[i] = false;
            
            int[] moves = {1, 3, 4};
            for (int k : moves) {
                if (i - k < 0) continue;
                if (i - k == 0) continue;
                if (!dp[i - k]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        System.out.println(dp[N] ? "SK" : "CY");
    }
}

/*
조건
- 번갈아 1, 3, 4개 돌을 가져감.
- 마지막돌 가져가면 지는거

요구
- 이기는 사람 구하기

풀이
1 -> CY
2 -> SK
3 -> CY
4 -> SK
5 -> SK
6 -> CY

 */