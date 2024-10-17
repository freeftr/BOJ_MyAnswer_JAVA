import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            int a = Integer.parseInt(st.nextToken());
            union(0,a);
        }

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            graph.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());
                graph.get(i).add(a);
                union(graph.get(i).get(0),a);
            }
        }
//        for (int i = 0; i <= N; i++) {
//            find(i);
//            System.out.println("parent[" + i + "] = " + parent[i]);
//        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for(int j : graph.get(i)) {
                if(find(j)==0) {
                    cnt++;
                    break;
                }
            }
        }

        if(K==0) {
            System.out.println(M);
            System.exit(0);
        }
        System.out.println(M-cnt);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a>b) {
            parent[a] = b;
        }
        else {
            parent[b] = a;
        }
    }

    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
}