import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<String> stack = new Stack<>();
        
        String[] letters = s.split("");
        
        for (String letter : letters) {
            if (letter.equals("(")) {
                stack.push("(");
            } else {
                if (stack.isEmpty()) return false;
                
                stack.pop();
            }
        }
        
        answer = stack.isEmpty() ? true : false;
        
        return answer;
    }
}

/*
조건
- 괄호가 바르게 짝지어졋다. = ()

요구
- 올바르면 true, 틀리면 false;

풀이
- 스택
*/