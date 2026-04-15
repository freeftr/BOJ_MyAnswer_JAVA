import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			pq.add(new int[] {a, b, c});
		}

		int cnt = 0;
		int sum = 0;
		int max = 0;

		while(!pq.isEmpty()) {
			int[] cur = pq.poll();

			if(find(cur[0]) != find(cur[1])) {
				union(cur[0], cur[1]);
				sum += cur[2];
				max = Math.max(max, cur[2]);
				cnt++;
			}
		}

		System.out.println(sum - max);
	}

	static int find(int v) {
		if (v == parent[v]) return v;
		return parent[v] = find(parent[v]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
}

/*
조건
- N개의 집, M개의 길
- 각 길마다 길을 유지하는데 드는 유지비가 있음.
- 마을을 분리한다.

요구
- 길들을 모두 없애고 나머지 길의 유지비 합을 최소로
 */