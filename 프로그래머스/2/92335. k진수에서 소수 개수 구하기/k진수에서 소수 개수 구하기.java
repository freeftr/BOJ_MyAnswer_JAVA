class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Long.toString(n, k);
        String[] nums = s.split("0+");
        
        for (String num : nums) {
            if (num.equals("")) continue;
            
            answer += isPrime(Long.parseLong(num)) ? 1 : 0;
        }
        return answer;
    }
    
    static boolean isPrime (long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}