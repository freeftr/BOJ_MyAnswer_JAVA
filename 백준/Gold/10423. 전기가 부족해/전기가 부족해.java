import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] parent;
    static HashSet<Integer> plant;
    static class Edge{
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        plant = new HashSet<>();
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            plant.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.w - b.w);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new Edge(u, v, w));
        }

        int sum = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (plant.contains(find(e.u)) && plant.contains(find(e.v))) continue;

            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                sum += e.w;
//                System.out.println(e.w);
            }
        }

        System.out.println(sum);
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (plant.contains(a)) parent[b] = a;
        else parent[a] = b;
    }
}

/*
조건
- 케이블 설치 비용을 최소화해서 모든 도시 연결
- 한 도시가 두 개의 발전소에서 전기를 공급받으면 안댐.

요구
- 케이블 설치 최소 비용 구하기

풀이
- MST
- 이미 플랜트가 연결되어 있으면 넘기기
 */