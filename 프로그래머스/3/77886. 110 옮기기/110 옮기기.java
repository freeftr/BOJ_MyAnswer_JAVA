import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int idx = 0; idx < s.length; idx++) {
            String a = s[idx];

            StringBuilder stack = new StringBuilder();
            int cnt110 = 0;
            for (int i = 0; i < a.length(); i++) {
                stack.append(a.charAt(i));
                int len = stack.length();
                if (len >= 3
                    && stack.charAt(len - 3) == '1'
                    && stack.charAt(len - 2) == '1'
                    && stack.charAt(len - 1) == '0') {
                    stack.delete(len - 3, len);
                    cnt110++;
                }
            }

            String remain = stack.toString();
            int lastZero = remain.lastIndexOf('0');

            String attach = "110".repeat(cnt110);

            StringBuilder res = new StringBuilder();
            if (lastZero == -1) {
                res.append(attach).append(remain);
            } else {
                res.append(remain, 0, lastZero + 1)
                   .append(attach)
                   .append(remain.substring(lastZero + 1));
            }

            answer[idx] = res.toString();
        }
        return answer;
    }
}
