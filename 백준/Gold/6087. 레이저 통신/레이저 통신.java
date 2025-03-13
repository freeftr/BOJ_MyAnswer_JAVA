import java.io.*;
import java.util.*;

public class Main {

    static int W,H;
    static char[][] map;
    static int[][][] dist;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sx=-1, sy=-1;
        int ex=-1, ey=-1;

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        // 방향 별 거울 개수
        dist = new int[H][W][4];

        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j]=='C' && sx==-1){
                    sx = i;
                    sy = j;
                }
                if (map[i][j]=='C' && sx!=-1){
                    ex = i;
                    ey = j;
                }

                for (int k = 0; k < 4; k++) {
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        System.out.println(bfs(sx, sy, ex, ey));
    }

    static int bfs(int sx, int sy, int ex, int ey){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[3]-b[3]);

        // 처음에 네 방향 넣어주기
        for (int i = 0; i < 4; i++) {
            pq.add(new int[]{sx,sy,i,0});
            dist[sx][sy][i] = 0;
        }

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int cnt = cur[3];

            if (x==ex && y==ey){
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int newCnt = cnt;

                if (i!=dir){
                    newCnt++;
                }


                if (nx<0 || ny<0 || nx>=H || ny>=W) continue;
                if (map[nx][ny]=='*') continue;

                if (newCnt < dist[nx][ny][i]){
                    pq.offer(new int[]{nx,ny,i,newCnt});
                    dist[nx][ny][i] = newCnt;
                }
            }
        }
        return -1;
    }
}
