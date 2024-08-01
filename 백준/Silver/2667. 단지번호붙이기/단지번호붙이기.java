import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> ans;
    static int cnt=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }
        ans = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j]==1 && !visited[i][j]){
                    house=0;
                    cnt++;
                    bfs(i,j);
                }
            }
        }
        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        sb.append(cnt+"\n");
        for(int i : ans){
            sb.append(i).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int house=0;
    public static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[]{x,y});
        while(!q.isEmpty()){
            house++;
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            for(int i = 0; i < 4; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 1){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        ans.add(house);
    }
}
