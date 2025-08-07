import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length;
        
        Arrays.sort(B);
        Arrays.sort(A);
        
        int aIdx = 0;
        int bIdx = 0;
        
        while(aIdx < n && bIdx < n) {
            if(A[aIdx] < B[bIdx]) {
                
                answer++;
                aIdx++;
                bIdx++;
            } else {
                bIdx++;
            }
        }
        
        return answer;
    }
}