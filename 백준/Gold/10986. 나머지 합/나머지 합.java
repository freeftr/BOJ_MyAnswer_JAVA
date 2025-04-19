import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] mod = new long[M];
		long[] presum = new long[N + 1];

		mod[0] = 1;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			presum[i] = presum[i - 1] + Long.parseLong(st.nextToken());
			int leftover = (int)(presum[i] % M);
			if (leftover < 0) leftover += M;
			mod[leftover]++;
		}

		long answer = 0;
		for (int i = 0; i < M; i++) {
			answer += mod[i] * (mod[i] - 1) / 2;
		}

		System.out.println(answer);
	}
}
