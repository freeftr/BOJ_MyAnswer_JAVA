import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static int ans = 0;

    static HashSet<Character> set;

    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        set = new HashSet<>();
        visited = new boolean[R][C];
        set.add(map[0][0]);
        dfs(0, 0, 1);
        System.out.println(ans);
    }

    static void dfs(int x, int y, int dist){
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
            if(visited[nx][ny]) continue;
            if(set.contains(map[nx][ny])) continue;
            visited[nx][ny] = true;
            set.add(map[nx][ny]);
            dfs(nx,ny,dist+1);
            set.remove(map[nx][ny]);
            visited[nx][ny] = false;
        }

        ans = Math.max(ans, dist);
    }
}