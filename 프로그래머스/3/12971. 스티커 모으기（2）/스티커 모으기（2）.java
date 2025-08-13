class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        
        int N = sticker.length;
        
        if (N == 1) {
            return sticker[0];
        }
        
        // 첫 번째 선택
        int[] dp1 = new int[N];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
        
        for (int i = 2; i < N - 1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i - 2] + sticker[i]);
        }
        
        // 두 번째부터 선택
        int[] dp2 = new int[N];
        dp2[0] = 0;
        dp2[1] = sticker[1];
        
        for (int i = 2; i < N; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i - 2] + sticker[i]);
        }
        
        answer = Math.max(dp1[N - 2], dp2[N - 1]);

        return answer;
    }
}

// 14 6 5 11 3 9 2 10
// 14 14