import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int ans = 0;
    static int cnt = 0;

    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};

    static Fish shark;
    public static class Fish implements Comparable<Fish>{
        int x;
        int y;
        int size;
        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public int compareTo(Fish o) {
            return this.size-o.size;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st =  new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==9){
                    shark = new Fish(i,j,2);
                    map[i][j] = 0;
                }
            }
        }

//        for(int i = 0; i < N; i++){
//            System.out.println();
//            for(int j = 0; j < N; j++){
//                System.out.printf("%d ", map[i][j]);
//            }
//        }
        while(true){
            if(!move()){
                break;
            }
        }
        System.out.println(ans);
    }

    public static boolean move() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{shark.x, shark.y, 0});
        visited[shark.x][shark.y] = true;

        int t_dist = Integer.MAX_VALUE; // 최단 거리 초기화
        int targetX = -1, targetY = -1; // 목표 물고기의 위치 초기화

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];
            int dist = arr[2];

            // 더 긴 거리에 도달하면 탐색 종료
            if (dist > t_dist) break;

            if (map[x][y] < shark.size && map[x][y] != 0) {
                if (dist < t_dist) {
                    // 새로운 최단 거리 물고기 발견
                    t_dist = dist;
                    targetX = x;
                    targetY = y;
                } else if (dist == t_dist) {
                    // 거리가 같은 물고기일 경우
                    if (x < targetX || (x == targetX && y < targetY)) {
                        targetX = x;
                        targetY = y;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] > shark.size) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dist + 1});
            }
        }

        if (t_dist == Integer.MAX_VALUE) {
            return false; // 더 이상 먹을 수 있는 물고기가 없음
        }

        // 목표 물고기를 먹는다
        ans += t_dist;
        shark.x = targetX;
        shark.y = targetY;
        map[targetX][targetY] = 0;
        cnt++;

        if (cnt == shark.size) {
            shark.size++;
            cnt = 0;
        }

        return true;
    }
}