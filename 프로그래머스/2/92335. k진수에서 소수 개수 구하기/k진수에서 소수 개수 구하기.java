class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n, k);
        String[] nums = s.split("0+");
        
        if (nums.length == 0) return 0;
        
        for (String num : nums) {
            long temp = Long.parseLong(num);
            if (check(temp)) answer++;
        }
        return answer;
    }
    
    static boolean check(long num) {
        if (num == 0) return false;
        if (num == 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        
        for (long i = 3; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}