package BOJ1697;
import java.util.*;
import java.math.*;
public class BOJ1697 {
	static int cnt = 0;
	static int[] dx = {1,-1, 0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		boolean[] visited = new boolean[100001];
		int[] road = new int[100001];
		bfs(N, K, visited, road);
		sc.close();
		System.out.println(road[K]);
	}
	
	public static void bfs(int N, int K, boolean[] visited, int[] road) {
		Queue<Integer> q = new LinkedList();
		visited[N] = true;
		q.offer(N);
		while(!q.isEmpty()) {
			int X = q.poll();
			if(X == K) {
				break;
			}
			for(int i = 0; i < 3; i++) {
				int nx = X + dx[i];
				if(i == 2) {
					nx = 2 * X;
				}
				if( 0 <= nx && nx < 100001 && !visited[nx] ) {
					road[nx] = road[X] + 1;
					visited[nx] = true;
					q.offer(nx);
				}
			}
		}
	}
}
