import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dist = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			dist[a][b] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i==j) continue;
					if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
		}

		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			int heavy = 0;
			int light = 0;
			for (int j = 1; j <= N; j++) {
				if (dist[i][j]!=Integer.MAX_VALUE) {
					light++;
				}
				if (dist[j][i]!=Integer.MAX_VALUE) {
					heavy++;
				}
			}

			if (heavy > N/2 || light > N/2) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}
}