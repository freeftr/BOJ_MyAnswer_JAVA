import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> line = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            line.add(Integer.parseInt(st.nextToken()));
        }

        int idx = 1;

        while (idx <= N) {
            if (!line.isEmpty() && idx == line.peek()) {
                line.poll();
                idx++;
            } else if (!stack.isEmpty() && idx == stack.peek()) {
                stack.pop();
                idx++;
            } else {
                if (line.isEmpty()) break;

                stack.add(line.poll());
            }
        }

        if (idx == N + 1) System.out.println("Nice");
        else System.out.println("Sad");
    }
}
/*
조건
- 간식 행사함.
- 번호표 순서로 간식 줌.
-

요구
- 모두 간식을 받을 수 있는지

풀이
- 스택
- 원하는 idx가 나올때까지 스택에 넣기.
 */
