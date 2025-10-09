class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n, k);
        String[] nums = s.split("0+");
        
        if (nums.length == 0) return 0;
        
        for (String num : nums) {
            if (check(Long.parseLong(num))) answer++;
        }
        return answer;
    }
    
    static boolean check(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        long limit = (long) Math.sqrt(num);
        for (long i = 3; i <= limit; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

}