import java.util.*;

public class Main {
    static int n, m;

    static int[] dx = {-1,1,0,0};
    static int[] dy= {0,0,1,-1};
    static long cnt = 0, abcnt = 0;
    static long max = 0;
    public static void bfs(int x, int y, int[][] paper, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];

            for(int i = 0 ; i < 4; i++) {
                int nx = dx[i] + X;
                int ny = dy[i] + Y;
                if(0 <= nx && nx < n && 0 <= ny && ny < m && paper[nx][ny] != 0 && visited[nx][ny] == false) {
                    abcnt++;
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        boolean[][] visited = new boolean[n][m];
        int[][] paper = new int[n][m];

        for(int i = 0 ; i < n; i++) {
            for(int j = 0; j < m; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(visited[i][j] == false && paper[i][j] == 1) {
                    abcnt = 1;
                    cnt++;
                    bfs(i,j,paper, visited);
                    if(max <= abcnt) {
                        max = abcnt;
                    }
                }
            }
        }
        if(cnt == 0){
            abcnt = 0;
        }
        sc.close();
        System.out.println(cnt);
        System.out.println(max);
    }

}
