import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        dist = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (find(a) != find(b)) union(a, b);
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE) continue;
                    if (dist[k][j] == Integer.MAX_VALUE) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        HashSet<Integer> groups = new HashSet<>();
        ArrayList<Integer> presidents = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            parent[i] = find(parent[i]);
            groups.add(parent[i]);
        }

        int K = groups.size();

        sb.append(K).append("\n");

        for (int group : groups) {
            ArrayList<Integer> members = new ArrayList<>();

            for (int i = 1; i < N + 1; i++) {
                if (group == parent[i])  members.add(i);
            }

            int president = -1;
            int best = Integer.MAX_VALUE;

            for (int a : members) {
                int max = 0;
                for (int b : members) {
                    if (dist[a][b] == Integer.MAX_VALUE) continue;

                    max = Math.max(max, dist[a][b]);
                }

                if (best >= max) {
                    best = max;
                    president = a;
                }
            }

            presidents.add(president);
        }

        Collections.sort(presidents);

        for (int president : presidents) {
            sb.append(president).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }
}

/*
조건
- 회의 개최한다.
- 위원회 구성해야 함.
- 서로 알고 있으면 같은 위원회.
- 위원회의 수는 최대가 되게.
- 위원회 별 대표 뽑아야 함.
- 의사전달시간: 위원회내에서 대표한테 의사를 전달하는데 거치는 사람 수.
- 의사전달시간의 최댓값이 가장 낮게 대표 정해야 함.

8
7
1 2
2 3
4 5
5 6
4 6
6 7
7 4

1 2 3
4 5 6 7
8
 */