import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent, route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N];
		route = new int[M];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a==1){
					union(i,j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			route[i] = Integer.parseInt(st.nextToken())-1;
		}

		boolean flag = false;
		for (int i = 1; i < M; i++) {
			if (find(route[i]) != find(route[0])) {
				flag = true;
				break;
			}
		}

		if (flag) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
	}

	static void union(int a, int b){
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}

	static int find(int v){
		if (parent[v] == v){
			return v;
		}
		return parent[v] = find(parent[v]);
	}
}
