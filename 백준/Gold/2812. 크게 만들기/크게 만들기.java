import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int now = s.charAt(i) - '0';
            while (!stack.isEmpty() && K > 0 && stack.peekLast() < now) {
                stack.removeLast();
                K--;
            }
            stack.addLast(now);
        }

        while (K-- > 0) {
            stack.removeLast();
        }

        for (int num : stack) {
            System.out.print(num);
        }
    }
}
