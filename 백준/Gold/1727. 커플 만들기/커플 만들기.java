import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[] male;
	static int[] female;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		male = new int[n];
		female = new int[m]; 
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			male[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			female[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(male);
		Arrays.sort(female);

		int[][] dp = new int[n + 1][m + 1];

		dp[0][0] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j - 1] + Math.abs(male[i - 1] - female[j - 1]);

				if (i > j) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
				}
				if (i < j) {
					dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
				}
			}
		}

		System.out.println(dp[n][m]);
	}
}
