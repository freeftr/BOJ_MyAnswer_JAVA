import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Star> stars = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars.add(new Star(i, x , y));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Double.compare(a.cost, b.cost));

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) continue;
                Star a = stars.get(i);
                Star b = stars.get(j);
                pq.add(new Edge(i, j, Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))));
            }
        }

        int cnt = 0;
        double answer = 0;

        while (cnt < n - 1) {
            Edge now = pq.poll();

            if (find(now.from) != find(now.to)) {
                union(now.from, now.to);
                answer += now.cost;
                cnt++;
            }
        }

        System.out.printf("%.2f", answer);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static class Edge {
        int from;
        int to;
        double cost;
        Edge (int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static class Star{
        int idx;
        double x;
        double y;
        Star(int idx, double x, double y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
}
