import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] stair = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stair[i] = Integer.parseInt(st.nextToken());
		}
		if(n == 1) {
			System.out.println(stair[0]);
			System.exit(0);
		}
		if(n == 2) {
			System.out.println(stair[0] + stair[1]);
			System.exit(0);
		}
		int[][] dp = new int[n][2];
		dp[0][0] = stair[0];
		dp[0][1] = stair[0];
		dp[1][0] = stair[1];
		dp[1][1] = stair[1] + stair[0];
		for(int i = 2; i < n ; i++) {
			dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + stair[i];
			dp[i][1] = dp[i-1][0] + stair[i];
		}
		System.out.println(Math.max(dp[n-1][1], dp[n-1][0]));
	}
}
