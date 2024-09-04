import java.util.*;
import java.io.*;

public class Main {

    static int[] hx = {-1,-2,-2,-1,1,2,2,1};
    static int[] hy = {-2,-1,1,2,2,1,-1,-2};

    static int[] mx = {0,0,1,-1};
    static int[] my = {1,-1,0,0};

    static int K, W, H;

    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for(int i = 0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[H][W][K+1];

        bfs();
    }

    public static void bfs(){

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0,0,0,0});
        visited[0][0][0] = true;

        while(!q.isEmpty()){

            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            int horse = arr[2];
            int dist = arr[3];

            if(X==H-1 && Y==W-1){
                System.out.println(dist);
                return;
            }

            if(horse<K){

                for(int i = 0; i < 4; i++){

                    int nx = X + mx[i];
                    int ny = Y + my[i];

                    if(nx<0 || ny<0 || nx>=H || ny>=W)continue;
                    if(visited[nx][ny][horse])continue;
                    if(map[nx][ny]==1)continue;

                    visited[nx][ny][horse] = true;
                    q.add(new int[]{nx,ny,horse,dist+1});
                }

                for(int i = 0; i < 8; i++){

                    int nx = X + hx[i];
                    int ny = Y + hy[i];

                    if(nx<0 || ny<0 || nx>=H || ny>=W)continue;
                    if(visited[nx][ny][horse+1])continue;
                    if(map[nx][ny]==1)continue;

                    visited[nx][ny][horse+1] = true;
                    q.add(new int[]{nx,ny,horse+1,dist+1});
                }
            }

            else{
                for(int i = 0; i < 4; i++){

                    int nx = X + mx[i];
                    int ny = Y + my[i];

                    if(nx<0 || ny<0 || nx>=H || ny>=W)continue;
                    if(visited[nx][ny][horse])continue;
                    if(map[nx][ny]==1)continue;

                    visited[nx][ny][horse] = true;
                    q.add(new int[]{nx,ny,horse,dist+1});
                }
            }
        }
        System.out.println(-1);
    }
}