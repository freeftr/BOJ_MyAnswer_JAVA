import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    static boolean[][] visited;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1,0,-1};

    static ArrayList<Cloud> cloud_list = new ArrayList<>();
    public static class Cloud{
        int x;
        int y;
        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cloud_list.add(new Cloud(N,1));
        cloud_list.add(new Cloud(N,2));
        cloud_list.add(new Cloud(N-1,1));
        cloud_list.add(new Cloud(N-1,2));

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            visited = new boolean[N+1][N+1];
            move(d-1,s);
            rain();
            make();
        }

        int ans = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    public static void move(int d, int s){
        for(int i = 0; i < cloud_list.size(); i++){
            int nx = cloud_list.get(i).x;
            int ny = cloud_list.get(i).y;
            for(int j = 0; j < s; j++){
                nx+=dx[d];
                ny+=dy[d];
                if(nx==0)nx=N;
                if(nx==N+1)nx=1;
                if(ny==0)ny=N;
                if(ny==N+1)ny=1;
            }
            cloud_list.get(i).x = nx;
            cloud_list.get(i).y = ny;
        }
    }

    public static void rain() {
        for (int i = 0; i < cloud_list.size(); i++) {
            int x = cloud_list.get(i).x;
            int y = cloud_list.get(i).y;
            visited[x][y] = true;
            map[x][y]++;
        }

        // 물복사 버그 마법
        for (int i = 0; i < cloud_list.size(); i++) {
            int x = cloud_list.get(i).x;
            int y = cloud_list.get(i).y;
            int count = 0;
            if (x + 1 <= N && y + 1 <= N && map[x + 1][y + 1] > 0) count++;
            if (x - 1 > 0 && y + 1 <= N && map[x - 1][y + 1] > 0) count++;
            if (x + 1 <= N && y - 1 > 0 && map[x + 1][y - 1] > 0) count++;
            if (x - 1 > 0 && y - 1 > 0 && map[x - 1][y - 1] > 0) count++;
            map[x][y] += count;
        }
        cloud_list.clear();
    }


    public static void make(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(map[i][j]>=2 && !visited[i][j]){
                    cloud_list.add(new Cloud(i,j));
                    map[i][j]-=2;
                }
            }
        }
    }
}