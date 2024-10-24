import java.io.*;
import java.util.*;

public class Main {

    static int N, R, G, B;
    static long[][][][] dp;
    static long[] factorial;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        factorial = new long[N+1];
        factorial[0] = 1; // 0! = 1

        dp = new long[N+1][R+1][G+1][B+1];

        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        long result = dfs(1, R, G, B);

        if (result == 0) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }

    static long dfs(int level, int r, int g, int b) {
        if (r < 0 || g < 0 || b < 0) return 0;
        if (level == N+1) return 1;
        if (dp[level][r][g][b] != 0) return dp[level][r][g][b];

        long count = 0;

        // case 1 : 1색만 사용하는 경우
        if (r >= level) count += dfs(level + 1, r - level, g, b);
        if (g >= level) count += dfs(level + 1, r, g - level, b);
        if (b >= level) count += dfs(level + 1, r, g, b - level);

        // case 2 : 2색 사용하는 경우
        if (level % 2 == 0) {
            if (r >= level / 2 && g >= level / 2) {
                count += dfs(level + 1, r - level / 2, g - level / 2, b)
                        * factorial[level] / (factorial[level / 2] * factorial[level / 2]);
            }
            if (r >= level / 2 && b >= level / 2) {
                count += dfs(level + 1, r - level / 2, g, b - level / 2)
                        * factorial[level] / (factorial[level / 2] * factorial[level / 2]);
            }
            if (g >= level / 2 && b >= level / 2) {
                count += dfs(level + 1, r, g - level / 2, b - level / 2)
                        * factorial[level] / (factorial[level / 2] * factorial[level / 2]);
            }
        }

        // case 3 : 3색 사용하는 경우
        if (level % 3 == 0) {
            if (r >= level / 3 && g >= level / 3 && b >= level / 3) {
                count += dfs(level + 1, r - level / 3, g - level / 3, b - level / 3)
                        * factorial[level] / (factorial[level / 3] * factorial[level / 3] * factorial[level / 3]);
            }
        }

        dp[level][r][g][b] = count;
        return count;
    }
}