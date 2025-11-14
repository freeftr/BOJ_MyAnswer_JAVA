class Solution {
    
    static int n;
    
    public int solution(int[] cookie) {
        int answer = 0;
        
        n = cookie.length;
        
        if (n == 1) return 0;
        
        for (int m = 0; m < n - 1; m++) {
            int left = m;
            int right = m + 1;
            int lSum = cookie[left];
            int rSum = cookie[right];
            
            while (left >= 0 && right < n) {
                if (lSum == rSum) {
                    answer = Math.max(answer, lSum);
                    left--;
                    right++;
                    
                    if (check(left, right)) break;
                    
                    lSum += cookie[left];
                    rSum += cookie[right];
                } else if (lSum < rSum) {
                    left--;
                    
                    if (check(left, right)) break;
                    
                    lSum += cookie[left];
                } else if (lSum > rSum) {
                    right++;
                    
                    if (check(left, right)) break;
                    
                    rSum += cookie[right];
                }
            }
        }
        
        return answer;
    }
    
    static boolean check(int left, int right) {
        return left < 0 || right >= n;
    }
}

/*
조건
- 과자를 바구니 단위로 판다.
- N개의 바구니
- 첫째는 l부터 m까지
- 둘째는 m + 1부터 r까지
- l - m == r - m + 1

풀이
- 바구니의 누적합을 먼저 구해야 할듯.
- 그런 다음 누적합으로 l ~ m, m + 1 ~ r 값을 구해야 함.
*/