import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(N==K){
            System.out.println(0);
            System.exit(0);
        }
        visited = new boolean[2][500002];
        bfs();
    }
    public static void bfs(){
        int[] dx = {1,-1,0};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N,0,K});
        visited[0][N]= true;
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int nowN = arr[0];
            int cnt = arr[1];
            int k = arr[2];

            if(k>500000){
                System.out.println(-1);
                System.exit(0);
            }
            if(visited[cnt%2][k]){
                System.out.println(cnt);
                System.exit(0);
            }

            for(int i = 0; i < 3; i++){
                int newN = nowN + dx[i];
                if(i==2){
                    newN = nowN*2;
                }
                if(newN<0 || newN>500000)continue;
                if(!visited[(cnt+1)%2][newN]){
                    q.add(new int[]{newN,cnt+1,k+cnt+1});
                    visited[(cnt+1)%2][newN] = true;
                }
            }
        }
    }
}