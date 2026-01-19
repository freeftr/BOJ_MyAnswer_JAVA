import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, W;
    static int[] deg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        deg = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            deg[U]++;
            deg[V]++;
        }

        double cnt = 0;
        for (int i = 2; i <= N; i++) {
            if (deg[i] == 1) cnt++;
        }

        double answer = W / cnt;

        System.out.println(answer);
    }
}

/*
조건
- 루트에 물이 있음. = W
- 물이 있고, 자식 있음 -> 자식에게 물 1 줌
- 부모가 물을 주면 쌓아둠

요구
- 기댓값 평균

풀이
- 몇번 작업하는지는 중요하지 않음 -> 결국 리프 노드에 있음을 의미.
-
 */