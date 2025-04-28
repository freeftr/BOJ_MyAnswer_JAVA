import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		int[] SCV = new int[3];
		dp = new int[100][100][100];
		Arrays.fill(SCV, 0);

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++){
			SCV[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dfs(SCV));
	}

	static int dfs(int[] SCV) {

		int a = Math.max(SCV[0], 0);
		int b = Math.max(SCV[1], 0);
		int c = Math.max(SCV[2], 0);

		if (a == 0 && b == 0 && c == 0) {
			return 0;
		}

		if (dp[a][b][c] != Integer.MAX_VALUE) {
			return dp[a][b][c];
		}

		dp[a][b][c] = Math.min(dp[a][b][c], 1 + dfs(new int[]{a-9,b-1,c-3}));
		dp[a][b][c] = Math.min(dp[a][b][c], 1 + dfs(new int[]{a-9,b-3,c-1}));
		dp[a][b][c] = Math.min(dp[a][b][c], 1 + dfs(new int[]{a-3,b-1,c-9}));
		dp[a][b][c] = Math.min(dp[a][b][c], 1 + dfs(new int[]{a-3,b-9,c-1}));
		dp[a][b][c] = Math.min(dp[a][b][c], 1 + dfs(new int[]{a-1,b-3,c-9}));
		dp[a][b][c] = Math.min(dp[a][b][c], 1 + dfs(new int[]{a-1,b-9,c-3}));

		return dp[a][b][c];
	}
}
