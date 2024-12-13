import java.io.*;
import java.util.*;


/*
MST => 크루스칼
오르막길 가중치 1
 */
public class Main {

    static int N, M;

    static int[] parent;
    static ArrayList<Edge> list = new ArrayList<>();

    static public class Edge{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];

        for (int i = 0; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int ud = Integer.parseInt(st.nextToken());

            list.add(new Edge(from,to,ud));
        }

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int answer1 = 0;
        Collections.sort(list, (o1,o2) -> o1.cost - o2.cost);

        int cnt = 0;
        for (int i = 0; i < list.size(); i++) {
            Edge now = list.get(i);

            if(find(now.from) != find(now.to)){
                union(now.from, now.to);

                if(now.cost==0){
                    answer1++;
                }
            }
        }

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int answer2 = 0;

        cnt = 0;

        for (int i = list.size()-1; i >= 0; i--) {
            Edge now = list.get(i);

            if(find(now.from) != find(now.to)){
                union(now.from, now.to);

                if(now.cost==0){
                    answer2++;
                }
            }
        }

        int answer = Math.abs(answer1*answer1 - answer2*answer2);

        System.out.println(answer);
    }

    static public int find(int v){
        if(parent[v]==v){
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static public void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a>b){
            parent[a] = b;
        }
        if(a<b){
            parent[b] = a;
        }
    }
}