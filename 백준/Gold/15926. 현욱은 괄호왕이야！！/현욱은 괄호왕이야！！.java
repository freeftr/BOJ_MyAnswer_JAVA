import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int answer = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else { // ')'
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i); // 새로운 기준점
                } else {
                    answer = Math.max(answer, i - stack.peek());
                }
            }
        }

        System.out.println(answer);
    }
}


/*
조건
- 여는 괄호 닫는 괄호로 구성된 문자열
- () 올바른 괄호 문자열
- x가 올바름 -> (x)도 올바름
- x, y가 올바름 -> xy도 올바름
 */