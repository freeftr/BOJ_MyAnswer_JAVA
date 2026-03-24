import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<Integer, ArrayList<Integer>> tubes = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> stations = new HashMap<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int a = Integer.parseInt(st.nextToken());
                tubes.computeIfAbsent(i, k -> new ArrayList<>()).add(a);
                stations.computeIfAbsent(a, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        boolean[] visitedT = new boolean[M + 1];

        q.add(new int[]{1, 1});
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int d = cur[1];

            if (v == N) {
                System.out.println(d);
                return;
            }

            ArrayList<Integer> nTs = stations.get(v);
            if (nTs == null) continue;

            for (int nt : nTs) {
                if (visitedT[nt]) continue;
                visitedT[nt] = true;
                for (int ns : tubes.get(nt)) {
                    if (visited[ns]) continue;
                    q.add(new int[]{ns, d + 1});
                    visited[ns] = true;
                }
            }
        }
        System.out.println(-1);
    }
}

/*
조건
- 하이퍼튜브 : 역 K개를 연결

요구
- 1번에서 N번가는데 방문하는 최소 역 개수
 */