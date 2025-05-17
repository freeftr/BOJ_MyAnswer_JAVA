class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        while (n > 0) {
            int num = n % 3;
            n /= 3;
            if (num == 0) {
                answer.append("4");
                n--;
            } else {
                answer.append(num);
            }
        }
        return answer.reverse().toString();
    }
}
