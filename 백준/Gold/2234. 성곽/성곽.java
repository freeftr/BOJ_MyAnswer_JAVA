import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] room;
	static int[][] wall;
	static int[][] team;
	static boolean[][] visited;
	static int idx = 1;
	static int maxRoomSize = 0;
	static int removeWall = 0;
	static int[] dx = {0, -1, 0, 1}; 
	static int[] dy = {-1, 0, 1, 0};
	static int[] dir = {1, 2, 4, 8};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		wall = new int[M][N];
		visited = new boolean[M][N];
		room = new int[M][N];
		team = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				wall[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int roomCnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					roomCnt++;
					bfs(i, j);
				}
			}
		}
		findMaxAfterWallRemove();

		System.out.println(roomCnt);
		System.out.println(maxRoomSize);
		System.out.println(removeWall);
	}

	static void bfs(int a, int b) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		ArrayList<int[]> sizeList = new ArrayList<>();
		int size = 0;
		dq.offer(new int[]{a, b});
		visited[a][b] = true;

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int x = cur[0];
			int y = cur[1];
			sizeList.add(new int[]{x, y});
			team[x][y] = idx;
			size++;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
				if (visited[nx][ny]) continue;
				if ((wall[x][y] & dir[i]) != 0) continue;

				visited[nx][ny] = true;
				dq.offer(new int[]{nx, ny});
			}
		}

		for (int i = 0; i < sizeList.size(); i++) {
			room[sizeList.get(i)[0]][sizeList.get(i)[1]] = size;
		}

		maxRoomSize = Math.max(maxRoomSize, size);
		idx++;
	}

	static void findMaxAfterWallRemove() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < 4; d++) {
					int ni = i + dx[d];
					int nj = j + dy[d];

					if (ni < 0 || nj < 0 || ni >= M || nj >= N) continue;
					if (team[ni][nj] == team[i][j]) continue;
					if ((wall[i][j] & dir[d]) != 0) {
						int newSize = room[i][j] + room[ni][nj];
						removeWall = Math.max(removeWall, newSize);
					}
				}
			}
		}
	}
}
