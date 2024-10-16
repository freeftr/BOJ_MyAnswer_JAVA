import java.io.*;
import java.util.*;
public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i==0 || i==N-1 || j==0 || j==M-1) {
                    map[i][j] = -1;
                }
            }
        }

        int ans=0;
        int time = 0;
        int cnt = 0;

        q.add(new int[]{0,0});
        while(true) {
            cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j]==1) {
                        cnt++;
                    }
                    if(map[i][j]==-2) {
                        map[i][j]=0;
                    }
                }
            }
            if(cnt==0) {
                break;
            }

            time++;

            bfs();



            ans = cnt;
        }

        System.out.println(time + "\n" + ans);

    }

    static void bfs() {
        Queue<int[]> nq = new LinkedList<>();
        visited = new boolean[N][M];

        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];


            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny<0 || nx>N-1 || ny>M-1)continue;
                if(visited[nx][ny])continue;

                if(map[nx][ny]==1) {
                    nq.add(new int[]{nx,ny});
                    map[nx][ny]=-2;
                }

                if(map[nx][ny]==0||map[nx][ny]==-1) {
                    visited[nx][ny] = true;
                    q.add(new int[] {nx,ny});
                }
            }
        }
        q.addAll(nq);
    }
}