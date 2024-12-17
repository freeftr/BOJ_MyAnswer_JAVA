import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int areaId = 2;
    static HashMap<Integer, Integer> size = new HashMap<>();

    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j]==1){
                    union(i,j, areaId);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0){
                    answer = Math.max(answer, bfs(i,j));
                }
            }
        }
        System.out.println(answer+1);
//        print();
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%d", map[i][j]);
            }
            System.out.println();
        }
    }

    static int bfs(int a, int b){
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;

        for (int i = 0; i < 4; i++) {
            int nx = a + dx[i];
            int ny = b + dy[i];

            if (nx<0 || ny<0 || nx>=N || ny>=M) continue;

            if (map[nx][ny]>1 && !set.contains(map[nx][ny])) {
                set.add(map[nx][ny]);
                sum+=size.get(map[nx][ny]);
            }
        }

        return sum;
    }

    static void union(int a, int b, int id){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a,b});
        map[a][b] = id;
        visited[a][b] = true;
        int cnt = 1;

        while (!q.isEmpty()){
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if (visited[nx][ny]) continue;

                if(map[nx][ny]==1){
                    map[nx][ny] = id;
                    q.add(new int[]{nx,ny});
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }
        areaId++;
        size.put(areaId-1, cnt);
    }
}