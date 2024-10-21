import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static char[][] map;

    static boolean[][][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        bfs();

        for (int i = 0; i < n*n; i++) {
            if(visited[n-1][n-1][i]){
                System.out.println(i);
                break;
            }
        }
    }

    static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[n][n][n*n];
        q.add(new int[]{0,0,0});
        visited[0][0][0] = true;

        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0],y = cur[1];
            int black = cur[2];

            if(min<black) continue;
            if(x==n-1 && y==n-1){
                min = Math.min(min,black);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
                if(visited[nx][ny][black])continue;
                if(map[nx][ny]=='1'){
                    visited[nx][ny][black] = true;
                    q.offer(new int[]{nx,ny,black});
                }
                else if(map[nx][ny]=='0'){
                    visited[nx][ny][black] = true;
                    q.offer(new int[]{nx,ny,black+1});
                }
            }
        }
    }
}