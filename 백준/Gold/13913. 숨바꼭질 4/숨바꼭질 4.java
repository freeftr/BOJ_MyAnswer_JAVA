import java.io.*;
import java.util.*;
public class Main {
    static int N, K;
    static int[] road;
    static boolean[] visited;
    static int[] root;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        road = new int[200000];
        visited = new boolean[200000];
        root=new int[200000];
        bfs();
        System.out.println(sb.toString());
    }
    public static void bfs() {
        int[] dx = {1,-1,0};
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur==K){
                sb.append(road[cur]).append("\n");
                Deque<Integer> dq = new LinkedList<>();
                int idx = K;
                while(idx!=N){
                    dq.addFirst(root[idx]);
                    idx=root[idx];
                }
                for(int i : dq){
                    sb.append(i+" ");
                }
                sb.append(K);
                break;
            }
            for(int i = 0; i < 3 ; i++){
                int nx = cur+dx[i];
                if(i==2){
                    nx=2*cur;
                }
                if(nx<0||nx>100001)continue;
                if(!visited[nx]){
                    q.offer(nx);
                    visited[nx]=true;
                    road[nx] = road[cur] + 1;
                    root[nx]=cur;
                }
            }
        }
    }
}
