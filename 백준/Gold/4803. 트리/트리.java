import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int n, m;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static boolean isTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int idx = 0;

		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0) break;

			idx++;
			graph = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			visited = new boolean[n + 1];
			int treeCount = 0;

			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					isTree = true;
					dfs(i, 0);
					if (isTree) treeCount++;
				}
			}

			if (treeCount == 0) {
				sb.append("Case ").append(idx).append(": No trees.\n");
			} else if (treeCount == 1) {
				sb.append("Case ").append(idx).append(": There is one tree.\n");
			} else {
				sb.append("Case ").append(idx).append(": A forest of ").append(treeCount).append(" trees.\n");
			}
		}

		System.out.print(sb);
	}

	static void dfs(int v, int parent) {
		visited[v] = true;

		for (int nv : graph.get(v)) {
			if (nv == parent) continue;
			if (visited[nv]) {
				isTree = false;  
			} else {
				dfs(nv, v);
			}
		}
	}
}
