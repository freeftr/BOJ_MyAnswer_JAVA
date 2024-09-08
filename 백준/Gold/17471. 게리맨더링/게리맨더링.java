import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] population;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    static int sum = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        population = new int[N+1];

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            population[i] = Integer.parseInt(st.nextToken());
            sum+=population[i];
        }

        ans = sum;

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j = 0; j < m; j++){
                int b = Integer.parseInt(st.nextToken());
                graph.get(i).add(b);
            }
        }

        for(int i = 1; i <= N/2; i++){
            ArrayDeque<Integer> dq1 = new ArrayDeque<>();
            dfs(0,dq1,i,1);
        }
        if(ans==sum){
            System.out.println(-1);
            System.exit(0);
        }

        System.out.println(ans);
    }

    public static void dfs(int depth, ArrayDeque<Integer> dq1, int r, int start){
        if(depth==r){
            ArrayDeque<Integer> dq2 = new ArrayDeque<>();

            for(int i = 1; i <= N; i++){
                if(!dq1.contains(i)){
                    dq2.add(i);
                }
            }

            if(bfs(dq1) && bfs(dq2)){
                int sum1 = 0;
                int sum2 = 0;
                for(int i : dq1){
                    sum1+=population[i];
                }
                for(int i : dq2){
                    sum2+=population[i];
                }

                if(ans>Math.abs(sum1-sum2)){
                    ans = Math.abs(sum1-sum2);
                }
            }

            return;
        }


        for(int i = start; i <= N; i++){
            dq1.addLast(i);
            dfs(depth + 1, dq1, r, i +1 );
            dq1.removeLast();
        }
    }

    public static boolean bfs(ArrayDeque<Integer> dq){
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 1;

        visited[dq.peekFirst()] = true;
        q.add(dq.peekFirst());

        while(!q.isEmpty()){
            int v = q.poll();
            for(int nv : graph.get(v)){
                if(!dq.contains(nv))continue;
                if(!visited[nv]){
                    q.add(nv);
                    visited[nv] = true;
                    cnt++;
                }
            }
        }

        if(cnt == dq.size()){
            return true;
        }
        return false;
    }
}