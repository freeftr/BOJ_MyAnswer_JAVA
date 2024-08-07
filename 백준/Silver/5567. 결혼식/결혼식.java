import java.io.*;
import java.util.*;
public class Main {
	static int n , m;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		check = new boolean[n+1];
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for(int j = 1; j <= m; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		dfs(1,0);
		System.out.println(cnt);
	}
	static int cnt = 0;
	public static void dfs(int V, int depth) {
		if(depth==1 && !check[V]) {
			check[V]=true;
			cnt++;
		}
		if(depth==2 && V!=1 && !check[V]) {
			cnt++;
			check[V] = true;
			return;
		}
		if(depth==2) {
			return;
		}
		for(int nV: graph.get(V)) {
			if(!visited[nV]) {
				visited[nV] = true;
				dfs(nV, depth+1);
				visited[nV] = false;
			}
		}
	}
}
