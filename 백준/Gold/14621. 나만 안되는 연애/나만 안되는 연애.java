import java.io.*;
import java.util.*;

public class Main {
    static public int N, M;
    static int[] parent;
    static String[] gender;

    static ArrayList<Edge> list = new ArrayList<>();

    static public class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
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

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gender = new String[N+1];
        parent = new int[N+1];
        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        String[] s = br.readLine().split(" ");
        for (int i = 1; i <= N; i++){
            gender[i] = s[i-1];
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new Edge(from, to, cost));
        }

        Collections.sort(list);

        int cnt = 0, ans = 0;

        for(Edge edge : list){
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            if(gender[from].equals(gender[to]))continue;

            if(find(to)!=find(from)){
                cnt++;
                union(to,from);
                ans+=cost;
            }
        }

        if(cnt==N-1){
            System.out.println(ans);
        }
        else{
            System.out.println(-1);
        }

    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a>b) parent[a] = b;
        if(a<b) parent[b] = a;
    }

    static int find(int v){
        if(parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }
}