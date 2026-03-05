class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String[] s = Integer.toString(n, k).split("0");
        
        for (String a : s) {
            if (a.equals("")) continue;
            
            long cur = Long.parseLong(a);
            
            if (isPrime(cur)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    static boolean isPrime(long num) {
        if (num < 2) return false;
        
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        
        return true;
    }
}