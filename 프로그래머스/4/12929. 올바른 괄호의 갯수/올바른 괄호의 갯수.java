class Solution {
    public int solution(int n) {
        long[] dp = new long[n + 1];
        
        dp[0] = 1;
        
        if (n == 1) return 1;
        // if (n == 2) return 2;
        // if (n == 3) return 5;
        
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            long sum = 0;
            for (int j = 0; j < i; j++) {
                sum += dp[j] * dp[i - 1 - j];
            }
            dp[i] = sum;
        }
        return (int) dp[n];
    }
}

/*
조건
- ()같이 올바르게 닫히 괄호 => 올바른 괄호
- n개의 쌍이 주어질 대 가능한 괄호 문자열의 갯수

풀이
- dp?
- 카탈란 수
*/
