import java.util.*;
import java.io.*;
public class Main {
    static int V, E;
    static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;
        Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }

    }
    static ArrayList<Node> list;
    static int[] parents;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V+1];
        list = new ArrayList<>();
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Node(from, to , cost));
        }
        Collections.sort(list);
        for(int i = 1; i <= V; i++){
            parents[i] = i;
        }

        int sum=0;
        for(Node node : list){
            int from = node.from;
            int to = node.to;
            int cost = node.cost;
            if(find(from)!=find(to)){
                union(from,to);
                sum+=cost;
            }
        }
//        System.out.println();
//        for(int i = 1; i <= V; i++){
//            System.out.printf("i: %d p: %d     ", i, parents[i]);
//        }
        System.out.println(sum);
    }
    //  find 부모 찾기 알고리즘
    public static int find(int v){
        if(parents[v]==v){
            return v;
        }
        return parents[v] = find(parents[v]);
    }
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b){
            parents[b] = a;
        }
        else{
            parents[a] = b;
        }
    }
}