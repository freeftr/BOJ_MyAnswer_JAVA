import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	조건
	- N * M
	- 점프를 뛰면 한겹의 학생들이 없어짐

	요구
	- 몇번 점프해서 범인(#)을 찾냐

	풀이
	- 다익스트라
	 */
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken()) - 1;
		int y1 = Integer.parseInt(st.nextToken()) - 1;
		int x2 = Integer.parseInt(st.nextToken()) - 1;
		int y2 = Integer.parseInt(st.nextToken()) - 1;

		String[] classroom = new String[N];

		for (int i = 0; i < N; i++) {
			classroom[i] = br.readLine();
		}

		int[][] dist = new int[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[]{x1, y1});
		dist[x1][y1] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.pollFirst();
			int x = cur[0];
			int y = cur[1];


			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

				if (classroom[nx].charAt(ny) == '1' || classroom[nx].charAt(ny) == '#') {
					if (dist[nx][ny] > dist[x][y] + 1) {
						dist[nx][ny] = dist[x][y] + 1;
						q.addLast(new int[]{nx, ny});
					}
				}

				if (classroom[nx].charAt(ny) == '0') {
					if (dist[nx][ny] > dist[x][y]) {
						dist[nx][ny] = dist[x][y];
						q.addFirst(new int[]{nx, ny});
					}
				}
			}
		}

		System.out.println(dist[x2][y2]);
	}
}
