import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][10];

		Arrays.fill(dp[0], 1);
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				int sum = 0;
				for (int a = j; a < 10; a++){
					sum += dp[i-1][a];
				}
				dp[i][j] = sum%10007;
			}
		}

		System.out.println(dp[N][0]);
	}
}
