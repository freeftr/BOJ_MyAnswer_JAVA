import java.io.*;
import java.util.*;

public class Main {
    static int K, N, F;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] selected = new int[901];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            selected[0] = i;
            dfs(1, i);
            visited[i] = false;
        }

        System.out.println(-1);
    }

    static void dfs(int depth, int last) {
        if (depth == K) {
            for (int i = 0; i < K; i++) {
                for (int j = i + 1; j < K; j++) {
                    if (!graph.get(selected[i]).contains(selected[j])) {
                        return;
                    }
                }
            }

            Arrays.sort(selected, 0, K);
            for (int i = 0; i < K; i++) {
                System.out.println(selected[i]);
            }
            System.exit(0);
        }

        for (int i = last + 1; i <= N; i++) {
            if (visited[i]) continue;

            boolean canPick = true;
            for (int j = 0; j < depth; j++) {
                if (!graph.get(selected[j]).contains(i)) {
                    canPick = false;
                    break;
                }
            }

            if (canPick) {
                visited[i] = true;
                selected[depth] = i;
                dfs(depth + 1, i);
                visited[i] = false;
            }
        }
    }
}
