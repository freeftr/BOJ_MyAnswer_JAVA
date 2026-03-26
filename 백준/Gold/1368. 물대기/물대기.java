import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;
    static class Edge{
        int from;
        int to;
        int c;

        Edge(int from, int to, int c) {
            this.from = from;
            this.to = to;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int W = Integer.parseInt(br.readLine());
            parent[i] = i;
            edges.add(new Edge(0, i, W));
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int c = Integer.parseInt(st.nextToken());
                edges.add(new Edge(i, j, c));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.c - b.c);
        pq.addAll(edges);
        int sum = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                sum += e.c;
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

        parent[b] = a;
    }
}

/*
조건
- 물대는 방법
- 직접 우물
- 다른 논으로부터 물을 끌어옴

요구
- 최소 비용으로 모든 논에 물을 대기
 */