import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int multiple = brown + yellow;
        
        for (int i = 1; i <= multiple; i++) {
            int a = i;
            int b = multiple / a;
            if (a * b != multiple) continue;
            
            if (a * 2 + b * 2 - 4 == brown) {
                answer[0] = a;
                answer[1] = b;
            }
        }
        
        if (answer[0] < answer[1]) {
            int temp = answer[0];
            answer[0] = answer[1];
            answer[1] = temp;
        }
        
        return answer; 
    }
}

/*
갈색 = 2a + 2b - 4
노란색 = (a - 2) * (b - 2) = ab - 2a - 2b + 4


0 0 0 0 0 0 0 0 
0 1 1 1 1 1 1 0
0 1 1 1 1 1 1 0
0 1 1 1 1 1 1 0
0 1 1 1 1 1 1 0
0 0 0 0 0 0 0 0
*/