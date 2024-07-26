import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] road;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        road = new int[100001];
        visited = new boolean[100001];
        bfs(N);
        System.out.println(road[K]);;
    }
    static void bfs(int n) {
        int[] dx = {-1, 0, 1};
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = true;
        while(!q.isEmpty()) {
            int X = q.poll();
            if(X == K){
                break;
            }
            for(int i = 0; i < 3; i++) {
                int nx = X + dx[i];
                if(i == 1){
                    nx = X*2;
                    if(0<=nx && nx < 100001 && !visited[nx]){
                        q.add(nx);
                        road[nx] = road[X];
                        visited[nx] = true;
                    }
                }
                else{
                    if(0<=nx && nx < 100001 && !visited[nx]) {
                        q.add(nx);
                        road[nx] = road[X] + 1;
                        visited[nx] = true;
                    }
                }
            }
        }
    }
}
