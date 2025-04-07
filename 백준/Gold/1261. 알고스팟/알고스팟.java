import java.io.*;
import java.util.*;

public class Main {

	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];

		for (int i = 1; i <= N; i++) {
			String[] s = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(s[j-1]);
			}
		}

		bfs();

		System.out.println(answer);
	}

	static void bfs(){
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
		pq.offer(new int[]{1,1,0});
		visited[1][1] = true;

		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			int x = cur[0];
			int y = cur[1];
			int cost = cur[2];
			
			if (x == N && y == M) {
				answer = Math.min(answer, cost);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx<1 || ny<1 || nx>N || ny>M) continue;
				if (visited[nx][ny]) continue;

				if (map[nx][ny] == 0) {
					visited[nx][ny] = true;
					pq.offer(new int[]{nx, ny, cost});
				}

				if (map[nx][ny] == 1){
					visited[nx][ny] = true;
					pq.offer(new int[]{nx, ny, cost + 1});
				}
			}
		}
	}
}
