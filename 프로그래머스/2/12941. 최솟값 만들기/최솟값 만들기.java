import java.util.*;
class Solution {
    public int solution(int[] A, int []B)
    {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[A.length - i - 1];
        }

        return answer;
    }
}

/*
조건
- A, B 배열
- 두 수를 곱한 값을 누적하여 더하기.

요구
- 누적된 값이 최소가 되게.
*/