import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static String[] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new String[N];

		for(int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}

		Queue<int[]> q = new LinkedList<>();
		int[][][] dp = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}

		dp[0][0][0] = 1;
		q.add(new int[] {0, 0, 1, 0});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int d = cur[2];
			int wall = cur[3];

			if (x == N-1 && y == M-1) {
				System.out.println(d);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

				if (map[nx].charAt(ny) == '1' && wall == 0) {
					if (dp[nx][ny][1] <= d + 1) continue;
					dp[nx][ny][1] = d + 1;
					q.add(new int[] {nx, ny, d + 1, 1});
				}

				if (map[nx].charAt(ny) == '0') {
					if (dp[nx][ny][wall] <= d + 1) continue;
					dp[nx][ny][wall] = d + 1;
					q.add(new int[] {nx, ny, d + 1, wall});
				}
			}
		}

		// for (int i = 0; i < N; i++) {
		// 	System.out.println();
		// 	for (int j = 0; j < M; j++) {
		// 		System.out.print(dp[i][j][1] + " ");
		// 	}
		// }

		System.out.println(-1);
	}
}
