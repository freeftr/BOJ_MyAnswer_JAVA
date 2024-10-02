import java.io.*;
import java.util.*;
public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i],-1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[N-1][M-1]=1;
        dfs(0,0);

        System.out.println(dp[0][0]);
    }

    static int dfs(int x, int y){
        if(dp[x][y]!=-1){
            return dp[x][y];
        }
        if(x==N-1 && y==M-1){
            return 1;
        }
        dp[x][y]=0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>=N || ny>=M)continue;
            if(map[x][y]<=map[nx][ny])continue;
            dp[x][y]+=dfs(nx,ny);
        }
        return dp[x][y];
    }
}