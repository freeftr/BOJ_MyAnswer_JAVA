class Solution {
    public int solution(int n) {
        int answer = 0;
        int left = 1;
        int right = 1;
        int sum = 1;
        
        while (right <= n) {
            if (sum < n) {
                right++;
                
                sum += right;
            } else if (sum == n) {
                answer++;
                sum -= left;
                left++;
            } else {
                sum -= left;
                left++;
            }
        }
        
        return answer;
    }
}

/*
조건
- 자연수 n을 연속된 자연수의 합으로 표현

요구
- 연속된 자연수의 합으로 표현하는 방법

풀이
- 투포인터?
*/