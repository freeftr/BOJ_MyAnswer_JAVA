import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] parent;
    static ArrayList<Edge> Edge_List = new ArrayList<>();
    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N+1];

        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        for(int i = 1; i <= N; i++){
            int a = Integer.parseInt(br.readLine());
            Edge_List.add(new Edge(0,i,a));
        }

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                //if(i==j)continue;
                int a = Integer.parseInt(st.nextToken());
                Edge_List.add(new Edge(i,j,a));
            }
        }


        Collections.sort(Edge_List);

        int cnt = 0;
        int ans = 0;

        for(Edge edge: Edge_List){
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            if(find(from) != find(to)){
                cnt++;
                union(to,from);
                ans+=cost;
            }
        }

        if(cnt==N){
            System.out.println(ans);
        }
    }

    public static int find(int v){
        if(parent[v]==v) return v;
        return parent[v] = find(parent[v]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a>b) parent[a] = b;
        else parent[b] = a;
    }
}