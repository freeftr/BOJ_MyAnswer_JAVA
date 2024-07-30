import java.io.*;
import java.util.*;
public class Main {
    static int[][] map;
    static int n, cnt=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n+2][n+2];
        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        dfs(1,2,1);
        System.out.println(cnt);
    }
    static public void dfs(int x, int y, int dir){
        if(x==n && y==n){
            cnt++;
            return;
        }
        if(x<=n && y<=n){
            if(dir == 1){
                if(map[x][y+1] == 0 && y+1<=n){
                    dfs(x,y+1,1);
                }
                if(map[x][y+1]==0 && map[x+1][y]==0 && map[x+1][y+1] == 0 && x+1<=n && y+1<=n){
                    dfs(x+1,y+1,2);
                }
            }
            if(dir == 2){
                if(map[x][y+1] == 0 && y+1<=n){
                    dfs(x,y+1,1);
                }
                if(map[x+1][y] == 0 && x+1<=n){
                    dfs(x+1,y,3);
                }
                if(map[x][y+1]==0 && map[x+1][y]==0 && map[x+1][y+1] == 0 && x+1<=n && y+1<=n){
                    dfs(x+1,y+1,2);
                }
            }
            if(dir == 3){
                if(map[x+1][y] == 0 && x+1<=n){
                    dfs(x+1,y,3);
                }
                if(map[x][y+1]==0 && map[x+1][y]==0 && map[x+1][y+1] == 0 && x+1<=n && y+1<=n){
                    dfs(x+1,y+1,2);
                }
            }
        }
    }
}