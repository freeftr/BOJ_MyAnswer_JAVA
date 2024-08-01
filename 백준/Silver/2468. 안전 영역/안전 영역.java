import java.io.*;
import java.util.*;
public class Main {
    static int N, highest=0;
    static int[][] safe;
    static boolean[][] visited;
    static int height =  0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        safe = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                safe[i][j] = Integer.parseInt(st.nextToken());
                highest = Math.max(highest, safe[i][j]);
            }
        }
        int max= 0;
        for(int i = 0; i <= highest; i++) {
            rain();
            int cnt = 0;
            visited = new boolean[N][N];
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < N; y++) {
                    if(safe[x][y] > 0 && !visited[x][y]){
                        bfs(x,y);
                        cnt++;
                    }
                }
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
    public static void rain() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (safe[i][j] <= height) {
                    safe[i][j] = 0;
                }
            }
        }
        height++;
    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void bfs(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            int X = temp[0];
            int Y = temp[1];
            for(int i = 0; i < 4; i++) {
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(safe[nx][ny] > 0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}
