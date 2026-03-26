import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while (true) {
            String s = br.readLine();

            if (s.charAt(0) == '-') break;

            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);

                if (cur == '{') {
                    stack.push(cur);
                } else {
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        stack.push(cur);
                    }
                }
            }

            int open = 0, close = 0;

            while (!stack.isEmpty()) {
                if (stack.pop() == '{') open++;
                else close++;
            }

            int answer = (open + 1) / 2 + (close + 1) / 2;

            sb.append(idx++ + ". ");
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}

/*
안정된 문자열
- 빈 문자열
- {S}
- ST

연산
- 여 -> 닫
- 닫 -> 여

요구
- 올바르게 만들기 위한 최소 횟수

풀이
- 올바른 것 제거
 */