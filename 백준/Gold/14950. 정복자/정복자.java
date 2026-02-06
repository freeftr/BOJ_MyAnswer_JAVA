import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, k;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.c - b.c);

    static class Edge{
        int u;
        int v;
        int c;

        Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        int sum = 0;
        int add = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.v) != find(e.u)) {
                sum += e.c;
                sum += add;
                add += k;
                cnt++;
                union(e.v, e.u);
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

        parent[a] = b;
    }
}
