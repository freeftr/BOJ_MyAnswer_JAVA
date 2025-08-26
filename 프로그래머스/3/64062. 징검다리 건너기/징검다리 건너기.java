class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int left = 0;
        int right = 200000000;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (check(mid, stones, k)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
    
    static boolean check(int target, int[] stones, int k) {
        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - target <= 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            
            if (cnt == k) return false;
        }
        return true;
    }
}