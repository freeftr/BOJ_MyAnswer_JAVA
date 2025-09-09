import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int[] scores = new int[3];
        int idx = 0;
        int i = 0, n = dartResult.length();

        while (i < n) {
            int num = 0;
            while (i < n && Character.isDigit(dartResult.charAt(i))) {
                num = num * 10 + (dartResult.charAt(i) - '0');
                i++;
            }

            char bonus = dartResult.charAt(i++);
            if (bonus == 'S') num = (int) Math.pow(num, 1);
            else if (bonus == 'D') num = (int) Math.pow(num, 2);
            else if (bonus == 'T') num = (int) Math.pow(num, 3);

            if (i < n) {
                char op = dartResult.charAt(i);
                if (op == '*') {
                    num *= 2;
                    if (idx > 0) scores[idx - 1] *= 2;
                    i++;
                } else if (op == '#') {
                    num *= -1;
                    i++;
                }
            }

            scores[idx++] = num;
        }

        return scores[0] + scores[1] + scores[2];
    }
}
