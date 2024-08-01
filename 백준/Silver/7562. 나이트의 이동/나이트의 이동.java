import java.io.*;
import java.util.*;
public class Main {
    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            int[][] chess = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            bfs(x1,y1,x2,y2,chess,visited);
            ans.append(chess[x2][y2]).append("\n");
        }
        bw.write(ans.toString());
        bw.close();
    }
    public static void bfs(int x, int y, int x2, int y2, int[][] chess, boolean[][] visited){
        int[] dx = {1,2,-1,-2,1,2,-1,-2};
        int[] dy = {2,1,-2,-1,-2,-1,2,1};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            if(X==x2 && Y==y2){
                break;
            }
            for(int i = 0; i < 8; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N)continue;
                if(visited[nx][ny])continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx,ny});
                chess[nx][ny] = chess[X][Y]+1;
            }
        }
    }
}
