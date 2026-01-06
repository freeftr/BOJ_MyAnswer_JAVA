import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack<Integer> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int b = stack.pop();
                int a = stack.pop();

                if (c == '+') stack.push(a + b);
                else if (c == '-') stack.push(a - b);
                else if (c == '*') stack.push(a * b);
                else if (c == '/') stack.push(a / b);
            }
        }

        System.out.println(stack.pop());
    }
}
