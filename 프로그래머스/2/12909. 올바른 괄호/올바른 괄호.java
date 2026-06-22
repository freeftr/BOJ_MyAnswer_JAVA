import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            
            if (stack.isEmpty()) {
                stack.add(cur);
            } else {
                if (stack.peek() == '(' && cur == ')') {
                    stack.pop();
                } else if (stack.peek() == '(' && cur == '(') {
                    stack.add(cur);
                } else if (stack.peek() == ')') {
                    return false;
                }
            }
        }
        
        if (!stack.isEmpty()) return false;

        return answer;
    }
}