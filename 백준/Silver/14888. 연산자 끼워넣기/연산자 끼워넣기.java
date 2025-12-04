import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] A;
    static int[] operations = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(st.nextToken());
        }

        dfs(A[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    // + - * /
    static void dfs(int sum, int depth) {
        if (depth == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        if (operations[0] > 0) {
            operations[0]--;
            dfs(sum + A[depth], depth + 1);
            operations[0]++;
        }

        if (operations[1] > 0) {
            operations[1]--;
            dfs(sum - A[depth], depth + 1);
            operations[1]++;
        }

        if (operations[2] > 0) {
            operations[2]--;
            dfs(sum * A[depth], depth + 1);
            operations[2]++;
        }

        if (operations[3] > 0) {
            operations[3]--;
            dfs(sum / A[depth], depth + 1);
            operations[3]++;
        }
    }
}

/*
조건
- N개의 수로 이루어진 수열 A
- N - 1개의 연산자
- 수의 순서는 바꾸면 안댐.
- 연산자 우선순위 미적용
- 나눗셈은 몫만.
- 음수를 양수로 나누면 -> 양수로 바꾼고 몫 구하고 -1 곱해

요구
- 만든 수식에서 최대 값과 최소 값 구해

풀이
- 완탐
 */