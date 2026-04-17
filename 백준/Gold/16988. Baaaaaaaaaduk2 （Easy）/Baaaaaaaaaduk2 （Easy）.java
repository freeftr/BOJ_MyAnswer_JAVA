import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	조건
	- 바둑을 2개씩 번갈아 둔다.
	- 죽음 = 그룹 내 빈칸 인접 없음

	요구
	- 현재 판에서 돌을 2개 두어서 죽일 수 있는 최대 개수 구하기

	풀이
	- 2가 AI돌
	- 1이 나의 돌
	1. 빈 칸중 두 개 브루트포스로 선정.
	2. 2에서 bfs돌려서 생사여부 확인
	 */

	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		ArrayList<int[]> pos = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0) {
					pos.add(new int[] {i, j});
				}
			}
		}

		int answer = 0;

		for (int i = 0; i < pos.size() - 1; i++) {
			for (int j = i + 1; j < pos.size(); j++) {
				int[] a = pos.get(i);
				int[] b = pos.get(j);

				map[a[0]][a[1]] = 1;
				map[b[0]][b[1]] = 1;

				int result = bfs();
				answer = Math.max(answer, result);

				map[a[0]][a[1]] = 0;
				map[b[0]][b[1]] = 0;
			}
		}

		System.out.println(answer);
	}

	static int bfs() {
		boolean[][] visited = new boolean[N][M];
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) continue;
				if (map[i][j] == 2) {
					Queue<int[]> q = new ArrayDeque<>();
					q.add(new int[]{i, j});
					visited[i][j] = true;
					boolean flag = true;
					int cnt = 0;

					while(!q.isEmpty()) {
						int[] cur = q.poll();
						int x = cur[0];
						int y = cur[1];
						cnt++;

						for (int[] dir : new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
							int nx = x + dir[0];
							int ny = y + dir[1];

							if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
							if (visited[nx][ny]) continue;

							if (map[nx][ny] == 0) flag = false;

							if (map[nx][ny] == 2) {
								q.add(new int[]{nx, ny});
								visited[nx][ny] = true;
							}
						}
					}

					if (flag) result += cnt;
				}
			}
		}

		return result;
	}
}
