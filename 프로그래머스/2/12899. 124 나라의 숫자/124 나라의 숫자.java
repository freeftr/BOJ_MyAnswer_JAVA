class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        
        while (n > 0) {
            int mod = n % 3;
            if (mod == 0) {
                answer.append("4");
                n = n / 3 - 1;
            } else if (mod == 1) {
                answer.append("1");
                n /= 3;
            } else {
                answer.append("2");
                n /= 3;
            }
        }
        
        return answer.reverse().toString();
    }
}
