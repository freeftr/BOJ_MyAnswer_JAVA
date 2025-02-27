import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long lcm;
    static boolean[] visited;
    static long[] answer;

    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        answer = new long[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        lcm = 1;

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b,p,q});
            graph.get(b).add(new int[]{a,q,p});

            lcm *= (p*q/gcd(p,q));
        }

        answer[0] = lcm;

        dfs(0);

        long gcd = answer[0];

        for (int i = 1; i < N; i++) {
            gcd = gcd(answer[i], gcd);
        }
        for (int i = 0; i < N; i++) {
            answer[i] = answer[i] / gcd;
            System.out.print(answer[i] +" ");
        }
    }

    static void dfs(int v){
        visited[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int[] cur = graph.get(v).get(i);
            if (!visited[cur[0]]) {
                answer[cur[0]] = answer[v] * cur[2]/cur[1];
                dfs(cur[0]);
            }
        }
    }

    static long lcm(long a, long b){
        return a * b / gcd(a,b);
    }

    static long gcd(long a, long b){
        if (b==0){
            return a;
        }
        return gcd(b,a%b);
    }
}