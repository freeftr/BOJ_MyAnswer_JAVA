import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    static int[] parent;

    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, cost));
        }

        int sum = 0;
        int cnt = 0;
        int max = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if(cnt==N-1){
                break;
            }

            if(find(e.a)==find(e.b)) continue;

            union(e.a,e.b);
            sum+=e.cost;
            cnt++;

            max = Math.max(max,e.cost);
        }

        System.out.println(sum-max);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a>b) parent[b] = a;
        else parent[a] = b;
    }

    static int find(int v){
        if(parent[v] == v) return v;
        else return parent[v] = find(parent[v]);
    }
}