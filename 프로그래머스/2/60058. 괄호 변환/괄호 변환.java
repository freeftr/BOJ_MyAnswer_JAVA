import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        
        return dfs(p);
    }
    
    static String dfs(String s) {
        if (s == "") return s;
        
        String u = "";
        String v = "";
        
        for (int i = 2; i <= s.length(); i+=2) {
            if (balance(s.substring(0, i))) {
                u = s.substring(0, i);
                v = s.substring(i);
                break;
            }
        }
        
        if (check(u)) {
            return u + dfs(v);
        } else {
            String temp = "(" + dfs(v) + ")";
            for (int i = 1; i < u.length() - 1; i++) {
                temp += u.charAt(i) == '(' ? ')' : '(';
            }
            return temp;
        }
    }
    
    static boolean balance(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            if (s.charAt(i) == ')') right++;
        }
        return left == right;
    }
    
    static boolean check(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

/*
빈 문자열 -> 빈 문자열 반환
u와 v로 분리
- u는 더이상 균형잡힌 문자열이되면 안댐.
- v는 빈 문자열일 수 있음.

u가 올바른 문자열이 아니면 
- 빈문자열에 (붙이기
- v에 대해 재귀 수행
- ')'다시 붙이기
- u의 첫 마지막 제거, 
*/