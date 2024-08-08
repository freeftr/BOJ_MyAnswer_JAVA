import java.util.*;
import java.io.*;
public class Main {
    static int N , M;
    static int[][] paper;
    static boolean[][] visited;
    static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visited[i][j] = true;
                dfs(i,j,1, paper[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(max);
    }
    //빠큐모양 탐색 불가
    public static void dfs(int x, int y, int depth, int sum){
        if(depth==4){
            max = Math.max(max,sum);
            return;
        }
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=N || ny>=M || nx < 0 || ny < 0) continue;
            if(visited[nx][ny])continue;
            if(depth==2){
                visited[nx][ny] = true;
                dfs(x,y,depth+1, sum+paper[nx][ny]);
                visited[nx][ny] = false;
            }
            visited[nx][ny] = true;
            dfs(nx,ny,depth+1, sum+paper[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}
