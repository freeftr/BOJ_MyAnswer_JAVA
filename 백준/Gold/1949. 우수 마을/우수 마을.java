import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> town = new ArrayList<>();
    static int[] population;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //  트리 구조 => 비순환 그래프
        //  우수마을
        //  우수마을로 선정된 마을 주민 수의 총 합을 최대로 해야한다.
        //  내가 우수마을이 아니야? 주변에 우수마을 하나는 있어야 해
        //  내가 우수마을이야? 주변은 다 우수마을이 아니야
        //  부모가 우수마을이면 자식은 우수마을이 다 아니다.
        //  부모가 우수마을이 아니면 자식은 둘 다 될 수 있다.

        N = Integer.parseInt(br.readLine());

        //  초기화
        for(int i = 0; i <= N; i++){
            town.add(new ArrayList<>());
        }
        population = new int[N+1];
        //  dp[노드의 개수][0:우마아님 1:우마임] = 최댓값
        dp = new int[N+1][2];
        visited = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            town.get(from).add(to);
            town.get(to).add(from);
        }
        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int v){
        visited[v] = true;
        dp[v][0] = 0;
        dp[v][1] = population[v];
        for(int nv: town.get(v)){
            if(visited[nv])continue;
            dfs(nv);
            //부모가 우마이면 자식은 다 우마가 아니다.
            //부모       자식
            dp[v][1] += dp[nv][0];
            //부모가 우마가 아니면 자식은 둘중 하나 하지만 한명은 우마이여야 한다.
            dp[v][0] += Math.max(dp[nv][0], dp[nv][1]);
        }
    }
}