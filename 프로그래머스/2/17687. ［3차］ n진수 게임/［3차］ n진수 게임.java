class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String s = "";
        int idx = 0;
        while (true) {
            String a = Integer.toString(idx++, n);
            s += a;
            if (s.length() > t * m) {
                break;
            }
        }
        s = s.substring(0, t * m);
        s = s.toUpperCase();
        System.out.println(s);
        
        if (m == p) p = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if ((i + 1) % m == p) {
                answer += s.charAt(i) + "";
            }
        }
        
        return answer;
    }
}