import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int MOD = 1_000_000;

        int[][][] dp = new int[N + 1][2][3];

        dp[0][0][0] = 1;

        for (int day = 1; day <= N; day++) {
            for (int late = 0; late < 2; late++) {
                for (int absent = 0; absent < 3; absent++) {
                    dp[day][late][0] = (dp[day][late][0] + dp[day - 1][late][absent]) % MOD;

                    if (late == 0) {
                        dp[day][1][0] = (dp[day][1][0] + dp[day - 1][0][absent]) % MOD;
                    }

                    if (absent < 2) {
                        dp[day][late][absent + 1] = (dp[day][late][absent + 1] + dp[day - 1][late][absent]) % MOD;
                    }
                }
            }
        }

        int answer = 0;
        for (int late = 0; late < 2; late++) {
            for (int absent = 0; absent < 3; absent++) {
                answer = (answer + dp[N][late][absent]) % MOD;
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- 개근상 줄것인지 말것인지.
- 출석, 지각, 결석
- 두번이상 지각 or 세번 연속 결석 시 개근상 못받음.

요구
- N일이 주어졋을 때 개근상을 받을 수 있는 출결 정보 개수 구하기.

풀이
- dp
 */