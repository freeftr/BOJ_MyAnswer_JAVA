import java.io.*;
import java.util.*;

class Solution {
    
    static String[] operations = new String[]{"+", "-", "*"};

    public long solution(String expression) {
        long answer = 0;
        
        ArrayList<String> words = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '*' || ch == '+' || ch == '-') {
                if (s.length() > 0) words.add(s.toString());
                words.add(String.valueOf(ch));
                s.setLength(0);
            } else {
                s.append(ch);
            }
        }
        if (s.length() > 0) words.add(s.toString());

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (a == b) continue;
                for (int c = 0; c < 3; c++) {
                    if (c == a || c == b) continue;
                    long result = calculate(operations[a], operations[b], operations[c], words);
                    answer = Math.max(answer, Math.abs(result));
                }
            }
        }
        return answer;
    }
    
    static long calculate(String first, String second, String third, ArrayList<String> words) {
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<String> ops = new ArrayList<>();

        for (String word : words) {
            if (word.equals("*") || word.equals("+") || word.equals("-")) {
                ops.add(word);
            } else {
                nums.add(Long.parseLong(word));
            }
        }
        
        String[] order = { first, second, third };
        for (String op : order) {
            for (int i = 0; i < ops.size(); ) {
                if (ops.get(i).equals(op)) {
                    long a = nums.get(i);
                    long b = nums.get(i + 1);
                    long r;
                    if (op.equals("*"))      r = a * b;
                    else if (op.equals("+")) r = a + b;
                    else                     r = a - b;

                    nums.set(i, r);
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
