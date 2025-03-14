import java.util.*;
import java.io.*;

public class Main {

    static int T,N,M,K;
    static int[] parent = new int[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc <= T ; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            for(int i = 0 ; i < N ; i++) {
                parent[i] = i;
            }

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            for(int i = 0 ; i < M ; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(find(a) != find(b)) {
                    union(a,b);
                }
            }

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            sb.append("Scenario " + tc + ":\n");

            for(int i = 0 ; i < K ; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(find(a) == find(b)) {
                    sb.append("1\n");
                }else{
                    sb.append("0\n");
                }
            }sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int find(int v) {
        if(v == parent[v]) return parent[v];

        return parent[v] = find(parent[v]);
    }

    static void union(int a,int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            parent[b] = a;
        }
        else
            parent[a] = b;
    }
}