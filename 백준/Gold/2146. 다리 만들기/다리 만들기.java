import java.io.*;
import java.util.*;

public class Main {
    static int N, cnt = 0, min;
    static int[][] map;
    static boolean[][] union;
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        union = new boolean[N][N];
        visited = new boolean[N][N];
        min = N*N;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //연합만들기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!union[i][j] && map[i][j]==1){
                    cnt++;
                    union_bfs(i,j);
                    map[i][j] = cnt;
                }
            }
        }
        //다리만들기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j]!=0){
                    visited = new boolean[N][N];
                    min = Math.min(min,make_bridge(i,j));
                }
            }
        }
//        for(int i = 0 ; i < N; i++){
//            System.out.println();
//            for (int j = 0; j < N; j++) {
//                System.out.printf("%d ", map[i][j]);
//            }
//        }
//        System.out.println();
        System.out.println(min);
    }
    //연합 만들기
    public static void union_bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        union[x][y] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int X = cur[0];
            int Y = cur[1];
            for(int i = 0; i < 4; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx>=N || ny>=N || nx<0 || ny<0)continue;
                if(union[nx][ny])continue;
                if(map[nx][ny]==1){
                    union[nx][ny] = true;
                    map[nx][ny] = cnt;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }

    //다리놓기
    public static int make_bridge(int x, int y){
        int union_num = map[x][y];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,0});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int X = cur[0];
            int Y = cur[1];
            int length = cur[2];
            for(int i = 0; i < 4; i++){
                int nx = X+dx[i];
                int ny = Y+dy[i];
                if(nx>=N || ny>=N || nx<0 || ny<0)continue;
                if(visited[nx][ny])continue;
                if(map[nx][ny]!=0 && map[nx][ny]!=union_num){
                    return length;
                }
                if(map[nx][ny] == union_num)continue;
                if(map[nx][ny]==0){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny,length+1});
                }
            }
        }
        return N*N;
    }
}