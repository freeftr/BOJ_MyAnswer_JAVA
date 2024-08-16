import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }
        bfs();
    }
    public static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1,0});
        visited[0][0][0] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];
            int check = cur[3];
            if(x==N-1 && y==M-1){
                System.out.println(cnt);
                System.exit(0);
                return;
            }
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=N || ny>=M || nx<0 || ny<0)continue;
                if(visited[nx][ny][check])continue;
                if(map[nx][ny] == 0){
                    visited[nx][ny][check]=true;
                    q.add(new int[]{nx,ny,cnt+1,check});

                }
                else if(map[nx][ny] == 1){
                    if(check==0){
                        check++;
                        visited[nx][ny][check]=true;
                        q.add(new int[]{nx,ny,cnt+1,check});
                        check--;
                    }
                }
            }
        }
        System.out.println(-1);
        System.exit(0);
    }
}