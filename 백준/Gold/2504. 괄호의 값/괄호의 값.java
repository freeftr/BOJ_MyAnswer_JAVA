import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split("");

        Stack<String> stack = new Stack<>();

        int answer = 0;
        int num = 1;
        for (int i = 0; i < s.length; i++) {
            String cur = s[i];

            if (cur.equals("(")) {
                stack.push(cur);
                num *= 2;
            } else if (cur.equals("[")) {
                stack.push(cur);
                num *= 3;
            } else if (cur.equals(")")) {
                if (stack.isEmpty() || !stack.peek().equals("(")) {
                    answer = 0;
                    break;
                }

                if (s[i - 1].equals("(")) {
                    answer += num;
                }

                stack.pop();
                num /= 2;

            } else if (cur.equals("]")) {
                if (stack.isEmpty() || !stack.peek().equals("[")) {
                    answer = 0;
                    break;
                }

                if (s[i - 1].equals("[")) {
                    answer += num;
                }

                stack.pop();
                num /= 3;

            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
            return;
        }

        System.out.println(answer);
    }
}

/*
조건
- (), [] 올바른 괄호열
- x가 올바르면 (x)도 올바름
- x, y가 올바르면 xy도 올바름

점수 계산
- () = 2
- [] = 3
- (x) = 2 * x
- [x] = 3 * x
- xy = x + y

([()])
(
([
([(
 */