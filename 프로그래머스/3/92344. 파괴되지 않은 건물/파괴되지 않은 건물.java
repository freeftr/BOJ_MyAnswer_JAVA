class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int r = board.length;
        int c = board[0].length;
        
        int[][] diff = new int[r + 1][c + 1];
        
        for (int[] sk : skill) {
            int type = sk[0]; // 1: 공격 2: 회복
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            int degree = sk[5];
            
            if (type == 1) degree *= -1;
            
            diff[r1][c1] += degree;
            if (c2 + 1 <= c) diff[r1][c2 + 1] += -degree;
            if (r2 + 1 <= r) diff[r2 + 1][c1] += -degree;
            if (r2 + 1 <= r && c2 + 1 <= c) diff[r2 + 1][c2 + 1] += degree;   
        }
        
        for (int i = 0; i < r; i++) {
            for (int j = 1; j < c; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }
        
        for (int i = 0; i < c; i++) {
            for (int j = 1; j < r; j++) {
                diff[j][i] += diff[j - 1][i];
            }
        }
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] + diff[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}

/*
조건
- 건물 공격받으면 내구도 감소.
- 내구도 0이하면 파괴.
- 아군은 회복을 통해 건물 내구도 높임.
- 스킬은 직사각형 모양.
*/