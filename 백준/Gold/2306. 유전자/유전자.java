import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int length = s.length();
		int[][] dp = new int[length][length];

		for (int len = 2; len <= length; len++) {
			for (int a = 0; a < length; a++) {

				if (a + len > length) {
					break;
				}

				int b = a + len - 1;

				for (int k = a; k < b; k++) {
					dp[a][b] = Math.max(dp[a][b], dp[a][k] + dp[k+1][b]);
				}

				if (s.charAt(a) == 'a' && s.charAt(b) == 't' || s.charAt(a) == 'g' && s.charAt(b) == 'c') {
					dp[a][b] = Math.max(dp[a][b], dp[a+1][b-1] + 2);
				}
			}
		}

		System.out.println(dp[0][length-1]);
	}
}
