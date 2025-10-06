import java.util.*;
class Solution {
    public String solution(String p) {
        String answer = "";
        
        return dfs(p);
    }
    
    static String dfs(String s) {
        if (s.equals("")) return "";
        
        String u = "";
        String v = "";
        for (int i = 1; i <= s.length(); i++) {
            u = s.substring(0, i);
            
            if (checkBalance(u)) {
                v = s.substring(i);
                break;
            }
        }
        
        if (checkRight(u)) {
            return u + dfs(v);
        } else {
            String temp = "(" + dfs(v) + ")";
            u = u.substring(1, u.length() - 1);
            for (int i = 0; i < u.length(); i++) {
                temp += u.charAt(i) == '(' ? ')' : '(';
            }
            
            return temp;
        }
    }
    
    static boolean checkBalance(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            if (s.charAt(i) == ')') right++;
        }
        
        return left == right;
    }
    
    static boolean checkRight(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty()) return false;
                if (stack.peek() == '(') stack.pop();
            }
        }
        
        return stack.isEmpty();
    }
}