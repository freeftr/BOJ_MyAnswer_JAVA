import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int answer = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char cur = S.charAt(i);

            if (cur == ')' && stack.isEmpty()) {
                answer++;
            } else if (cur == ')' && !stack.isEmpty()) {
                stack.pop();
            }else if (cur == '(') {
                stack.push('(');
            }
        }

        answer += stack.size();

        System.out.println(answer);
    }
}

/*
조건
- 올바른 괄호열 -> 짝이 맞음

요구
- 앞과 뒤에 붙여야할 괄호의 최소 개수 구하기

풀이
- 스택
-
 */