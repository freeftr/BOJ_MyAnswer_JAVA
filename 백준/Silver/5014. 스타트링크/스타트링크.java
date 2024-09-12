import java.io.*;
import java.util.*;

public class Main {
    static int F,S,G,U,D;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        // 건물 크기
        F = Integer.parseInt(st.nextToken());
        // 강호의 현재 위치
        S = Integer.parseInt(st.nextToken());
        // 목표 층
        G = Integer.parseInt(st.nextToken());
        // 올라가는 버튼 크기
        U = Integer.parseInt(st.nextToken());
        // 내려가는 버튼 크기
        D = Integer.parseInt(st.nextToken());

        int ans = bfs(S);
        if(ans==-1){
            System.out.println("use the stairs");
            System.exit(0);
        }

        System.out.println(ans);
    }

    public static int bfs(int S){
        boolean[] visited = new boolean[F+1];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{S,0});
        visited[S] = true;

        while(!q.isEmpty()){
            int[] arr = q.poll();
            int now = arr[0];
            int dist = arr[1];

            if(now==G){
                return dist;
            }

            // 0 => U
            // 1 => D
            for(int i = 0; i < 2; i++){
                int next = now;
                if(i==0){
                    next+=U;
                }
                else{
                    next-=D;
                }
                if(next<1 || next>F)continue;
                if(visited[next])continue;
                visited[next] = true;
                q.add(new int[]{next,dist+1});
            }
        }
        return -1;
    }
}