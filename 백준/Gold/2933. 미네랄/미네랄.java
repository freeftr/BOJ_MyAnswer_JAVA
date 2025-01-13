import java.io.*;
import java.util.*;

public class Main {

    //한번에 클러스터가 두개 생길 일은 없다!!!
    //=> 한번에 한개블록만 제거하기 때문
    static int R, C;
    static char[][] map;
    static int N, H;

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<int[]> downList = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = R - 1; i >= 0; i--) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            H = Integer.parseInt(st.nextToken());
            visited = new boolean[R][C];
            destroy(i, H);
            findCluster();
            down();

//            print();
//            System.out.println();
        }

        print();
    }

    private static void print() {
        for (int i = R-1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    // 창던지는 로직
    static void destroy(int turn, int height) {
        if(turn%2==1) {
            for (int i = 0; i < C; i++){
                if(map[height-1][i]=='x') {
                    map[height-1][i]='.';
                    break;
                }
            }
        }
        if(turn%2==0) {
            for (int i = C-1; i >= 0; i--){
                if(map[height-1][i]=='x') {
                    map[height-1][i]='.';
                    break;
                }
            }
        }
    }
    // 클러스터 찾는 로직
    static void findCluster(){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < C; i++){
            if(map[0][i]=='x') {
                q.add(new int[]{0,i});
                visited[0][i] = true;
            }
        }

        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
                if(map[nx][ny]=='.') continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        // 클러스터 좌표 추가해주기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]=='x' && !visited[i][j]) {
                    downList.add(new int[]{i,j});
                }
            }
        }
    }

    static void down() {
        int min = Integer.MAX_VALUE;
        for(int[] cluster : downList) {
            int x = cluster[0];
            int y = cluster[1];
            int cnt = 0;
            while(x>=0) {
                x--;
                cnt++;
                if(x<0 || map[x][y]=='x' && visited[x][y]) {
                    min = Math.min(min, cnt);
                }
            }
        }
        if(min==Integer.MAX_VALUE) {
            return;
        }
        for(int[] cluster : downList) {
            map[cluster[0]][cluster[1]]='.';
            map[cluster[0]-min+1][cluster[1]]='x';
        }
        downList.clear();
    }
}