import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		int[] dp = new int[100001];
		dp[0] = 1;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int coin : arr){
			for (int i = coin; i <= K; i++){
				dp[i] += dp[i - coin];
			}
		}

		System.out.println(dp[K]);
	}
}
