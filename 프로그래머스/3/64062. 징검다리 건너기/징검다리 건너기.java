class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 0;
        int right = 200000000;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(stones, k, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
    
    static boolean check(int[] stones, int k, int mid) {
        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i]  - mid <= 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            if (cnt == k) return false;
        }
        return true;
    }
}