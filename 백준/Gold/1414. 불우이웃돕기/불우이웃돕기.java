import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N;
    static int[] parent;
    static int total = 0;

    static class Edge implements Comparable<Edge> {
        int to;
        int from;
        int cost;

        public Edge(int to, int from, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                int a = s.charAt(j);
                if (97<=a && a<=122) {
                    a -= 96;
                } else if (65<=a && a<=90) {
                    a -= 38;
                } else {
                    a = Integer.MAX_VALUE;
                }
                if(a!=Integer.MAX_VALUE) {
                    total += a;
                    pq.offer(new Edge(i, j, a));
                }
            }
        }

        System.out.println(min(pq));
    }

    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        return find(parent[v]);
    }
    static void union(int v, int w) {
        v = find(v);
        w = find(w);
        if(v>w){
            parent[v] = w;
        }
        else{
            parent[w] = v;
        }
    }

    static int min(PriorityQueue<Edge> pq) {
        int cnt = 0;
        int sum = 0;

        while (!pq.isEmpty()) {
            if(cnt==N-1) break;
            Edge e = pq.poll();

            if(find(e.to) == find(e.from)) {
                continue;
            }

            cnt++;
            union(e.to, e.from);
            sum+=e.cost;
        }

        return (cnt == N - 1) ? total - sum : -1;
    }
}
