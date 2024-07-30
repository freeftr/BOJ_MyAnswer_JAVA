import java.io.*;
import java.util.*;
public class Main {
    static int L,R,C;
    static char[][][] building;
    static boolean[][][] visited;
    static boolean check = false;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            check = false;
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0 && R == 0 && C == 0){
                bw.write(ans.toString());
                bw.close();
                break;
            }
            int a=0,b=0,c=0;
            visited = new boolean[L][R][C];
            building = new char[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = s.charAt(k);
                        if(building[i][j][k] == 'S') {
                            a=i;
                            b=j;
                            c=k;
                        }
                    }
                }
                br.readLine();
            }
            bfs(a,b,c);
        }
    }
    static int[] dl = {1,-1,0,0,0,0};
    static int[] dr = {0,0,1,-1,0,0};
    static int[] dc = {0,0,0,0,-1,1};
    static void bfs(int a, int b, int z){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a,b,z,0});
        visited[a][b][z] = true;
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int l = arr[0];
            int r = arr[1];
            int c = arr[2];
            int cnt = arr[3];
            if(building[l][r][c] == 'E') {
                ans.append("Escaped in " + cnt + " minute(s).\n");
                check = true;
                return;
            };
            for(int i = 0; i < 6; i++){
                int nl = l+dl[i];
                int nr = r+dr[i];
                int nc = c+dc[i];
                if(nl<0 || nr<0 || nc<0) continue;
                if(nl>=L || nr>=R || nc>=C) continue;
                if(!visited[nl][nr][nc] && building[nl][nr][nc] != '#') {
                    visited[nl][nr][nc] = true;
                    q.add(new int[]{nl, nr, nc, cnt + 1});
                }

            }
        }
        ans.append("Trapped!\n");
    }
}
