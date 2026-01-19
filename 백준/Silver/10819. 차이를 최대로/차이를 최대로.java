import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        dfs(N, new int[N], 0, A);

        System.out.println(answer);
    }

    static void dfs(int N, int[] selected, int depth, int[] A) {
        if (depth == N) {
            int result = 0;

            for (int i = 0; i < N - 1; i++) {
                result += Math.abs(selected[i] - selected[i + 1]);
            }

            answer = Math.max(answer, result);
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            selected[depth] = A[i];
            visited[i] = true;
            dfs(N, selected, depth + 1, A);
            visited[i] = false;
        }
    }
}

/*
조건
- N 개의 정수로 이루어진 배열 A
- 정수 순서 봐꿔서 최댓값 나오게

요구
- 최댓값 구하기

풀이
- 백트래킹
 */