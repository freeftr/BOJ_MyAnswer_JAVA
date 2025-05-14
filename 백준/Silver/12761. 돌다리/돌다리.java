import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int A, B, N, M;
	static int[] dx = {1,-1,1,1,1,1,1,1};
	static int answer = Integer.MAX_VALUE;
	static int[] visited = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		bfs();

		System.out.println(answer);
	}

	static void bfs() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[]{N,0});
		visited[N] = 0;
		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int x = arr[0];
			int dist = arr[1];

			if (x == M) {
				answer = Math.min(answer, dist);
				return;
			}

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];

				if (i == 2) {
					nx = x * A;
				}

				if (i == 3) {
					nx = x * B;
				}

				if (i == 4) {
					nx = x + A;
				}

				if (i == 5) {
					nx = x - A;
				}

				if (i == 6) {
					nx = x + B;
				}

				if (i == 7) {
					nx = x - B;
				}

				if (nx < 0 || nx > 100000) continue;
				if (visited[nx] != 0) {
					continue;
				}
				visited[nx] = dist + 1;
				q.add(new int[]{nx, dist + 1});
			}
		}
	}
}
