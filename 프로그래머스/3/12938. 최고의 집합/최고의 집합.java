import java.io.*;
import java.util.*;
class Solution {
    static int[] answer;
    public int[] solution(int n, int s) {
        answer = new int[n];
        int div = s / n;
        int mod = s % n;
        int idx = 0;
        
        if (div == 0) {
            return new int[]{-1};
        }
        
        Arrays.fill(answer, div);
        for (int i = 1; i <= mod; i++) {
            answer[idx % n]++;
            idx++;
        }
        
        Arrays.sort(answer);
        return answer;
    }
}