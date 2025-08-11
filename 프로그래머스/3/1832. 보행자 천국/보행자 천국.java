class Solution {
    int MOD = 20170805;
    static int M, N;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        M = m;
        N = n;
        
        int[][] dp = new int[M][N];
        
        dp[0][0] = 0;
        
        // 베이스 컨디션 설정
        for (int i = 1; i < N; i++) {
            if (cityMap[0][i] == 1) {
                break;
            }
            
            dp[0][i] = 1;
        }
        
        for (int i = 0; i < M; i++) {
            if (cityMap[i][0] == 1) {
                break;
            }
            
            dp[i][0] = 1;
        }
        
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (cityMap[i][j] == 1) continue;
                
                int top = 0, left = 0;
                for (int k = i - 1; k >= 0; k--) {
                    if (cityMap[k][j] != 2) {
                        top = dp[k][j];
                        break;
                    }
                }
                
                for (int k = j - 1; k >= 0; k--) {
                    if (cityMap[i][k] != 2) {
                        left = dp[i][k];
                        break;
                    }
                }
                
                dp[i][j] = (top + left) % MOD;
            }
        }
        
        // for (int i = 0; i < m; i++) {
        //     System.out.println();
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(dp[i][j]);
        //     }
        // }
        
        return dp[m-1][n-1];
    }
}

/*
0 1 1
1 2 3
1 3 6

0 0 0
0 2 0
0 0 0 

0 1 1
1 0 2
1 2 
*/

