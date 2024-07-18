import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] result = new int[T];
		for(int t = 0; t < T; t++) {
			int cnt = 0;
			int M = sc.nextInt();
		 	int N = sc.nextInt();
			int K = sc.nextInt();
			boolean[][] visited = new boolean[N][M];
			int[][] farm = new int[N][M];
			for(int k = 0; k < K; k++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				farm[y][x] = 1;
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(!visited[i][j] && farm[i][j] == 1) {
						cnt++;
						bfs(i, j, visited, farm, N, M, cnt);
					}
				}
			}
			result[t] = cnt;
		}
		for(int t = 0; t < T; t++) {
			System.out.println(result[t]);
		}
	}
	public static void bfs(int x, int y, boolean[][] visited, int[][] farm, int N , int M, int cnt) {
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		Queue<int[]> q = new LinkedList<>();
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int X = arr[0];
			int Y = arr[1];
			for(int i = 0; i < 4; i++) {
				int nx = dx[i] + X;
				int ny = dy[i] + Y;
				if(0 <= nx && nx < N && 0 <= ny && ny < M) {
					if(!visited[nx][ny] && farm[nx][ny] == 1) {
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
		
	}
}
