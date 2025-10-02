import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] F = new int[1000001];
        int[] result = new int[N];
        st = new StringTokenizer(br.readLine());

        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            F[A[i]]++;
        }

        for (int i = N - 1; i >= 0; i--) {
            int a = A[i];
            int f = F[a];

            while (!stack.isEmpty() && f >= stack.peek()[1]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek()[0];

            stack.push(new int[]{a, f});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]);
            if (i != N - 1) sb.append(" ");
        }

        System.out.println(sb);
    }
}

/*
조건
- F는 Ai가 수열 A에서 등장한 횟수
- 오등큰수: 오른쪽에 있으면서 수열 A에서 등장한 횟수가 F(Ai)보다 큰 수 중에 가장 왼쪽에 있는 수
- 없으면 -1

요구
- 거꾸로 집어넣으면서 스택에 넣기.
- 비어있으면 -1 기록하고 현재 넣기.
- 스택에 있는 f값이 현재 f보다 크면 현재 넣고, 스택에 있는 n값 넣기
- 스택에 있는 값이 현재 f와 같거나 작으면 빼기.
- 다 뺀 후에는

A   1   1   2   3   4   2   1
F   3   3   2   1   1   2   3

7 [1,3]
스택 비어 있음 -> -1 기록, 스택에 [1,3] 넣기
[1,3]

6 [2,2]
스택에 더 큰 f값 있음 -> top[0] = 1 기록, 스택에 [2,2] 넣기

5 [4,1]
스택에 더 큰 f값 있음 -> top[0] = 2 기록, 스택에 [4,1] 넣기

4 [3,1]
스택에 동일한 f값 있음 -> f가 커질 때까지 pop, top[0] = 2 기록, 스택에 [3,1] 넣기
 */