import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[] s = br.readLine().split("");

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(s[i]);

            while (!stack.isEmpty() && K > 0 && stack.peek() < cur) {
                stack.pop();
                K--;
            }

            stack.add(cur);
        }
        
        while (K > 0) {
            K--;
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        sb.reverse();

        System.out.println(sb);
    }
}

/*
조건
- N자리 숫자에서 숫자 K개 지워서 얻을 수 있는 가장 큰수 구하기

요구
- 지워서 얻을 수 있는 가장 큰수 출력

풀이
- 스택?
4   1   7   7   2   5   2   8   4   1

4

 */
