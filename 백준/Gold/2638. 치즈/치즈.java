import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] temp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        temp = new int[N][M];
        visited = new boolean[N][M];
        int time = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(!check()) {
            visited = new boolean[N][M];
            temp = new int[N][M];

            bfs(0, 0);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (temp[i][j] >= 2) {
                        map[i][j] = 0;
                    }
                }
            }
            time++;
        }
        System.out.println(time);
    }

    static void bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, b});
        visited[a][b] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

                if(map[nx][ny] == 1){
                    temp[nx][ny]++;
                }

                if(map[nx][ny] == 0 && !visited[nx][ny]){
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean check(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }
}