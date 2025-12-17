import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int len = A.length;
        
        for (int i = 0; i < len; i++) {
            int a = A[i];
            int b = B[len - i - 1];
            
            answer += a * b;
        }

        return answer;
    }
}

/*
조건
- 길이가 같은 두 배열 A, B
- 각각 한 숫자씩 뽑아서 곱한값 누적.
- 배열의 길이만큼 반복.
- 누적값이 최소가 되게.

요구
- 누적된 최솟값 구해라.

풀이
1. 배열 둘 다 정렬
2. A 가장 큰값 * B 가장 작은값 + B 가장 작은값 * A 가장 큰값
*/