import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, P;
    static int[][] map;

    static int[] pwr;
    static boolean check;
    static Queue<int[]>[] q;

    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        q = new Queue[P+1];
        for (int i = 1; i <= P; i++) {
            q[i] = new LinkedList<>();
        }

        pwr = new int[P+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            pwr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if(s.charAt(j) == '.') map[i][j] = 0;
                else if(s.charAt(j) == '#') map[i][j] = -1;
                else{
                    map[i][j] = Integer.parseInt(s.charAt(j)+"");
                    q[map[i][j]].add(new int[]{i,j,0});
                }
            }
        }

        while(true){
            // 영역 별 성 넣기
            check = true;

            for (int i = 1; i <= P; i++) {
                if(q[i].isEmpty())continue;
                bfs(i);
            }

            if(check) break;
        }

        int[] ans = new int[P+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] > 0) ans[map[i][j]]++;
            }
        }

        for(int i = 1; i <= P; i++){
            System.out.printf(ans[i] + " ");
        }
    }

    static void bfs(int a){
        Queue<int[]> nq = new LinkedList<>();
        while(!q[a].isEmpty()){
            int[] arr = q[a].poll();
            int x = arr[0], y = arr[1];
            int range = arr[2];

            if(range == pwr[a]){
                nq.offer(new int[]{x, y, 0});
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] != 0) continue;
                map[nx][ny] = a;
                check = false;
                q[a].add(new int[]{nx, ny, range + 1});
            }
        }

        q[a] = nq;
    }
}