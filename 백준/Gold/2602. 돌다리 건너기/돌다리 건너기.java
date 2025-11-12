import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String order = br.readLine();
        String demon = br.readLine();
        String angel = br.readLine();

        int len = demon.length();
        int[][][] dp = new int[2][order.length() + 1][len + 1];

        // 빈 문자열을 만들 수 있는 경우는 1
        for (int j = 0; j <= len; j++) {
            dp[0][0][j] = 1;
            dp[1][0][j] = 1;
        }

        for (int i = 1; i <= order.length(); i++) {
            for (int j = 1; j <= len; j++) {
                // demon 다리
                dp[0][i][j] = dp[0][i][j - 1]; // 현재 다리만
                if (order.charAt(i - 1) == demon.charAt(j - 1))
                    dp[0][i][j] += dp[1][i - 1][j - 1]; // 천사 다리에서 넘어오는 경우

                // angel 다리
                dp[1][i][j] = dp[1][i][j - 1]; // 현재 다리만
                if (order.charAt(i - 1) == angel.charAt(j - 1))
                    dp[1][i][j] += dp[0][i - 1][j - 1]; // 악마 다리에서 넘어오는 경우
            }
        }

        System.out.println(dp[0][order.length()][len] + dp[1][order.length()][len]);
    }
}

/*
조건
- 각 다리를 건널대 밟아야 하는 순서가 있음.
- 악마와 천사 번갈아 가며 밟기

요구
- 통과하는 경우의 수 구하기

풀이
- dp
R   I   N   G   S   R
1   0   0   0   0   1

G   R   G   G   N   S
0   1   0   0   0   0

천사 G를 밟으려면 이전까지의 악마 R을 더해야 함.
 */
