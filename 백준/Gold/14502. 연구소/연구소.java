import java.io.*;
import java.util.*;
public class Main {
    static int[][] lab;
    static int N, M;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        int max = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //벽 세우기 브루트포스
        for(int x1 = 0; x1 < N; x1++){
            for(int y1 = 0; y1 < M; y1++){
                if(lab[x1][y1]!=0) continue;
                for(int x2 = 0; x2 < N; x2++){
                    for(int y2 = 0; y2 < M; y2++){
                        if(lab[x2][y2]!=0) continue;
                        for(int x3 = 0; x3 < N; x3++){
                            for(int y3 = 0; y3 < M; y3++) {
                                if(lab[x3][y3]!=0) continue;
                                if(x1==x2 && y1==y2) continue;
                                if(x1==x3 && y1==y3) continue;
                                if(x3==x2 && y3==y2) continue;
                                lab[x1][y1] = 1;
                                lab[x2][y2] = 1;
                                lab[x3][y3] = 1;
                                if(max<=bfs()){
                                    max = bfs();
                                }
                                lab[x1][y1] = 0;
                                lab[x2][y2] = 0;
                                lab[x3][y3] = 0;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
    static public int bfs() {
        int[][] temp = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                temp[i][j] = lab[i][j];
                if(lab[i][j]==2) {
                    q.add(new int[]{i, j});
                }
            }
        }
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            for(int i = 0; i < 4; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx>=0 && ny>=0 && nx<N && ny<M){
                    if(temp[nx][ny]==0){
                        temp[nx][ny] = 2;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(temp[i][j]==0){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
