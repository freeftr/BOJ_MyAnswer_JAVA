import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	static int N, M, K, cnt = 0;
	static int[][] paper;
	static boolean[][] visited;
	static List<Integer> ans = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		paper = new int[M][N];
		visited = new boolean[M][N];
		K = Integer.parseInt(st.nextToken());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());//1
			int y1 = Integer.parseInt(st.nextToken());//0
			int x2 = Integer.parseInt(st.nextToken());//2
			int y2 = Integer.parseInt(st.nextToken());//5
			for(int x = x1; x < x2; x++){
				for(int y = y1; y < y2; y++){
					paper[y][x] = -1;
				}
			}
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(paper[i][j]==0) {
					cnt = 0;
					visited[i][j]=true;
					bfs(i,j);
					ans.add(cnt);
				}
			}
		}
		Collections.sort(ans);
		System.out.println(ans.size());
		for(int i : ans) {
			System.out.printf("%d ", i);
		}
		
	}
	
	static public void bfs(int x, int y) {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{x,y});
		while(!q.isEmpty()) {
			cnt++;
			int[] arr = q.poll();
			int X = arr[0];
			int Y = arr[1];
			for(int i = 0; i < 4; i++) {
				int nx = X + dx[i];
				int ny = Y + dy[i];
				if(0<=nx && 0<=ny && nx<M && ny<N &&!visited[nx][ny]) {
					if(paper[nx][ny]!=-1) {
						visited[nx][ny] = true;
						paper[nx][ny] = paper[X][Y] + 1;
						q.add(new int[]{nx, ny});
					}
				}
			}
		}
	}
}
