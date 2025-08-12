class Solution {
    static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        if ((n & 1) == 1) return 0; // 홀수면 못 채움

        long[] dp = new long[n + 1];
        dp[0] = 1;
        if (n >= 2) dp[2] = 3;
        if (n >= 4) dp[4] = 11;

        if (n == 0) return 1;
        if (n == 2) return 3;
        if (n == 4) return 11;

        for (int i = 6; i <= n; i += 2) {
            long val = (4L * dp[i - 2]) % MOD;
            val = (val - dp[i - 4] + MOD) % MOD;
            dp[i] = val;
        }
        return (int) (dp[n] % MOD);
    }
}
