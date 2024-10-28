import java.io.*;
import java.util.*;

public class Main {

    static int V, E;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V+1][V+1];

        for(int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {
                if(i==j){
                    dist[i][j] = 0;
                    continue;
                }
                dist[i][j] = 1000000000;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], c);
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int ans = 1000000000;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if(i==j)continue;

                if(dist[i][j]!=1000000000 && dist[j][i]!=1000000000){
                    ans = Math.min(ans, dist[i][j] + dist[j][i]);
                }
            }
        }

        if(ans==1000000000) {
            System.out.println(-1);
            System.exit(0);
        }
        else{
            System.out.println(ans);
        }
    }
}