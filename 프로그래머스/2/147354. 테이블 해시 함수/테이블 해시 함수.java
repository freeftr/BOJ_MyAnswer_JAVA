import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        col--;
        row_begin--;
        row_end--;
        
        final int c = col;
        Arrays.sort(data, (a, b) -> {
            if (a[c] == b[c]) return b[0] - a[0];
            return a[c] - b[c];
        });
        
        int A = 0;
        int B = 0;
        
        int xor = 0;
        for (int r = row_begin; r <= row_end; r++) {
            int s = 0;
            
            for (int j = 0; j < data[0].length; j++) {
                s += data[r][j] % (r + 1);
            }
            xor ^= s;
        }
        
        answer = xor;
        
        return answer;
    }
}

/*
조건
- 첫번째 컬럼 = 기본키, 유니크
- col 번째 컬럼 값을 기준으로 오름차순.
- S_i = i번째 튜플에 대해 각 컬럼의 값을 i로 나눈 나머지들의 합.
- row_begin <= i <= row_end인 모든 S_i를 누적하여 XOR한 값 반환.

2   2   6
1   5   10
4   2   9
3   8   3

4   2   9
2   2   6
1   5   10
3   8   3
*/