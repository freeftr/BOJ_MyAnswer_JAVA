import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    static int start, end;
    static int[] dist;
    static boolean[] visited;

    public static class Node implements Comparable<Node>{
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
            return this.cost-o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] prev = new int[n+1];
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(from, to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, start,0));;
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if (visited[now.to])continue;
            visited[now.to] = true;

            for(Node next : graph.get(now.to)){
                if(dist[next.to] > dist[now.to] + next.cost){
                    dist[next.to] = dist[now.to] + next.cost;
                    prev[next.to] = now.to;
                    pq.add(new Node(next.from,next.to,dist[next.to]));
                }
            }
        }
        Stack<Integer> path = new Stack<>();
        int cur = end;
        while(cur!=0){
            path.add(cur);
            cur = prev[cur];
        }
        System.out.println(dist[end]);
        System.out.println(path.size());
        while(!path.isEmpty()){
            System.out.printf(path.pop() + " ");
        }
    }
}