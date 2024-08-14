import java.util.*;
import java.io.*;
public class Main {
    static int N,L,R;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] union;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean check;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        while(true){
            check = true;
            union = new boolean[N][N];

            visited = new boolean[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0 ; j < N; j++) {
                    if(!visited[i][j]){
                        bfs(i,j,1);
                    }
                }
            }
//            System.out.println();
//            for(int i = 0; i < N; i++){
//                for(int j = 0; j < N; j++){
//                    System.out.printf("%2d ", map[i][j]);
//                }
//                System.out.println();
//            }
            if(!check){
                ans++;
            }
            else{
                System.out.println(ans);
                System.exit(0);
            }
        }
    }
    public static void bfs(int x, int y,int depth){
        Queue<int[]> q = new LinkedList<>();
        ArrayList<Node> group = new ArrayList<>();
        q.add(new int[]{x,y});
        visited[x][y] = true;
        union[x][y] = true;
        int sum = 0;
        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            sum+=map[X][Y];
            group.add(new Node(X,Y));
            for (int i = 0; i < 4; i++) {
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if ( nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
                if (visited[nx][ny]) continue;
                if (L <= Math.abs(map[X][Y] - map[nx][ny]) && Math.abs(map[X][Y] - map[nx][ny]) <= R) {
                    visited[nx][ny] = true;
                    union[nx][ny] = true;
                    check = false;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        if (!group.isEmpty()) {
            int newPopulation = sum / group.size();
            for (Node node : group) {
                map[node.x][node.y] = newPopulation;
            }
        }

    }
    public static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
