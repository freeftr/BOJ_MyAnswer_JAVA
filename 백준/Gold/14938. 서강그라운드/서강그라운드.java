import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, r;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static int[][] dist;
    static int[] count;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        dist = new int[n + 1][n + 1];
        count = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            count[i] = Integer.parseInt(st.nextToken());
            dist[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(l, dist[a][b]);
            dist[b][a] = Math.min(l, dist[b][a]);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int itemCnt = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) continue;
                if (dist[i][j] > m) continue;
                itemCnt += count[j];
            }
            answer = Math.max(answer, itemCnt);
        }

        System.out.println(answer);
    }
}