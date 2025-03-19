import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int[][] cost;
	static int ans = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int robotA = Integer.parseInt(st.nextToken());
		int robotB = Integer.parseInt(st.nextToken());

		cost = new int[N+1][N+1];
		visited = new boolean[N+1];

		for (int i = 0; i <= N; i++){
			graph.add(new ArrayList<>());
		}

		 for (int i = 0; i < N-1; i++) {
			 st = new StringTokenizer(br.readLine());
			 int a = Integer.parseInt(st.nextToken());
			 int b = Integer.parseInt(st.nextToken());

			 graph.get(a).add(b);
			 graph.get(b).add(a);
			 cost[a][b] = Integer.parseInt(st.nextToken());
			 cost[b][a] =cost[a][b];
		 }
		 visited[robotA] = true;
		 dfs(robotA,robotA,robotB, new ArrayList<>());

		System.out.println(ans);
	}

	static void dfs(int v, int robotA, int robotB, ArrayList<Integer> path) {
		if (v==robotB){
			int max = 0;
			int sum = 0;
			for (int a : path){
				max = Math.max(max, a);
				sum += a;
			}
			ans = Math.min(ans, sum-max);
			return;
		}

		for (int nv : graph.get(v)){
			if (visited[nv]) continue;
			visited[nv] = true;
			path.add(cost[v][nv]);
			dfs(nv, robotA, robotB, path);
			path.remove(path.size()-1);
		}
	}
}
