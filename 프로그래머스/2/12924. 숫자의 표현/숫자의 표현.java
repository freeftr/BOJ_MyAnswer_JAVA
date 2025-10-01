class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] arr = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + i;
        }
        
        int left = 0;
        int right = 1;
        
        while(left <= right && right <= n) {
            int now = arr[right] - arr[left];
            if (now == n) {
                answer++;
                right++;
            }
            if (now > n) left++;
            if (now < n) right++;
        }
        
        return answer;
    }
}

/*
조건
- n을 연속한 자연수로 표현

요구
- 경우의 수

풀이
- 누적합
*/