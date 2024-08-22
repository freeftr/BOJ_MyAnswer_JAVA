import java.util.*;
import java.io.*;
public class Main {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1,0,1};
    static int[] dy = {1,1,1};
    static boolean[][] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++){
            String s = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < R; i++){
            dfs(i,0);
        }
        System.out.println(cnt);
    }
    public static boolean dfs(int x, int y){
        for(int i = 0; i < 3; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=R || ny>=C || nx<0 || ny<0)continue;
            if(visited[nx][ny])continue;
            if(map[nx][ny]=='x')continue;
            visited[nx][ny] = true;
            if(ny==C-1){
                cnt++;
                return true;
            }
            if(dfs(nx,ny))return true;
        }
        return false;
    }
}