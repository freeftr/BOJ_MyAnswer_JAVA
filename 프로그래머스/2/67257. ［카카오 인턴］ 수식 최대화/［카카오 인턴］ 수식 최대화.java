import java.util.*;

class Solution {

    static ArrayList<Long> nums = new ArrayList<>();
    static ArrayList<String> ops = new ArrayList<>();
    static String[] operations = {"+", "-", "*"};

    public long solution(String expression) {
        long answer = 0;
        rebuild(expression);

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    if (a == b || b == c || a == c) continue;
                    rebuild(expression);
                    long result = calculate(a, b, c);
                    answer = Math.max(answer, Math.abs(result));
                }
            }
        }
        return answer;
    }

    static void rebuild(String expression) {
        nums = new ArrayList<>();
        ops  = new ArrayList<>();
        Deque<String> dq = new ArrayDeque<>();
        String[] s = expression.split("");
        for (int i = 0; i < s.length; i++) {
            String ch = s[i];
            if (ch.equals("+") || ch.equals("-") || ch.equals("*")) {
                StringBuilder temp = new StringBuilder();
                while (!dq.isEmpty()) temp.append(dq.pollFirst());
                if (temp.length() != 0) nums.add(Long.parseLong(temp.toString()));
                ops.add(ch);
            } else {
                dq.addLast(ch);
            }
        }
        if (!dq.isEmpty()) {
            StringBuilder temp = new StringBuilder();
            while (!dq.isEmpty()) temp.append(dq.pollFirst());
            nums.add(Long.parseLong(temp.toString()));
        }
    }

    static long calculate(int a, int b, int c) {
        String[] order = {operations[a], operations[b], operations[c]};
        for (String od : order) {
            int i = 0;
            while (i < ops.size()) {
                if (ops.get(i).equals(od)) {
                    long left = nums.get(i);
                    long right = nums.get(i + 1);
                    long val;
                    if (od.equals("+")) val = left + right;
                    else if (od.equals("-")) val = left - right;
                    else val = left * right;
                    nums.set(i, val);
                    nums.remove(i + 1);
                    ops.remove(i);
                } else {
                    i++;
                }
            }
        }
        return nums.get(0);
    }
}
