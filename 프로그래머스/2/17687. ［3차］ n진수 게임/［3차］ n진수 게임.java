class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        // 어디까지 탐색해야하는지 구해야 함.
        
        int num = 0;
        int limit = m * (t + 1);
    
        while (sb.length() <= m * (t + 1)) {
            sb.append(Integer.toString(num, n));
            // System.out.println(Integer.toString(num, n));
            num++;
        }
        
        String s = sb.toString();
        s = s.toUpperCase();
        
        for (int i = 0; i <= m * (t + 1); i++) {
            if (answer.length() == t) break;
            if (i % m == p - 1) {
                answer += s.charAt(i);
            }   
        }
        
        return answer;
    }
}


/*
조건
- 0부터 차례대로 말함.
- 10 이상 부터는 1, 0, 1, 1 이런식으로 나감.
- 이걸 N진수 함.

요구
- n진법으로 t개 미리 구해서 m명에 p번째면 뭐뭐말해야 하는지.
*/