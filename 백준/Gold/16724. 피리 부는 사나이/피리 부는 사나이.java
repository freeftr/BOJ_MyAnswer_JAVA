import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[] parent;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        parent = new int[N*M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                parent[i*M+j] = i*M+j;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int dir = 0;
                if(map[i][j]=='U'){
                    dir = 0;
                }
                else if(map[i][j]=='D'){
                    dir = 1;
                }
                else if(map[i][j]=='L'){
                    dir = 2;
                }
                else if(map[i][j]=='R'){
                    dir = 3;
                }

                int nx = i + dx[dir];
                int ny = j + dy[dir];

                union(parent[i*M+j], parent[nx*M+ny]);
            }
        }

        int ans = 0;
        for (int i = 0; i < parent.length; i++) {
            if(parent[i]==i){
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a!=b){
            parent[b]=a;
        }
    }

    static int find(int v){
        if(parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }
}