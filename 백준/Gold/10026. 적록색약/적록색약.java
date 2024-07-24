import java.io.*;
import java.util.*;
public class Main {
	static char[][] board;
	static int N;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int Ncnt = 0, Scnt = 0;
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		//일반인 구역 구하기 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					Ncnt++;
					bfs(i, j);
				}
			}
		}
		visited = new boolean[N][N];
		//색약 구역
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 'R') {
					board[i][j] = 'G';
				}
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					Scnt++;
					bfs(i, j);
				}
			}
		}
		bw.write(Ncnt + " " + Scnt);
		bw.close();
	}
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,-1,1};
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int X = arr[0];
			int Y = arr[1];
			for(int i = 0; i < 4; i++) {
				int nx = X + dx[i];
				int ny = Y + dy[i];
				if(0 <= nx & nx < N && 0 <= ny && ny < N) {
					if(!visited[nx][ny] && board[X][Y] == board[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
	}
}
