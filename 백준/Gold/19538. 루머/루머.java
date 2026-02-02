import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] result;
    static int[] deg;
    static int[] cnt;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        result = new int[N + 1];
        deg = new int[N + 1];
        cnt = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (true) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 0) break;

                graph.get(i).add(a);
                graph.get(a).add(i);
                deg[i]++;
                deg[a]++;
            }
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Queue<int[]> q = new ArrayDeque<>();
        Arrays.fill(result, -1);

        for (int i = 0; i < M; i++) {
            int r = Integer.parseInt(st.nextToken());
            result[r] = 0;
            q.add(new int[]{r, 0});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int time = cur[1];

            for (int nv : graph.get(v)) {
                if (result[nv] != -1) continue;

                cnt[nv]++;

                if (cnt[nv] * 2 >= deg[nv]) {
                    result[nv] = time + 1;
                    q.add(new int[]{nv, time + 1});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.print(sb);
    }
}
