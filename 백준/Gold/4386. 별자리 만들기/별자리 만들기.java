import java.nio.DoubleBuffer;
import java.util.*;
import java.io.*;
public class Main {

    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return Double.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        double[] X = new double[n+1];
        double[] Y = new double[n+1];
        parent = new int[n+1];

        for(int i = 1; i <= n; i++){
            parent[i]=i;
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            X[i] = x;
            Y[i] = y;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                double dist = Math.sqrt(Math.pow(X[i]-X[j],2)+Math.pow(Y[i]-Y[j],2));
                pq.add(new Edge(i,j,dist));
            }
        }

        int cnt = 0;
        double ans = 0;
        while(cnt<n-1){
            Edge now = pq.poll();
            if(find(now.from)!=find(now.to)){
                union(now.from,now.to);
                ans+=now.cost;
                cnt++;
            }
        }
        System.out.printf("%.2f", ans);
    }

    public static int find(int v){
        if(parent[v]==v)return v;
        return parent[v]=find(parent[v]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a > b) parent[a] = b;
        else parent[b] = a;
    }
}