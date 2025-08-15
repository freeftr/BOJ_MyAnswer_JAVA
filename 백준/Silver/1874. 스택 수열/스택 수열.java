import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int idx = 1;
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());

            if (stack.isEmpty()) {
                stack.push(idx++);
                sb.append("+\n");
            }

            while (stack.peek() != a && idx <= a) {
                stack.push(idx++);
                sb.append("+\n");
            }

            if (stack.peek() == a) {
                stack.pop();
                sb.append("-\n");
            }
        }

        System.out.println(stack.isEmpty() ? sb.toString() : "NO");
    }
}
