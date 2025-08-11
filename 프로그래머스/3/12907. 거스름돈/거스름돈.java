class Solution {
    static int[] dp;
    public int solution(int n, int[] money) {
        int answer = 0;
        dp = new int[n+1];
        
        dp[0] = 1;
        
        for (int a : money) {
            for (int i = a; i <= n; i++) {
                dp[i] += dp[i - a];
                dp[i] %= 1_000_000_007;
            }
        }
        
        return dp[n];
    }
}

// dp[5] = dp[1] + dp[1] + dp[1] + dp[1] + dp[1];
// dp[5] = dp[2] + dp[3];