class Solution {
    public int solution(int n) {
        int answer = 0;
        int next = n + 1;
        
        while (next <= Integer.MAX_VALUE) {
            if (Integer.bitCount(next) == Integer.bitCount(n)) {
                return next;
            }
            
            next++;
        }
        
        return answer;
    }
}

/*
- n의 다음 큰 숫자 = n보다 큰 자연수
- n의 다음 큰 숫자와 n은 이진수로 변환 시 1의 개수가 같다.
- 다큰수는 위 두 개를 만족하는 수중 가장 작은 수
*/