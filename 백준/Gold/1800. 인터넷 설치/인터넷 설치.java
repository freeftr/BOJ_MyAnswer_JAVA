import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, P, K;
	static ArrayList<ArrayList<int[]>> graph;
	/*
	조건
	- 1번은 인터넷 연결되어 있음
	- N번이 연결되도록 해야 함.
	- k개 까지는 연결해줌
	- 나머지 필요한 것 중에서는 가격이 제일 비싼 것만 받음

	요구
	- 원장이 내는 최솟값 구하기

	풀이
	- 다익스트라를 돌리는데 이제 연결했던 선들을 모두 기록을 해야함
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		int left = 0;
		int right = 0;

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});

			right = Math.max(right, c);
		}

		int answer = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(answer);
	}

	static boolean check(int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] cnt = new int[N + 1];
		Arrays.fill(cnt, Integer.MAX_VALUE);
		cnt[1] = 0;

		pq.add(new int[] {1, 0});

		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int v = cur[0];
			int d = cur[1];

			if (cnt[v] < d) continue;

			for (int[] n : graph.get(cur[0])) {
				int nv = n[0];
				int c = n[1];
				int nd = d;

				if (c > k) nd++;

				if (cnt[nv] > nd) {
					cnt[nv] = nd;
					pq.add(new int[] {nv, nd});
				}
			}
		}

		if (cnt[N] > K) return false;
		return true;
	}
}
