import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] kit;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kit = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kit[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, new boolean[N], 500);

        System.out.println(answer);
    }

    static void dfs(int depth, boolean[] used, int cur) {
        if (depth > N) return;

        if (cur < 500) return;

        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (used[i]) continue;
            used[i] = true;
            cur += kit[i];
            cur -= K;
            dfs(depth + 1, used, cur);
            cur -= kit[i];
            cur += K;
            used[i] = false;
        }
    }
}

/*
조건
- 하루가 지날때마다 K 감소
- N개의 키트
- 키트를 사용하면 중량 증가
- 항상 500이상 유지

 */