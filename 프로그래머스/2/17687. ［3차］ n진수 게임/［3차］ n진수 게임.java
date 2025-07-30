class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int number = 0;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        
        while (cnt != m * t) {
            sb.append(Integer.toString(number++, n));
            cnt++;
        }
        
        String s = sb.toString();
        for (int i = p - 1; i < s.length(); i+=m) {
            answer += s.charAt(i);
            if (answer.length() == t) {
                break;
            }
        }
        
        return answer.toUpperCase();
    }
}