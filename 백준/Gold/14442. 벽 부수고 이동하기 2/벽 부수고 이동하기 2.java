import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N+1][M+1];

        for(int i = 1; i <= N; i++){
            String s = br.readLine();
            for(int j = 1; j <= M; j++){
                map[i][j] = s.charAt(j-1);
            }
        }

        visited = new boolean[K+1][N+1][M+1];
        System.out.println(bfs());
    }

    public static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1,1,0,1});
        visited[0][1][1] = true;

        while(!q.isEmpty()){
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];
            int wall = arr[2];
            int dist = arr[3];

            if(x==N && y==M){
                return dist;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<1 || ny<1 || nx>N || ny>M)continue;
                if(visited[wall][nx][ny])continue;
                if(map[nx][ny]=='1'){
                    if(wall==K)continue;
                    if(!visited[wall+1][nx][ny])
                    q.add(new int[]{nx,ny,wall+1,dist+1});
                    visited[wall+1][nx][ny] = true;
                }
                else{
                    q.add(new int[]{nx,ny,wall,dist+1});
                    visited[wall][nx][ny] = true;
                }
            }
        }
        return -1;
    }
}