import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            if (check(s)) {
                answer++;
            }
            s = s.substring(1) + s.charAt(0);
        }

        return answer;
    }

    static boolean check(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == '[' || cur == '(' || cur == '{') {
                st.push(cur);
            } else {
                if (st.isEmpty()) return false;

                char top = st.peek();
                if ((cur == ']' && top == '[') ||
                    (cur == ')' && top == '(') ||
                    (cur == '}' && top == '{')) {
                    st.pop();
                } else {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }
}
