import java.io.*;
import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        
        
        return dfs(p);
    }
    
    static String dfs(String s) {
        String u = "";
        String v = "";
        
        // 1.
        if (s.equals("")) {
            return "";
        }
        
        String result = "";
        
        for (int i = 2; i <= s.length(); i++) {
            if (check1(s.substring(0,i))) {
                u = s.substring(0, i);
                v = s.substring(i);
                break;
            }
        }
        
        if (check2(u))  {
            return u + dfs(v);
        } else {
            String temp = "(" + dfs(v) + ")";
            
            StringBuilder sb = new StringBuilder(temp);
            for (int i = 1; i < u.length() - 1; i++) {
                sb.append(u.charAt(i) == '(' ? ')' : '(');
            }
            return sb.toString();
        }
    }
    
    // 균형잡힌 문자열 검사
    static boolean check1(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
        }
        
        return left == right;
    }
    
    // 올바른 문자열 검사
    static boolean check2(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (stack.peek() == '(' && s.charAt(i) == ')') {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        
        return stack.isEmpty();
    }
}

//()()()