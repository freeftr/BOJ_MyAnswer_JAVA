import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
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
- 두 배열에서 한 개씩 뽑아 곱해서 곱하 값의 합의 최소가 되게.
*/