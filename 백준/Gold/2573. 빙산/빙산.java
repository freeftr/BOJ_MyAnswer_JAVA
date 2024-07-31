import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[][] sea;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sea = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                sea[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;
        while(true){
            year++;
            int cnt = 0;
            melt();
            boolean[][] visited = new boolean[N][M];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(sea[i][j] > 0 && !visited[i][j]){
                        bfs(i,j, visited);
                        cnt++;
                    }
                }
            }
            if(!check()){
                System.out.println(0);
                break;
            }
            if(cnt>=2){
                System.out.println(year);
                break;
            }
        }
    }
    public static void melt(){
        int[][] zeroice = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                int zero=0;
                if(i + 1 < N && sea[i+1][j] <= 0){
                    zero++;
                }
                if(j + 1 < M && sea[i][j+1] <= 0){
                    zero++;
                }
                if(i - 1 >= 0 && sea[i-1][j] <= 0){
                    zero++;
                }
                if(j - 1 >= 0 && sea[i][j-1] <= 0){
                    zero++;
                }
                zeroice[i][j] = zero;
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sea[i][j] -= zeroice[i][j];
            }
        }
    }
    public static boolean check(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(sea[i][j] > 0){
                    return true;
                }
            }
        }
        return false;
    }
    public static void bfs(int x, int y, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        q.add(new int[]{x, y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            for(int i = 0; i < 4; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx>=0 && ny>=0 && nx < N && ny < M && !visited[nx][ny] &&sea[nx][ny] > 0){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}

